package com.mxl.demo.entity;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarInfo {
	private String car_number;
	private String car_type;
	private float car_length;
	private int bear_weight;
	private int owner_id;
	private Date register_time;
	private int all_account;
	private int using_account;
	private String start_station;
	private String destination_station;
	private String content;
}
