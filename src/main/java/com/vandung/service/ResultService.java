package com.vandung.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vandung.entity.ExamList;
import com.vandung.entity.Result;

public interface ResultService {

	public List<Result> getAllByExamination();
	public Boolean update(Result db);
	public Boolean createExcelCollectTestList(List<ExamList> entity, ServletContext context, HttpServletRequest request, HttpServletResponse response);
	public Boolean createExcelResultList(List<Result> entity, ServletContext context, HttpServletRequest request, HttpServletResponse response);
}
