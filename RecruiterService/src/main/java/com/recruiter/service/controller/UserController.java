package com.recruiter.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruiter.service.model.Recruiter;
import com.recruiter.service.service.UserService;

@RestController
@RequestMapping("/recruiter")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//create user	
	@PostMapping("/")
	public ResponseEntity<Recruiter> createUser(@RequestBody Recruiter user) throws Exception
	{
		Recruiter u1=this.userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(u1);
	}
	
	//get all user
	@GetMapping("/")
	public ResponseEntity<List<Recruiter>> getAllUser(){
		
		List<Recruiter> list=this.userService.getAllUser();		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	//get user by useriId
	@GetMapping("/{id}")
	public ResponseEntity<Recruiter> getUserByUserId(@PathVariable String id)
	{
		Recruiter user=this.userService.getUserByUserId(id);		
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
}
