package com.spring.jobapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.jobapp.model.Candidate;

@Repository
public interface ICandidateRepo extends JpaRepository<Candidate, Long> {
	
}
