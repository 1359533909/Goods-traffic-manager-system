package com.mxl.demo.controller;

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
import com.mxl.demo.entity.Comment;
import com.mxl.demo.entity.CompanyUser;
import com.mxl.demo.entity.GoodInfo;
import com.mxl.demo.entity.MyPage;
import com.mxl.demo.entity.User;
import com.mxl.demo.service.CarInfoService;
import com.mxl.demo.service.CommentService;
import com.mxl.demo.service.CompanyUserService;
import com.mxl.demo.service.GoodInfoService;
import com.mxl.demo.service.UserService;
import com.mxl.demo.service.impl.CommentServiceImpl;

@Controller
public class OperatorController {
	@Autowired
	CommentService commentService;
	@Autowired
	UserService userService;
	@Autowired
	CarInfoService carInfoService;
	@Autowired
	GoodInfoService goodInfoService;
	@Autowired
	CompanyUserService companyUserService;
	
	@RequestMapping(value = "/addComment",method = RequestMethod.POST)
	public String getCommentAllByLimit(Model model,String username,String context){
		Comment comment=new Comment();
		comment.setUsername(username);
		comment.setContext(context);
		commentService.insertComment(comment);
//		数据库获取评论
		int count = commentService.selectCount();
		List<Comment> comments = commentService.selectAllByLimit(count-10,10);
		model.addAttribute("comments", comments);
		model.addAttribute("username", username);
		return "user/index";
	}
	
	@RequestMapping(value="/SupplyOfCarsPage")
	@ResponseBody
	public MyPage<CarInfo> supplyOfCarsPage(int current) {
		PageHelper.startPage(current,10);
		Page<CarInfo> pagelist = carInfoService.selectAll();
		MyPage<CarInfo> myPagelist=new MyPage<CarInfo>();
		myPagelist.pageNum=current;
		myPagelist.page=pagelist;
		return myPagelist;
	}
	
	@RequestMapping(value="/SupplyOfGoodPage")
	@ResponseBody
	public MyPage<GoodInfo> supplyOfGoodPage(int current) {
		PageHelper.startPage(current,10);
		Page<GoodInfo> pagelist = goodInfoService.selectAll();
		MyPage<GoodInfo> myPagelist=new MyPage<GoodInfo>();
		myPagelist.pageNum=current;
		myPagelist.page=pagelist;
		return myPagelist;
	}
	
	@RequestMapping(value = "/searchGoods")
	public String searchGoods(String start_station,String destination_station,Model model) {
		GoodInfo gi=new GoodInfo();
		gi.setStart_station(start_station);
		gi.setDestination_station(destination_station);
		PageHelper.startPage(1,10);
		Page<GoodInfo> page = goodInfoService.selectAllByStation(gi);
		model.addAttribute("pagelist", page);
		return "SupplyOfGoods";
	}
	
	@RequestMapping(value = "/usersearchGoods")
	public String usersearchGoods(String username,String start_station,String destination_station,Model model) {
		GoodInfo gi=new GoodInfo();
		gi.setStart_station(start_station);
		gi.setDestination_station(destination_station);
		PageHelper.startPage(1,10);
		Page<GoodInfo> page = goodInfoService.selectAllByStation(gi);
		model.addAttribute("pagelist", page);
		model.addAttribute("username", username);
		return "user/SupplyOfGoods";
	}
	
	@RequestMapping(value = "/searchCars")
	public String searchCars(String start_station,String destination_station,String cartype,float carlength,Model model) {
		CarInfo gi=new CarInfo();
		gi.setStart_station(start_station);
		gi.setDestination_station(destination_station);
		gi.setCar_length(carlength);
		gi.setCar_type(cartype);
		Page<CarInfo> page = carInfoService.selectAllByCarInfo(gi);
		model.addAttribute("pagelist", page);
		return "SupplyOfCars";
	}
	
	@RequestMapping(value = "/userSearchCars")
	public String userSearchCars(String username,String start_station,String destination_station,String cartype,float carlength,Model model) {
		CarInfo gi=new CarInfo();
		gi.setStart_station(start_station);
		gi.setDestination_station(destination_station);
		gi.setCar_length(carlength);
		gi.setCar_type(cartype);
		Page<CarInfo> page = carInfoService.selectAllByCarInfo(gi);
		model.addAttribute("pagelist", page);
		model.addAttribute("username", username);
		return "user/SupplyOfCars";
	}
	
	/**
	 * 普通用户注册信息
	 */
	@RequestMapping(value = "/regPerson",method = RequestMethod.POST)
	@ResponseBody
	public String regPerson(String username,String password,String email,String qq,String mobile) {
		Md5Hash strenPs=new Md5Hash(password);
		User user=new User();
		user.setEmail(email);
		user.setMobile(mobile);
		user.setPassword(strenPs.toString());
		user.setQq(qq);
		user.setUsername(username);
		try {
			userService.insertUser(user);
		} catch (Exception e) {
			// TODO: handle exception
			String str="注册失败,用户名已存在!";
			return str;
		}
		String str="注册成功!";
		return str;
	}
	/**
	 * 公司用户注册
	 * 
	 */
	@RequestMapping(value = "/regCompany")
	@ResponseBody
	public String regCompany(String username,String password,String companyname,String principal,int moneydeposit,String companyaddress) {
		Md5Hash strenPs=new Md5Hash(password);
		CompanyUser user=new CompanyUser();
		user.setUsername(username);
		user.setCompanyaddress(companyaddress);
		user.setCompanyname(companyname);
		user.setMoneydeposit(moneydeposit);
		user.setPassword(strenPs.toString());
		try {
			int flag = companyUserService.insertCompanyUser(user);
			System.out.println("注册成功否:"+flag);
		} catch (Exception e) {
			// TODO: handle exception
			return "注册失败,用户名已存在!";
		}
		String str="注册成功!";
		return str;
	}
	
//	登录前浏览新闻
	@RequestMapping(value = "/toShowCar/{car_number}")
	public String toShowCar(@PathVariable(required = true,value = "car_number")String car_number,Model model) {
		CarInfo carInfo = carInfoService.selectCarByCar_number(car_number);
		model.addAttribute("carInfo", carInfo);
		return "single-CarInfoNew";
	}
	
//	登录后浏览新闻
	@RequestMapping(value = "/toUserShowCar/{car_number}/{username}")
	public String toUserShowCar(@PathVariable(required = true,value = "car_number")String car_number,@PathVariable(required = true,value = "username")String username,Model model) {
		CarInfo carInfo = carInfoService.selectCarByCar_number(car_number);
		System.out.println("我进来了p");
		model.addAttribute("carInfo", carInfo);
		model.addAttribute("username", username);
		return "user/single-UserCarInfoNew";
	}
	
}
