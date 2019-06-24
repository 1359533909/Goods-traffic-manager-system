package com.mxl.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.mxl.demo.entity.CarInfo;

public interface CarInfoMapper {
	public List<CarInfo> selectAllByLimit(@Param("start") int start, @Param("pageSize") int pageSize);
	public Page<CarInfo> selectAll();
	public int selectCount();
	public Page<CarInfo> selectAllByCarInfo(CarInfo gi);
	public void deleteCar(String car_number);
	public CarInfo selectCarByCar_number(String car_number);
	public void updateCarInfoByCar(CarInfo car);
	public void insertCarInfo(CarInfo car);
}
