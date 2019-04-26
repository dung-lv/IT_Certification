package com.vandung.repository;

import java.util.List;

import com.vandung.entity.Examination;

public interface ExaminationRepository {

	public List<Examination> getAll();
	public Examination getExaminationNow();
	public void add(Examination entity);
	public void update(Examination entity);
}
