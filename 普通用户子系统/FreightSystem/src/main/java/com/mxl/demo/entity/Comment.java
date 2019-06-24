package com.mxl.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Comment {
	private int id;
	private String username;
	private String context;
}
