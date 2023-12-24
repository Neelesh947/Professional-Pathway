package com.recruiter.service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.recruiter.service.model.Recruiter;

public interface UserRepository extends JpaRepository<Recruiter, String>{

	public Recruiter findByUsername(String username);
	
	
}
