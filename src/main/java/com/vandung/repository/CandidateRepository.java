package com.vandung.repository;

import java.util.List;

import com.vandung.entity.Candidate;

public interface CandidateRepository {

	public List<Candidate> getAll();
	public void add(Candidate db);
}
