package com.vandung.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vandung.entity.ExamList;

public interface ExamListService {

	public List<ExamList> getListByConfirm();
	public List<ExamList> getListByNotConfirm();
	public void add(ExamList db);
	public void updateCandidateConfirm(Integer id);
	public Boolean createExcel(List<ExamList> entity, ServletContext context, HttpServletRequest request, HttpServletResponse response);
}
