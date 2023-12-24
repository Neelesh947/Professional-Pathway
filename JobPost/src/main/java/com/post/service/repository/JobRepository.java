package com.post.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.post.service.model.Job;


@Repository
public interface JobRepository extends JpaRepository<Job, String>{

	List<Job> findByRecruiterId(String recruiterId);
	
	List<Job> findByUserId(String userId);
}
