package com.spring.jobapp.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.spring.jobapp.model.Candidate;
import com.spring.jobapp.model.Job;

public interface ICandidateService {
	public Candidate saveCandidate(long jobId, Candidate candidate);

	public void saveCandidateResume(long candidateId, MultipartFile file);

	public Candidate getCandidate(long candidateId);

	public void saveCandidatePic(long candidateId, MultipartFile picFile) throws IOException;
	
	public Candidate candidateUpdate(long candidateId, Candidate candidate);
	
	public void candidateDelete(long candidateId);

}	
