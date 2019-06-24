package com.mxl.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.mxl.demo.entity.GoodInfo;
import com.mxl.demo.mapper.GoodInfoMapper;
import com.mxl.demo.service.GoodInfoService;

@Service
public class GoodInfoServiceImpl implements GoodInfoService {
	@Autowired
	GoodInfoMapper goodInfoMapper;
	@Override
	public Page<GoodInfo> selectAll() {
		// TODO Auto-generated method stub
		Page<GoodInfo> pagelist = goodInfoMapper.selectAll();
		return pagelist;
	}
	@Override
	public Page<GoodInfo> selectAllByStation(GoodInfo gi) {
		// TODO Auto-generated method stub
		Page<GoodInfo> list = goodInfoMapper.selectAllByStation(gi);
		return list;
	}

}
