package com.post.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JobPostApplication {
	
		
	public static void main(String[] args) {
		SpringApplication.run(JobPostApplication.class, args);
	}
}
