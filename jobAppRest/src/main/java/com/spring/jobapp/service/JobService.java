package com.spring.jobapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jobapp.exception.ItemNotFoundException;
import com.spring.jobapp.model.Job;
import com.spring.jobapp.repo.IJobRepo;

@Service
public class JobService implements IJobService {
	
	@Autowired
	private IJobRepo repo;
	
	@Override
	public Job createJob(Job job) {
		Job saveedJob = repo.save(job);
		return saveedJob;
	}

	@Override
	public List<Job> getAll() {
		List<Job> allJobs = repo.findAll();
		return allJobs;
	}

	@Override
	public Job getJob(long jobId) {
		if(!repo.existsById(jobId)) {
			throw new ItemNotFoundException("item not found for id");
		}
		Job job = repo.findById(jobId).get();
		return job;
	}

	@Override
	public Job updateJob(Job job, long jobId) {
		if(!repo.existsById(jobId)) {
			throw new ItemNotFoundException("item not found for id");
		}
		repo.deleteById(jobId);
		Job updatedJob = repo.save(job);
		return updatedJob;
	}

	@Override
	public void deleteJob(long jobId) {
		if(!repo.existsById(jobId)) {
			throw new ItemNotFoundException("item not found for id");
		}
		repo.deleteById(jobId);
	}

	@Override
	public List<Job> searchByTitleAndRole(String search) {
		return repo.searchByTitleAndRole(search);
	}
}
