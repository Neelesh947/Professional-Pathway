package com.post.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.post.service.model.Job;
import com.post.service.service.JobService;

@RestController
@RequestMapping("/jobpost")
@CrossOrigin("*")
public class JobController {

	@Autowired
	private JobService jobService;
	
	//create Job Post
	@PostMapping("/")
	public ResponseEntity<Job> createJobPost(@RequestBody Job job)
	{
		Job jobpost=this.jobService.createJobPost(job);
		return ResponseEntity.status(HttpStatus.CREATED).body(jobpost);
	}
	
	//get all jobpost list
	@GetMapping("/")
	public ResponseEntity<List<Job>> getAllJobs()
	{
		List<Job> joblist=this.jobService.getAllJobList();
		return ResponseEntity.status(HttpStatus.OK).body(joblist);
	}
	
	//get job by jobID
	@GetMapping("/{jobId}")
	public ResponseEntity<Job> getJobsByJobId(@PathVariable("jobId") String jobid)
	{
		Job job=this.jobService.getJobByJobId(jobid);
		return ResponseEntity.status(HttpStatus.OK).body(job);
	}
	
	// delete job post
	@DeleteMapping("/{jobId}")
	public  ResponseEntity<Void> deleteJobPost(@PathVariable String jobId)
	{
		this.jobService.deleteJobById(jobId);
		return ResponseEntity.noContent().build();
	}
	
	//list of User
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Job>> getListOfJobUserId(@PathVariable String userId)
	{
		List<Job> list=this.jobService.getListByuserId(userId);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	//list of Recruiter
	@GetMapping("/recruiter/{recruiterId}")
	public ResponseEntity<List<Job>> getListOfrecruiter(@PathVariable String recruiterId)
	{
		List<Job> list=this.jobService.getListByrecruiterId(recruiterId);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	
	@PutMapping("/job/{jobId}")
	public ResponseEntity<Job> updatePostJobs(@PathVariable String jobId, @RequestBody Job job)
	{
		Job existingjob=this.jobService.getJobByJobId(jobId);
		if(existingjob==null)
		{
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
		Job jobs=this.jobService.updateJobs(job, jobId);
		return ResponseEntity.status(HttpStatus.OK).body(jobs);
	}
}
