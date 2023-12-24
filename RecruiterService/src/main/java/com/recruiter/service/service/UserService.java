package com.recruiter.service.service;

import java.util.List;

import com.recruiter.service.model.Recruiter;

public interface UserService {
	
	//create users
	public Recruiter createUser(Recruiter user) throws Exception;

	public List<Recruiter> getAllUser();

	public Recruiter getUserByUserId(String id);
	
}
