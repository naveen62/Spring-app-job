package com.spring.jobappclient.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.spring.jobappclient.model.Candidate;
import com.spring.jobappclient.model.Job;
import com.spring.jobappclient.model.JobList;
import com.spring.jobappclient.model.JwtResponse;
import com.spring.jobappclient.model.UserModel;

@Service
public class JobClientService {
	
	private String jobApiUrl = "http://localhost:8080/jobs";
	
	@Autowired
	RestTemplate rt;
	
	private String token;
	
	public Job createJob(Job job) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+this.getToken());
		HttpEntity<Job> jobEntity = new HttpEntity<>(job, headers);
		return rt.postForObject(jobApiUrl, jobEntity, Job.class);
	}
	public ResponseEntity<String> createCandidate(long jobId, Candidate candidate, MultipartFile picFile, MultipartFile file) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+this.getToken());
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("profile", picFile.getResource());
		body.add("file", file.getResource());
		body.add("name", candidate.getName());
		body.add("email", candidate.getEmail());
		body.add("contactNo", candidate.getContactNo());
		body.add("education", candidate.getEducation());
		HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<>(body,headers);
		ResponseEntity<String> response = rt.exchange("http://localhost:8080/candidate/jobs/"+jobId,HttpMethod.POST, requestEntity, String.class);
		return response;		
	}
	public void candidateChangeProfile(long candidateId, MultipartFile file) throws IOException {
		HttpHeaders parts = new HttpHeaders();
		parts.add("Content-Type", file.getContentType());
		HttpEntity<ByteArrayResource> partsEntity = new HttpEntity<>(new ByteArrayResource(file.getBytes()){
			@Override
			public String getFilename() {
				return file.getOriginalFilename();
			}
		},parts);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.set("Authorization", "Bearer "+this.getToken());
		MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<>();
		requestMap.add("profile", partsEntity);
		HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<>(requestMap,headers);
		rt.exchange("http://localhost:8080/candidate/pic/" + candidateId,HttpMethod.POST, requestEntity, String.class);
	}
	public List<Job> getAllJobs() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+this.getToken());
		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<Job[]> arrayJobs = rt.exchange(jobApiUrl, HttpMethod.GET, requestEntity, Job[].class);
//		Job[] arrayJobs = rt.getForObject(jobApiUrl, Job[].class);
		List<Job> jobs = Arrays.asList(arrayJobs.getBody());
		return jobs;
	}
	public Job getJob(long jobId) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+this.getToken());
		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		Job job = rt.exchange(jobApiUrl+"/"+jobId, HttpMethod.GET, requestEntity, Job.class).getBody();
//		Job job = rt.getForObject(jobApiUrl+"/"+jobId, Job.class);
		return job;
	}
	public void deleteJob(long jobId) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+this.getToken());
		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		rt.exchange(jobApiUrl+"/"+jobId, HttpMethod.DELETE, requestEntity, Void.class);
//		rt.delete(jobApiUrl+"/"+jobId);
	}
	public Job updateJob(long jobId,Job job) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+this.getToken());
//		HttpEntity<Job> jobEntity = new HttpEntity<>(job, headers);
		HttpEntity<Job> entity = new HttpEntity<Job>(job,headers);
		Job updatedJob = rt.exchange(jobApiUrl+"/"+jobId, HttpMethod.PUT, entity, Job.class).getBody();
		return updatedJob;
	}
	public List<Job> searchJob(String search) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+this.getToken());
		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		String searchApi = "http://localhost:8080/search/jobs/"+ search;
//		Job[] arrayJobs = rt.getForObject(searchApi, Job[].class);
		ResponseEntity<Job[]> arrayJobs = rt.exchange(searchApi, HttpMethod.GET, requestEntity, Job[].class);
		return Arrays.asList(arrayJobs.getBody());
	}
	public Candidate updateCandidate(long candidateId, Candidate candidate) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+this.getToken());
		String candidateUpdateApi = "http://localhost:8080/candidate/" + candidateId;
		HttpEntity<Candidate> entity = new HttpEntity<Candidate>(candidate,headers);
		return rt.exchange(candidateUpdateApi, HttpMethod.PUT, entity, Candidate.class).getBody();
	}
	public void deleteCandidate(long candidateId) {
		String candidateDelApi = "http://localhost:8080/candidate/" + candidateId;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+this.getToken());
		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		rt.exchange(candidateDelApi, HttpMethod.DELETE, requestEntity, Void.class);
	}
	public Candidate getCandidate(long candidateId) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+this.getToken());
		HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
		String candidateGetApi = "http://localhost:8080/candidate/" + candidateId;
		Candidate candidate = rt.exchange(candidateGetApi, HttpMethod.GET, requestEntity, Candidate.class).getBody();
		return candidate;
//		return rt.getForObject(candidateGetApi, Candidate.class);
	}
	public JwtResponse userRegister(UserModel user) {
		JwtResponse res = rt.postForObject("http://localhost:8080/register", user, JwtResponse.class);
		this.setToken(res.getJwttoken());
		return res;
	}
	public JwtResponse userLogin(UserModel user) {
		JwtResponse res = rt.postForObject("http://localhost:8080/login", user, JwtResponse.class);
		this.setToken(res.getJwttoken());
		return res;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
