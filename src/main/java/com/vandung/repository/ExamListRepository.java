package com.vandung.repository;

import java.util.List;

import com.vandung.entity.ExamList;

public interface ExamListRepository {

	public List<ExamList> getListByConfirm();
	public List<ExamList> getListByNotConfirm();
	public void updateCandidateConfirm(Integer id);
	public void add(ExamList db);
}
