package com.spring.jobappclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JobAppRestClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobAppRestClientApplication.class, args);
		System.out.println();
	}
	@Bean
	public RestTemplate getRestTemp() {
		return new RestTemplate();
	}
}
