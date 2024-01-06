package com.user.service.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.service.model.User;
import com.user.service.repository.UserRepository;
import com.user.service.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(User user) throws Exception {
		// TODO Auto-generated method stub
		String uuid=UUID.randomUUID().toString();
		user.setUserId(uuid);
		
		String fname=user.getFirstName();
		String lname=user.getLastName();
		String fullName=fname.concat(" ").concat(lname);
		
		user.setFullName(fullName);
		
		User existUser=this.userRepository.findByUsername(user.getUsername());
		
		if(existUser !=null)
		{
			System.out.println("User Already Exist");
			throw new Exception("USER ALREADY PRESENT");
		}
		else
		{
			existUser=this.userRepository.save(user);
		}
		return existUser;
	}

	@Override
	public List<User> getallUser() {
		// TODO Auto-generated method stub
		List<User> user=this.userRepository.findAll();
		return user;
	}

	@Override
	public User getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		User user=this.userRepository.findById(userId).orElse(null);
		return user;
	}

}
