package com.spring.jobappclient.model;

import java.util.ArrayList;
import java.util.List;

public class JobList {
	private ArrayList<Job> jobList;

	
	public JobList() {
		super();
	}

	public JobList(ArrayList<Job> jobList) {
		super();
		this.jobList = jobList;
	}

	public ArrayList<Job> getJobList() {
		return jobList;
	}

	public void setJobList(ArrayList<Job> jobList) {
		this.jobList = jobList;
	}
	
	
}
