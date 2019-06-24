package com.mxl.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mxl.demo.entity.CarInfo;
import com.mxl.demo.service.CarInfoService;

@Controller
public class TestController {
	@Autowired
	CarInfoService carInfoService;
	@RequestMapping("/test")
	@ResponseBody
	public String Test(String current) {
		System.out.println("current:"+current);
		return current;
	}
}
