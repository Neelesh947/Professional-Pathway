package com.recruiter.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.recruiter.service.model.Recruiter;
import com.recruiter.service.repository.UserRepository;


@Service
public class UserDetailsSecurityServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Recruiter user=this.userRepository.findByUsername(username);
		
		if(user==null)
		{
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("USER_NOT_FOUND_!!");
		}
		return user;
	}

}
