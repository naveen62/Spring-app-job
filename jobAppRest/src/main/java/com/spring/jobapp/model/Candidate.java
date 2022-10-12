package com.spring.jobapp.model;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

 import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private long candidateId;
	@NotEmpty(message = "provide candidate name")
	private String name;
	@Email(message = "provide valid email")
	@NotEmpty(message = "provide candidate email")
	private String email;
	@NotNull(message = "provide candidate contact Number")
	private long contactNo;
	@NotEmpty(message = "provide candidate education")
	private String education;
	private String resurl;
	private String picType;
	@Lob
	@JsonIgnore
	private byte[] pic;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="jod_id")
	private Job job;
	
	public Candidate() {
		super();
	}
	public Candidate(long candidateId, String name, String email, long contactNo, String education,Job job, String resurl, String picType, byte[] pic) {
		super();
		this.candidateId = candidateId;
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.education = education;
		this.resurl = resurl;
		this.job = job;
		this.picType = picType;
		this.pic = pic;
	}
	public long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public String getResurl() {
		return resurl;
	}
	public void setResurl(String resurl) {
		this.resurl = resurl;
	}
	public String getPicType() {
		return picType;
	}
	public void setPicType(String picType) {
		this.picType = picType;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", name=" + name + ", email=" + email + ", contactNo="
				+ contactNo + ", education=" + education + ", resurl=" + resurl + ", picType=" + picType + ", pic="
				+ Arrays.toString(pic) + ", job=" + job + "]";
	}
	
}
