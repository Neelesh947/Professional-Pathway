package com.recruiter.service.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recruiter {

	@Id
	private String id;
	private String firstname;
	private String lastname;
	private String name;
	private String email;
	private String username;
	private String password;
	private String address;
	private String phone;
	
	@Transient
	private List<Job> job=new ArrayList<>();
	
}
