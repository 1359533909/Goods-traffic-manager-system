package com.mxl.demo.controller;

import java.sql.Date;
import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONParser;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mxl.demo.entity.CarInfo;
import com.mxl.demo.entity.CompanyUser;
import com.mxl.demo.entity.GoodInfo;
import com.mxl.demo.entity.MyPage;
import com.mxl.demo.entity.User;
import com.mxl.demo.service.CarInfoService;
import com.mxl.demo.service.CompanyUserService;
import com.mxl.demo.service.GoodInfoService;
import com.mxl.demo.service.UserService;

@Controller
public class OperatorController {
	@Autowired
	UserService userService;
	@Autowired
	CarInfoService carInfoService;
	@Autowired
	GoodInfoService goodInfoService;
	@Autowired
	CompanyUserService companyUserService;
	
	
	@RequestMapping(value="/user/searchCarsPage")
	@ResponseBody
	public MyPage<CarInfo> supplyOfCarsPage(int current) {
		PageHelper.startPage(current,10);
		Page<CarInfo> pagelist = carInfoService.selectAll();
		MyPage<CarInfo> myPagelist=new MyPage<CarInfo>();
		myPagelist.pageNum=current;
		myPagelist.page=pagelist;
		return myPagelist;
	}
	
	@RequestMapping(value = "/user/toDelete")
	@ResponseBody
	public String toDelete(String car_number) {
		carInfoService.deleteCar(car_number);
		return car_number;
	}
	
	@RequestMapping(value="/user/transportInfoEdit/{car_number}/{username}")
	public String toTransportInfoEdit(@PathVariable(required = true,value = "car_number") String car_number,@PathVariable(required = true,value = "username")String username,Model model) {
		User user = userService.findUserByName(username);
		CarInfo car = carInfoService.selectCarByCar_number(car_number);
		model.addAttribute("user", user);
		model.addAttribute("car", car);
		return "user/transportInfoEdit";
	}
	
	@RequestMapping(value = "/updateCarInfo")
	public String updateCarInfo(String username,String car_number,String car_type,float car_length,int bear_weight,Date register_time,int all_account,int using_account,String start_station,String destination_station,Model model) {
		CarInfo car=new CarInfo();
		car.setBear_weight(bear_weight);
		car.setCar_length(car_length);
		car.setCar_number(car_number);
		car.setCar_type(car_type);
		car.setDestination_station(destination_station);
		car.setRegister_time(register_time);
		car.setStart_station(start_station);
		car.setUsing_account(using_account);
		carInfoService.updateCarInfoByCar(car);
		return "redirect:/user/TransportInfo/"+username;
	}
	
	@RequestMapping(value="/insertCarInfo",method = RequestMethod.POST)
	@ResponseBody
	public String insertCarInfo(String content,String car_number,String car_type,float car_length,int bear_weight,
			Date register_time,int all_account,int using_account,String start_station, String destination_station
			) {
		CarInfo car=new CarInfo();
		car.setBear_weight(bear_weight);
		car.setAll_account(all_account);
		car.setCar_length(car_length);
		car.setCar_number(car_number);
		car.setCar_type(car_type);
		car.setContent(content);
		car.setDestination_station(destination_station);
		car.setOwner_id(1);
		car.setRegister_time(register_time);
		car.setUsing_account(using_account);
		car.setStart_station(start_station);
		try {
			carInfoService.insertCarInfo(car);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return "发布失败,请检查网络是否可用!";
		}
		return "发布成功";
	}
}
