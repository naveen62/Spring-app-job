package com.spring.jobapp.service;

import java.util.List;

import com.spring.jobapp.model.Job;

public interface IJobService {
	public Job createJob(Job job);
	
	public List<Job> getAll();
	
	public Job getJob(long jobId);
	
	public Job updateJob(Job job, long jobId);
	
	public void deleteJob(long jobId);
	
	public List<Job> searchByTitleAndRole(String search);
}
