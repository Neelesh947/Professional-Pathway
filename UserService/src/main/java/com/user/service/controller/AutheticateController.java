package com.user.service.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.config.JwtUtils;
import com.user.service.model.JwtRequest;
import com.user.service.model.JwtResponse;
import com.user.service.model.User;
import com.user.service.serviceImpl.UserDetailsSecurityServiceImpl;

@RestController
@CrossOrigin("*")
public class AutheticateController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsSecurityServiceImpl userDetailsSecurityServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	//generate tokens
	
	public ResponseEntity<? > generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		try {
			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		}
		catch(Exception e)
		{
			throw new Exception("USEER_NOT_FOUND_!!");
		}
		
		 /////////////authenticate

        UserDetails userDetails = this.userDetailsSecurityServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
	}
	 private void authenticate(String username, String password) throws Exception {

	        try {

	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

	        } catch (DisabledException e) {
	            throw new Exception("USER DISABLED " + e.getMessage());
	        } catch (BadCredentialsException e) {
	            throw new Exception("Invalid Credentials " + e.getMessage());
	        }
	    }
	 
	 
	//return the details of the user who is currently login
		 @GetMapping("/current-user")
		 public User getCurrentUser(Principal principal)
		 {
			 return ((User)this.userDetailsSecurityServiceImpl.loadUserByUsername(principal.getName()));
		 }
		 
}
