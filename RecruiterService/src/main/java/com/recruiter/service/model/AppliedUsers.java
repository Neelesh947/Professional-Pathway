package com.recruiter.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppliedUsers {

	private String userId;
	private String firstName;
	private String lastName;
	private String fullName;
	private String email;
	private String username;
	private String password;
	private String address;
	private String phone;
}
