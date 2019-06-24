package com.mxl.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mxl.demo.entity.CompanyUser;
import com.mxl.demo.mapper.CompanyUserMapper;
import com.mxl.demo.service.CompanyUserService;

@Service
public class CompanyUserServiceImpl implements CompanyUserService {
	@Autowired
	CompanyUserMapper userMapper;
	
	@Override
	public int insertCompanyUser(CompanyUser user) {
		// TODO Auto-generated method stub
		int flag = userMapper.insertCompanyUser(user);
		return flag;
	}

}
