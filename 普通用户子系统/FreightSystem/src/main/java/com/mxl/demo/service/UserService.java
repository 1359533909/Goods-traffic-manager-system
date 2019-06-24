package com.mxl.demo.service;

import com.mxl.demo.entity.User;

public interface UserService {
	public User findUserByName(String name);
	public User findUserById(int id);
	public void insertUser(User user);
}
