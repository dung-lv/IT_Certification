package com.vandung.service;

import java.util.List;

import com.vandung.entity.Candidate;
import com.vandung.model.CandidateModel;

public interface CandidateService {

	public List<Candidate> getAll();
	public void add(CandidateModel model);
}
