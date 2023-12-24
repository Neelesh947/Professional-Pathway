package com.post.service.service;

import java.util.List;

import com.post.service.model.Job;

public interface JobService {

	//create job post
	Job createJobPost(Job job);

	List<Job> getAllJobList();

	Job getJobByJobId(String jobid);

	void deleteJobById(String jobId);
	
	//get all by userId i.e. user applied
	List<Job> getListByuserId(String userId);	
	
	//get all by recruiter
	List<Job> getListByrecruiterId(String recruiterId);

}
