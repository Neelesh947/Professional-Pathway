package com.post.service.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.post.service.model.Job;
import com.post.service.repository.JobRepository;
import com.post.service.service.JobService;

@Service
public class JobServiceImpl implements JobService{

	@Autowired
	private JobRepository jobRepository;

	@Override
	public Job createJobPost(Job job) {
		// TODO Auto-generated method stub
		LocalDate currentdate=LocalDate.now();
		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	        // Format the current date as a string using the defined format
	        String formattedDate = currentdate.format(formatter);
	        
	        job.setPostDate(formattedDate);
	        
	        String uuid=UUID.randomUUID().toString();
	        job.setJobId(uuid);
		
		Job jobpost=this.jobRepository.save(job);
		return jobpost;
	}

	@Override
	public List<Job> getAllJobList() {
		// TODO Auto-generated method stub
		List<Job> joblist=this.jobRepository.findAll();
		return joblist;
	}

	@Override
	public Job getJobByJobId(String jobid) {
		// TODO Auto-generated method stub
		Job job=this.jobRepository.findById(jobid).orElse(null);
		return job;
	}

	@Override
	public void deleteJobById(String jobId) {
		// TODO Auto-generated method stub
		this.jobRepository.deleteById(jobId);
	}

	@Override
	public List<Job> getListByuserId(String userId) {
		// TODO Auto-generated method stub
		return this.jobRepository.findByUserId(userId);
	}

	@Override
	public List<Job> getListByrecruiterId(String recruiterId) {
		// TODO Auto-generated method stub
		return this.jobRepository.findByRecruiterId(recruiterId);
	}

	@Override
	public Job updateJobs(Job job, String jobId) {
		// TODO Auto-generated method stub
		job.setJobId(jobId);
		return this.jobRepository.save(job);
	}

}
