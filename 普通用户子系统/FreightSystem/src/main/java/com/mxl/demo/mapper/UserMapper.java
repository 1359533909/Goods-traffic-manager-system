package com.mxl.demo.mapper;

import com.mxl.demo.entity.User;

public interface UserMapper {
	public User findUserByName(String username);
	public User findUserById(int id);
	public void insertUser(User user);
}
