package com.user.service.service;

import java.util.List;

import com.user.service.model.User;

public interface UserService {

	//create user
	public User createUser(User user) throws Exception;
	
	//get user
	public List<User>  getallUser(); 
	
	//get user by userid
	public User getUserByUserId(String userId);
	
}
