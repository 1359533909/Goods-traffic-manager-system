package com.mxl.demo.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mxl.demo.entity.CarInfo;
import com.mxl.demo.entity.User;
import com.mxl.demo.service.CarInfoService;
import com.mxl.demo.service.UserService;


@Controller
public class DemoController {
	@Autowired
	UserService userService;
	
	@Autowired
	CarInfoService carInfoService;
	
	@RequestMapping("/")
	public String toDemo() {
		return "sign-in";
	}
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "sign-in";
	}
	@RequestMapping("/Unauthor")
	public String Unauthor() {
		return "Unauthor";
	}
	
//	登录
	@RequestMapping(value="/login")
	public String Login(String username,String password,Model model) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(username,password);
		try {
			subject.login(token);
			//加载评论区数据
			User user = userService.findUserByName(username);
			model.addAttribute("user", user);
			return "/user/index";
		} catch (UnknownAccountException e) {
			// 用户名不存在
			model.addAttribute("msg", "用户名不存在!");
			return "sign-in";
		}catch (IncorrectCredentialsException e) {
			// TODO: handle exception
			model.addAttribute("msg", "密码不正确!");
			return "sign-in";
		}catch(ExcessiveAttemptsException e){
			model.addAttribute("msg", "登录次数已经超过限制，请一分钟后重试");
			return "sign-in";
		}
	}
//	登录之后的跳转
	@RequestMapping(value = "/user/TransportInfo/{username}")
	public String toUsertransportInfo(@PathVariable(required = true, value = "username")String username,Model model) {
		System.out.println("进入toUsertransportInfo:"+username);
		User user = userService.findUserByName(username);
		PageHelper.startPage(1,10);
		Page<CarInfo> page = carInfoService.selectAll();
		model.addAttribute("pageList", page);
		model.addAttribute("user", user);
		return "user/transportInfo";
	}
	@RequestMapping(value = "/user/index/{username}")
	public String toUserIndex(@PathVariable(required = true, value = "username")String username,Model model) {
		User user = userService.findUserByName(username);
		model.addAttribute("user", user);
		return "user/index";
	}
	@RequestMapping(value = "/user/AirplaneInfo/{username}")
	public String toUserAirplaneInfo(@PathVariable(required = true, value = "username")String username,Model model) {
		User user = userService.findUserByName(username);
		model.addAttribute("user", user);
		return "user/airplaneInfo";
	}
	@RequestMapping(value = "/user/publishCarInfo/{username}")
	public String toPublishCarInfo(@PathVariable(required = true, value = "username")String username,Model model) {
		User user = userService.findUserByName(username);
		model.addAttribute("user", user);
		return "user/publishCarInfo";
	}
	@RequestMapping(value = "/user/PublishAirplaneInfo/{username}")
	public String toPublishAirplaneInfo(@PathVariable(required = true, value = "username")String username,Model model) {
		User user = userService.findUserByName(username);
		System.out.println("进来了!");
		model.addAttribute("user", user);
		return "user/publishAirplaneInfo";
	}
}
