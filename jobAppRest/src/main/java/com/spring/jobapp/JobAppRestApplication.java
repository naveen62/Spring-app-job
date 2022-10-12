package com.spring.jobapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.jobapp.service.IStorageService;
import com.spring.jobapp.service.StorageService;

@SpringBootApplication
public class JobAppRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobAppRestApplication.class, args);
	}
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			// storageService.deleteAll();
			storageService.init();
		};
	}

}
