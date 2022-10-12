package com.spring.jobapp.restController;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.jobapp.model.Candidate;
import com.spring.jobapp.model.ErrorModel;
import com.spring.jobapp.model.Job;
import com.spring.jobapp.service.CandidateService;
import com.spring.jobapp.service.JobService;
import com.spring.jobapp.service.StorageService;

 import io.swagger.v3.oas.annotations.Operation;
 import io.swagger.v3.oas.annotations.media.ArraySchema;
 import io.swagger.v3.oas.annotations.media.Content;
 import io.swagger.v3.oas.annotations.media.Schema;
 import io.swagger.v3.oas.annotations.responses.ApiResponse;
 import io.swagger.v3.oas.annotations.responses.ApiResponses;
 import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "Job REST API", description = "API for Job Management System")
public class JobRestController {
	
	@Autowired
	JobService service;
	@Autowired
	CandidateService candidateService;
	@Autowired
	StorageService storageService;
	
	@PostMapping("/jobs")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Creation of job")
	@ApiResponse(responseCode = "201",description = "job created successfully",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Job.class))})
	@ApiResponse(responseCode = "400",description = "Missing or invalid request body",
	content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))})
	public Job createJob(@Valid @RequestBody Job job) {
		return service.createJob(job);
	}
	
	@GetMapping("/jobs")
	@Operation(summary = "Get all job")
	@ApiResponse(responseCode = "200",description = "getting all jobs from database",
		content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Job.class)))})
	@ResponseStatus(code = HttpStatus.OK)
	public List<Job> getAllJobs() {
		return service.getAll();
	}
	
	@GetMapping("/jobs/{job-id}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Get job by its ID")
	@ApiResponse(responseCode = "200",description = "Getting Job by its own id",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Job.class))})
	@ApiResponse(responseCode = "404",description = "Schema not found",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))})
	public Job getJob(@PathVariable("job-id") long jobId) {
		return service.getJob(jobId);
	}
	@PutMapping("/jobs/{job-id}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "update the job")
	@ApiResponse(responseCode = "200",description = "job updated using id",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Job.class))})
	@ApiResponse(responseCode = "400",description = "Missing or invalid request body",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))})
	@ApiResponse(responseCode = "404",description = "Schema not found",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))})
	public Job updateJob(@PathVariable("job-id") long jobId, @RequestBody Job job) {
		return service.updateJob(job, jobId);
	}
	@DeleteMapping("/jobs/{job-id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "delete the job")
	@ApiResponse(responseCode = "204",description = "job deleted using id")
	@ApiResponse(responseCode = "404",description = "Schema not found",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))})
	public boolean deleteJob(@PathVariable("job-id") long jodId) {
		service.deleteJob(jodId);
		return true;
	}
	
	@PostMapping("/candidate/jobs/{job-id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "create candidate for a job")
	@ApiResponse(responseCode = "200",description = "candidate created for job specified",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Candidate.class))})
	@ApiResponse(responseCode = "404",description = "Schema not found",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))})
	public Candidate addCandidate(
	@PathVariable("job-id") long jobId, 
	@Valid @ModelAttribute Candidate candidate,
	@RequestPart("profile") MultipartFile profile,
	@RequestPart("file") MultipartFile file) throws IOException {
		Candidate savedCandidate = candidateService.saveCandidate(jobId,candidate);
		candidateService.saveCandidatePic(savedCandidate.getCandidateId(), profile);
		candidateService.saveCandidateResume(candidate.getCandidateId(), file);
		return savedCandidate;
	}
	
	@Operation(summary = "upload resume for candidate")
	@ApiResponse(responseCode = "200",description = "candidate resume uploaded to server",
		content = {@Content(mediaType = "application/pdf")})
	@ApiResponse(responseCode = "404",description = "Schema not found",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))})
	@ApiResponse(responseCode = "400",description = "Invalid or missing file",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))})
	@PostMapping(path = "/candidate/resume/{candidate-id}", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public void addCandidateResume(@PathVariable("candidate-id") long candidateId ,@RequestParam("file") MultipartFile file) {
		candidateService.saveCandidateResume(candidateId, file);
	}
	
	@Operation(summary = "Get resume for candidate")
	@ApiResponse(responseCode = "200",description = "fetched candidate resume",
		content = {@Content(mediaType = "image/*")})
	@ApiResponse(responseCode = "404",description = "Schema not found",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))})
	@GetMapping("/candidate/resume/{candidate-id}")
	public ResponseEntity<Resource> sendResume(@PathVariable("candidate-id") String candidateId)  {
		Resource resume = storageService.load(candidateId);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, "application/pdf")
				.body(resume);
	}
	
	@Operation(summary = "upload profile picture for candidate")
	@ApiResponse(responseCode = "200",description = "candidate profile picture uploaded to server")
	@ApiResponse(responseCode = "404",description = "Schema not found",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))})
	@ApiResponse(responseCode = "400",description = "Invalid or missing file",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))})
	@PostMapping("/candidate/pic/{candidate-id}")
	public void addCandidatePic(@PathVariable("candidate-id") long candidateId, @RequestParam("profile") MultipartFile picFile) {
		try {
			candidateService.saveCandidatePic(candidateId, picFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Operation(summary = "Get profile picture for candidate")
	@ApiResponse(responseCode = "200",description = "fetched candidate profile picture",
		content = {@Content(mediaType = "image/*")})
	@ApiResponse(responseCode = "404",description = "Schema not found",
		content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))})
	@GetMapping("/candidate/pic/{candidate-id}")
	public ResponseEntity<byte[]> sendPic(@PathVariable("candidate-id") long candidateId) {
		Candidate candidate = candidateService.getCandidate(candidateId);
		return ResponseEntity.ok()
			.header(HttpHeaders.CONTENT_TYPE, candidate.getPicType())
			.body(candidate.getPic());
	}
	
	@GetMapping("/search/jobs/{search-query}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "search job")
	@ApiResponse(responseCode = "200",description = "list of jobs searched",
		content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Job.class)))})
	public List<Job> searchJob(@PathVariable("search-query") String search) {
		
		return service.searchByTitleAndRole(search);
	}
	
	@PutMapping("/candidate/{candidate-id}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "update candidate")
	public Candidate updateCandidate(@PathVariable("candidate-id") long candidateId,
			@RequestBody Candidate candidate) {
		return candidateService.candidateUpdate(candidateId, candidate);
	}
	
	@DeleteMapping("/candidate/{candidate-id}")
	public void deleteCandidate(@PathVariable("candidate-id") long candidateId) {
		candidateService.candidateDelete(candidateId);
	}
	
	@GetMapping("/candidate/{candidate-id}")
	public Candidate getCandidate(@PathVariable("candidate-id") long candidateId) {
		return candidateService.getCandidate(candidateId);
	}
	
	
}
