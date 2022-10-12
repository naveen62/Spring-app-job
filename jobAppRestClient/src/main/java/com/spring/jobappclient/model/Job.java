package com.spring.jobappclient.model;

import java.util.List;

public class Job {
	private long jodId;
	private String title;
	private String jobDesc;
	private int exp;
	private String jobRole;
	private List<Candidate> candidates;
	
	
	public Job() {
		super();
	}
	
	public Job(String title, String jobDesc, int exp, String jobRole, List<Candidate> candidates) {
		super();
		this.title = title;
		this.jobDesc = jobDesc;
		this.exp = exp;
		this.jobRole = jobRole;
		this.candidates = candidates;
	}

	public Job(long jodId, String title, String jobDesc, int exp, String jobRole, List<Candidate> candidates) {
		super();
		this.jodId = jodId;
		this.title = title;
		this.jobDesc = jobDesc;
		this.exp = exp;
		this.jobRole = jobRole;
		this.candidates = candidates;
	}
	public long getJodId() {
		return jodId;
	}
	public void setJodId(long jodId) {
		this.jodId = jodId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getjobDesc() {
		return jobDesc;
	}
	public void setjobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public int getexp() {
		return exp;
	}
	public void setexp(int exp) {
		this.exp = exp;
	}
	public String getjobRole() {
		return jobRole;
	}
	public void setjobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public Job(String title, String jobDesc, int exp, String jobRole) {
		super();
		this.title = title;
		this.jobDesc = jobDesc;
		this.exp = exp;
		this.jobRole = jobRole;
	}
	
	
}
