package com.user.service.controller;

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

import com.user.service.model.User;
import com.user.service.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//create user
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user) throws Exception
	{
		User user1=this.userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	//get all user
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUser()
	{
		List<User> user=this.userService.getallUser();
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	//get User BY userid
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserByUserId(@PathVariable String userId)
	{
		User user=this.userService.getUserByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

}
