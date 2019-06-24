package com.mxl.demo.entity;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GoodInfo {
	private int id;
	private int user_id;
	private String goodtype;
	private String start_station;
	private String destination_station;
	private Date update_time; 
}
