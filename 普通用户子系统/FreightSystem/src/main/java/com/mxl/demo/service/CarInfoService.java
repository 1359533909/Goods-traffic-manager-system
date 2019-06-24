package com.mxl.demo.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.mxl.demo.entity.CarInfo;

public interface CarInfoService {
	public List<CarInfo> selectAllByLimit(int start, int pageSize);
	public Page<CarInfo> selectAll();
	public int selectCount();
	public Page<CarInfo> selectAllByCarInfo(CarInfo gi);
	public CarInfo selectCarByCar_number(String car_number);
}
