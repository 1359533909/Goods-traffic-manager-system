package com.mxl.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyUser {
	private int id;
	private String username;
	private String password;
	private String perms;
	private String companyname;
	private String principal;
	private int moneydeposit;
	private String companyaddress;
}
