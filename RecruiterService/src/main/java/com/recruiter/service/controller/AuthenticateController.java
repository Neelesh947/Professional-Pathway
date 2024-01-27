package com.recruiter.service.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.recruiter.service.config.JwtUtils;
import com.recruiter.service.model.JwtRequest;
import com.recruiter.service.model.JwtResponse;
import com.recruiter.service.model.Recruiter;
import com.recruiter.service.serviceImpl.UserDetailsSecurityServiceImpl;

@Controller
@CrossOrigin("*")
public class AuthenticateController {


	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsSecurityServiceImpl userDetailsSecurityServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	//generate tokens
    @PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		try {
			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		}
		catch(Exception e)
		{
			throw new Exception("USER_NOT_FOUND_!!");
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
		 public Recruiter getCurrentUser(Principal principal)
		 {
			return (Recruiter) this.userDetailsSecurityServiceImpl.loadUserByUsername(principal.getName());
		 }
}
