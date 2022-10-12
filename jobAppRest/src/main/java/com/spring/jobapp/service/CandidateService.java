package com.spring.jobapp.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.jobapp.exception.FileNotValidException;
import com.spring.jobapp.exception.ItemNotFoundException;
import com.spring.jobapp.model.Candidate;
import com.spring.jobapp.model.Job;
import com.spring.jobapp.repo.ICandidateRepo;
import com.spring.jobapp.repo.IJobRepo;

@Service
public class CandidateService implements ICandidateService {
	
	
	@Autowired
	private ICandidateRepo candidateRepo;
	@Autowired
	private JobService jobService;
	@Autowired
	private StorageService storageService;

	@Override
	public Candidate saveCandidate(long jobId, Candidate candidate
	) {
		Job job = jobService.getJob(jobId);
		candidate.setJob(job);
		Candidate savedCandidate =  candidateRepo.save(candidate);
		return savedCandidate;
	}
	public void saveCandidateResume(long candidateId, MultipartFile file) {
		if(!candidateRepo.existsById(candidateId)) {
			throw new ItemNotFoundException("item Candidate not found for id");
		}
		if(!file.getContentType().equals("application/pdf")) {
			throw new FileNotValidException("invalid file type accept only application/pdf");
		}
		storageService.store(file, candidateId);
	}
	@Override
	public Candidate getCandidate(long candidateId) {
		if(!candidateRepo.existsById(candidateId)) {
			throw new ItemNotFoundException("item Candidate not found for id");
		}
		return candidateRepo.findById(candidateId).get();
	}
	@Override
	public void saveCandidatePic(long candidateId, MultipartFile picFile) throws IOException {
		if(!(picFile.getContentType().equals("image/png") || picFile.getContentType().equals("image/jpg") || picFile.getContentType().equals("image/jpeg"))) {
			throw new FileNotValidException("invalid file type accept only image/*");
		}
		Candidate candidate = getCandidate(candidateId);
		candidate.setPicType(picFile.getContentType());
		candidate.setPic(picFile.getBytes());
		candidateRepo.save(candidate);
	}
	@Override
	public Candidate candidateUpdate(long candidateId, Candidate candidate) {
		return candidateRepo.findById(candidateId).map(candidateItem -> {
			candidateItem.setName(candidate.getName());
			candidateItem.setEducation(candidate.getEducation());
			candidateItem.setContactNo(candidate.getContactNo());
			candidateItem.setEmail(candidate.getEmail());
			return candidateRepo.save(candidateItem);
		}).orElseGet(() -> candidateRepo.save(candidate));
		
	}
	@Override
	public void candidateDelete(long candidateId) {
		if(!candidateRepo.existsById(candidateId)) {
			throw new ItemNotFoundException("item not found for id");
		}
		candidateRepo.deleteById(candidateId);
	}
	

}
