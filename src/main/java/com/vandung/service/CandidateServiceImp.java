package com.vandung.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vandung.entity.Candidate;
import com.vandung.entity.Examination;
import com.vandung.model.CandidateModel;
import com.vandung.repository.CandidateRepository;

@Service
public class CandidateServiceImp implements CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private ExaminationService examinationService;
	
	@Override
	public List<Candidate> getAll() {
		List<Candidate> candidates = candidateRepository.getAll();
		return candidates;
	}
	@Override
	public void add(CandidateModel model) {
		Candidate db = new Candidate();
		db.setNameCandidate(model.nameCandidate);
		db.setEmail(model.email);
		
		try {

			String pattern = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Date date = sdf.parse(model.dateOfBirth);
			java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
			db.setDateOfBirth(sqlStartDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(model.sex == "1") {
			db.setSex(true);
		}
		else {
			db.setSex(false);
		}
		db.setPhone(model.phone);
		db.setCode(model.code);
		db.setDemand(model.demand);
		Examination exam = examinationService.getExaminationNow();
		db.setExamination(exam);
		candidateRepository.add(db);
	}

}
