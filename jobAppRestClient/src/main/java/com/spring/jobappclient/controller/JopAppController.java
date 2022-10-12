package com.spring.jobappclient.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jobappclient.model.Candidate;
import com.spring.jobappclient.model.ErrorModel;
import com.spring.jobappclient.model.Job;
import com.spring.jobappclient.model.JobList;
import com.spring.jobappclient.model.UserModel;
import com.spring.jobappclient.service.JobClientService;

@Controller
public class JopAppController {
	
	@Autowired
	private JobClientService service;
	
	@GetMapping("/")
	public String getAllJobs(@ModelAttribute("success") String msg,Model m) {
		List<Job> jobs = service.getAllJobs();
		Collections.reverse(jobs);
		m.addAttribute("jobs",jobs);
		m.addAttribute("success",msg);
		return "index.jsp";
	}
	@GetMapping("/addjob")
	public String addJobForm(@ModelAttribute("error") String msg, Model m) {
		m.addAttribute("error", msg);
		return "addUserForm.jsp";
	}
	@PostMapping("/addjob")
	public String addJob(@RequestParam("title") String title,
			@RequestParam("jobDesc") String jobDesc,
			@RequestParam("exp") String exp,
			@RequestParam("jobRole") String jobRole,
			RedirectAttributes redirectAttributes) throws JsonMappingException, JsonProcessingException {
		try {
			int expInt = Integer.parseInt(exp);
			Job job = new Job(title, jobDesc, expInt, jobRole);
			service.createJob(job);
			redirectAttributes.addFlashAttribute("success","Successfully Added");
			return "redirect:/";
		} catch (HttpStatusCodeException e) {
			ErrorModel error = new ObjectMapper().findAndRegisterModules().readValue(e.getResponseBodyAsString(), ErrorModel.class);
			redirectAttributes.addFlashAttribute("error", error.getMessage());
			return "redirect:/addjob";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Please add all required fields");
			return "redirect:/addjob";
		}
	}
	@GetMapping("/{job-id}")
	public String viewJob(@PathVariable("job-id") long jobId, Model m) {
		Job job = service.getJob(jobId);
		m.addAttribute("job",job);
		return "showJob.jsp";
	}
	@GetMapping("/delete-job/{jobId}")
	public String deleteJob(@PathVariable("jobId") long jobId, RedirectAttributes ra) {
		service.deleteJob(jobId);
		ra.addFlashAttribute("success","Successfully Deleted");
		return "redirect:/";
	}
	@GetMapping("/{jobId}-update-job")
	public String updateJobForm(@PathVariable("jobId") long jobId, Model m) {
		Job job = service.getJob(jobId);
		m.addAttribute("job",job);
		return "updateJobForm.jsp";
	}
	@PostMapping("/updatejob")
	public String updateJob(@RequestParam("title") String title,
			@RequestParam("jobDesc") String jobDesc,
			@RequestParam("exp") int exp,
			@RequestParam("jobRole") String jobRole,
			@RequestParam("jodId") long jodId,
			RedirectAttributes redirectAttributes) {
		Job job = new Job(title, jobDesc, exp, jobRole);
		service.updateJob(jodId, job);
		redirectAttributes.addFlashAttribute("success", "successfully updated");
		return "redirect:/";
	}
	@PostMapping("/search-job")
	public String searchJob(@RequestParam("search") String search, Model m) {
		List<Job> jobs = service.searchJob(search);
		m.addAttribute("jobs",jobs);
		m.addAttribute("success","");
		m.addAttribute("search",search);
		return "index.jsp";
	}
	
	@GetMapping("add-candidate-{job-id}")
	public String addCandidateForm(@PathVariable("job-id") long jobId,
			@ModelAttribute("error") String msg,
			Model m) {
		m.addAttribute("jobId",jobId);
		m.addAttribute("error", msg);
		return "candidateForm.jsp";
	}
	@PostMapping("add-candidate-{job-id}")
	public String addCandidateForm(@PathVariable("job-id") String jobIdStr,
			RedirectAttributes ra,
			@ModelAttribute Candidate candidate,
			@RequestParam("profile") MultipartFile picFile,
			@RequestParam("file") MultipartFile file) throws IOException {
		try {
			long jobId = Long.parseLong(jobIdStr);
			service.createCandidate(jobId, candidate, picFile, file);
			return "redirect:/" + jobId;
		} catch (HttpStatusCodeException e) {
			ErrorModel error = new ObjectMapper().readValue(e.getResponseBodyAsString(), ErrorModel.class);
			ra.addFlashAttribute("error", error.getMessage());
			return "redirect:/add-candidate-" + jobIdStr;
		}catch (Exception e) {
			ra.addFlashAttribute("error", "Please add all required fields");
			return "redirect:/add-candidate-" + jobIdStr;
		}
	}
	@GetMapping(path =  "/change/{candidate-id}/{job-id}")
	public String changeProfileForm(@PathVariable("candidate-id") long candidateId,@PathVariable("job-id") long  jobId,Model m) {
		m.addAttribute("candidateId",candidateId);
		m.addAttribute("jobId",jobId);
		return "candidateProfileForm.jsp";
	}
	@PostMapping("/change/{candidate-id}/{job-id}")
	public String chnageProfile(@PathVariable("candidate-id") long candidateId,@PathVariable("job-id") long  jobId,@RequestParam("profile") MultipartFile file, HttpServletRequest request) {
		try {
			service.candidateChangeProfile(candidateId, file);
			return "redirect:/" + jobId;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping("/candidate/{candidate-id}/{job-id}")
	public String updateCandidateForm(@PathVariable("candidate-id") long candidateId,
			@PathVariable("job-id") long jobId,
			Model m) {
		Candidate candidate = service.getCandidate(candidateId);
		m.addAttribute("candidate", candidate);
		m.addAttribute("jobId", jobId);
		return "candidateUpdateForm.jsp";
	}
	@PostMapping("/candidate/update/{candidate-id}/{job-id}")
	public String updateCandidate(@PathVariable("candidate-id") long candidateId,
			@PathVariable("job-id") long jobId,
			@ModelAttribute Candidate candidate) {
		service.updateCandidate(candidateId, candidate);
		return "redirect:/" + jobId;
	}
	@GetMapping("/candidate/delete/{candidate-id}/{job-id}")
	public String delCandidate(@PathVariable("candidate-id") long candidateId,
			@PathVariable("job-id") long jobId) {
		service.deleteCandidate(candidateId);
		return "redirect:/" + jobId;
	}
	@GetMapping("/register")
	public String userRegisterForm(@ModelAttribute("auth-err") String msg, Model m) {
		m.addAttribute("auth-err", msg);
		return "register.jsp";
	}
	
	@PostMapping("/register")
	public String userRegister(@ModelAttribute UserModel user) {
		service.userRegister(user);
		return "redirect:/";
	}
	@GetMapping("/login")
	public String userLoginForm(@ModelAttribute("auth-err") String msg, Model m) {
		m.addAttribute("auth-err", msg);
		return "login.jsp";
	}
	@PostMapping("/login")
	public String userLogin(@ModelAttribute UserModel user) {
		try {
			service.userLogin(user);
			return "redirect:/";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}
}
