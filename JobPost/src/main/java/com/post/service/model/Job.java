package com.post.service.model;

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
public class Job {

	@Id
	private String jobId;
	private String title;
	private String description;
	private String experience;
	private String company;
	private String Location;
	private String salary;
	private String employmentType;
	private String skills;
	private String applicationDeadlines;
	private String contactInformation;
	private String postDate;
	private String recruiterId;
	private String userId;
}
