package com.recruiter.service.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.recruiter.service.model.AppliedUsers;
import com.recruiter.service.model.Job;
import com.recruiter.service.model.Recruiter;
import com.recruiter.service.repository.UserRepository;
import com.recruiter.service.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Override
	public Recruiter createUser(Recruiter user) throws Exception {
		// TODO Auto-generated method stub
		
		String uuid=UUID.randomUUID().toString();
		user.setId(uuid);	
		
		String fname=user.getFirstname();
		String lname=user.getLastname();
		
		String fullName = fname.concat(" ").concat(lname);
		
		user.setName(fullName);
		
		Recruiter existUser=this.userRepository.findByUsername(user.getUsername());
		
		if(existUser !=null)
		{
			System.out.println("User Already Exist");
			throw new Exception("USER ALREADY PRESENT");
		}
		else
		{
			//create user
			existUser=this.userRepository.save(user);
		}
		
		return existUser;
	}
	
	
	// get all user
	public List<Recruiter> getAllUser() {
		// TODO Auto-generated method stub
		
		List<Recruiter> user=this.userRepository.findAll();		
		return user;
	}

	//get single user
	@Override
	public Recruiter getUserByUserId(String id) {
		// get user from database with the help of user repository
		Recruiter user=this.userRepository.findById(id).orElse(null);
		
		//fetch rating of the above user from JOB SERVICE
		//localhost:8083/jobpost/recruiter/17da614e-6fa1-4c92-8d22-c5e61cea7c31
		Job[] jobPostByRecruiter= restTemplate.getForObject("http://JOB-SERVICE/jobpost/recruiter/"+user.getId(), Job[].class);
//		log.info("{} ",jobPostByRecruiter);
		List<Job> Jobs= Arrays.stream(jobPostByRecruiter).toList();
			
		List<Job> appliedUsers=Jobs.stream().map(users->{
			//api  to call the get the applied user
			//http://localhost:8082/user/69019d1c-2dfe-407d-929f-501432ea06f8
			ResponseEntity<AppliedUsers> applieduserList= restTemplate.getForEntity("http://USER-SERVICE/user/"+users.getUserId(),AppliedUsers.class);
			AppliedUsers ausers=applieduserList.getBody();
			
			
//			ResponseEntity<AppliedUsers[]> applieduserList= restTemplate.getForEntity("http://USER-SERVICE/user/"+users.getUserId(),AppliedUsers[].class);
//			AppliedUsers[] ausers=applieduserList.getBody();
						
			//set the applied user to the job posts			
			users.setUsers(ausers);
			
			//users.setUsers(Arrays.asList(ausers));
			
			//return the jobpost
			return users;
		}).collect(Collectors.toList());
		
		user.setJob(appliedUsers);
				
		return user;
	}
}
