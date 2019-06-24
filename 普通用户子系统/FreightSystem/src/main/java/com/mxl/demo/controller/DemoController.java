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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.stat.TableStat.Mode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mxl.demo.entity.CarInfo;
import com.mxl.demo.entity.Comment;
import com.mxl.demo.entity.GoodInfo;
import com.mxl.demo.entity.User;
import com.mxl.demo.service.CarInfoService;
import com.mxl.demo.service.CommentService;
import com.mxl.demo.service.GoodInfoService;

/**
 * @author 莫小林
 *
 */
@Controller
public class DemoController {
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	CarInfoService carInfoService;
	
	@Autowired
	GoodInfoService goodInfoService;
	
	
	
	@RequestMapping(value="/toLogin")
	public String toLogin(Model model) {
		return "login";
	}
	@RequestMapping(value="/login")
	public String Login(String username,String password,Model model) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(username,password);
		try {
			subject.login(token);
			//加载评论区数据
			int count = commentService.selectCount();
			List<Comment> comments = commentService.selectAllByLimit(count-10,10);
			model.addAttribute("comments", comments);
			model.addAttribute("username", username);
			return "/user/index";
		} catch (UnknownAccountException e) {
			// 用户名不存在
			model.addAttribute("msg", "用户名不存在!");
			return "login";
		}catch (IncorrectCredentialsException e) {
			// TODO: handle exception
			model.addAttribute("msg", "密码不正确!");
			return "login";
		}catch(ExcessiveAttemptsException e){
			model.addAttribute("msg", "登录次数已经超过限制，请一分钟后重试");
			return "login";
		}
	}
	@RequestMapping(value="/Unauthor")
	public String unauthor() {
		return "Unauthor";
		
	}
	
	@RequestMapping(value="/")
	public String toDefault(Model model) {
		int count = commentService.selectCount();
		List<Comment> comments = commentService.selectAllByLimit(count-10,10);
		model.addAttribute("comments", comments);
		return "index";
	}
	/**
	 * 注册界面的选择跳转
	 */
	@RequestMapping(value="/toRegPerson")
	public String toRegPerson() {
		return "regperson";
	}
	@RequestMapping(value = "/toRegCompany")
	public String toRegCompany() {
		return "regcompany";
	}
	/**
	 * 登录之前页面跳转
	 */
	@RequestMapping(value="/index")
	public String toIndex(Model model) {
		int count = commentService.selectCount();
		List<Comment> comments = commentService.selectAllByLimit(count-10,10);
		model.addAttribute("comments", comments);
		return "index";
	}
	@RequestMapping(value="/SupplyOfGoods")
	public String toSupplyOfGoods(Model model) {
		PageHelper.startPage(1,10);
		Page<GoodInfo> pagelist = goodInfoService.selectAll();
		model.addAttribute("pagelist", pagelist);
		return "SupplyOfGoods";
	}
	@RequestMapping(value="/SupplyOfCars")
	public String toSupplyOfCars(Model model) {
		PageHelper.startPage(1,10);
		Page<CarInfo> pagelist = carInfoService.selectAll();
		model.addAttribute("pagelist", pagelist);
		return "SupplyOfCars";
	}
	@RequestMapping(value="/SupplyOfAirplane")
	public String toSupplyOfAirPlane() {
		return "SupplyOfAirplane";
	}
	@RequestMapping(value="/NewsInfo")
	public String toNewsInfo() {
		return "NewsInfo";
	}
	@RequestMapping(value="/aboutWe")
	public String toaboutWe() {
		return "aboutWe";
	}
	@RequestMapping(value = "/toRegist")
	public String toRegist() {
		return "selectreg";
	}
	/**
	 * 登录成功之后页面跳转user/
	 */
	@RequestMapping(value="/user/index/{username}")
	public String toUserIndex(Model model,@PathVariable(required = true, value = "username")String username) {
		int count = commentService.selectCount();
		List<Comment> comments = commentService.selectAllByLimit(count-10,10);
		model.addAttribute("comments", comments);
		model.addAttribute("username", username);
		return "user/index";
	}
	@RequestMapping(value="/user/SupplyOfAirplane/{username}")
	public String toUserSupplyOfAirplane(Model model,@PathVariable(required = true, value = "username")String username) {
		model.addAttribute("username", username);
		return "user/SupplyOfAirplane";
	}
	@RequestMapping(value="/user/SupplyOfGoods/{username}")
	public String toUserSupplyOfGoods(Model model,@PathVariable(required = true, value = "username")String username) {
		PageHelper.startPage(1,10);
		Page<GoodInfo> pagelist = goodInfoService.selectAll();
		model.addAttribute("pagelist", pagelist);
		model.addAttribute("username", username);
		return "user/SupplyOfGoods";
	}
	@RequestMapping(value="/user/SupplyOfCars/{username}")
	public String toUserSupplyOfCars(Model model,@PathVariable(required = true, value = "username")String username) {
		PageHelper.startPage(1,10);
		Page<CarInfo> pagelist = carInfoService.selectAll();
		model.addAttribute("pagelist", pagelist);
		model.addAttribute("username", username);
		return "user/SupplyOfCars";
	}
	@RequestMapping(value="/user/aboutWe/{username}")
	public String toUseraboutWe(Model model,@PathVariable(required = true, value = "username")String username) {
		model.addAttribute("username", username);
		return "user/aboutWe";
	}
	@RequestMapping(value="/user/NewsInfo/{username}")
	public String toUserNewsInfo(Model model,@PathVariable(required = true, value = "username")String username) {
		model.addAttribute("username", username);
		return "user/NewsInfo";
	}
}
