package com.mxl.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.mxl.demo.entity.CarInfo;
import com.mxl.demo.mapper.CarInfoMapper;
import com.mxl.demo.service.CarInfoService;

@Service
public class CarInfoServiceImpl implements CarInfoService {
	@Autowired
	private CarInfoMapper carInfoMapper;
	
	@Override
	public List<CarInfo> selectAllByLimit(int start, int pageSize) {
		// TODO Auto-generated method stub
		List<CarInfo> list = carInfoMapper.selectAllByLimit(start, pageSize);
		return list;
	}

	@Override
	public Page<CarInfo> selectAll() {
		// TODO Auto-generated method stub
		Page<CarInfo> pageList = carInfoMapper.selectAll();
		return pageList;
	}

	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		int count = carInfoMapper.selectCount();
		return count;
	}

	@Override
	public Page<CarInfo> selectAllByCarInfo(CarInfo gi) {
		// TODO Auto-generated method stub
		Page<CarInfo> page = carInfoMapper.selectAllByCarInfo(gi);
		return page;
	}

	@Override
	public void deleteCar(String car_number) {
		// TODO Auto-generated method stub
		carInfoMapper.deleteCar(car_number);
	}

	@Override
	public CarInfo selectCarByCar_number(String car_number) {
		// TODO Auto-generated method stub
		CarInfo car = carInfoMapper.selectCarByCar_number(car_number);
		return car;
	}

	@Override
	public void updateCarInfoByCar(CarInfo car) {
		// TODO Auto-generated method stub
		carInfoMapper.updateCarInfoByCar(car);
	}

	@Override
	public void insertCarInfo(CarInfo car) {
		// TODO Auto-generated method stub
		carInfoMapper.insertCarInfo(car);
	}

}
