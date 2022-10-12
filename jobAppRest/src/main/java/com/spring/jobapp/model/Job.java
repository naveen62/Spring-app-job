package com.spring.jobapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

 import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private long jodId;
	@NotEmpty(message = "title must not be empty")
	private String title;
	 @Schema(name = "jobDesc")
	 @NotNull(message = "provide job description")
	 @Size(min = 5, max = 1000)
	private String jobDesc;
	 @Schema(name = "exp")
	 @NotNull(message = "provide experience requird")
	private int exp;
	 @Schema(name = "jobRole")
	 @NotNull(message = "provide job role")	
	private String jobRole;
	@JsonManagedReference
	@OneToMany(mappedBy = "job")
	@Cascade(CascadeType.DELETE)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)	
	private List<Candidate> candidates;
	
	
	public Job() {
		super();
	}
	
	public Job(long jodId, String title, String jobDesc, int exp, String jobRole,List<Candidate> candidate) {
		super();
		this.jodId = jodId;
		this.title = title;
		this.jobDesc = jobDesc;
		this.exp = exp;
		this.jobRole = jobRole;
		this.candidates = candidate;
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
}
