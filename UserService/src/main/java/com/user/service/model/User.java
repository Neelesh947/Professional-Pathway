package com.user.service.model;

import javax.persistence.Entity;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	private String userId;
	private String firstName;
	private String lastName;
	private String fullName;
	private String email;
	private String userName;
	private String password;
	private String address;
	private String phone;
}
