package com.vandung.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vandung.entity.Examination;

public interface ExaminationService {

	public List<Examination> getAll();
	public Examination getExaminationNow();
	public void add(Examination model);
	public void update(Examination model);
//	public Boolean createPdf(Examination model, ServletContext context, HttpServletRequest request, HttpServletResponse response);
	public Boolean createExcel(Examination entity, ServletContext context, HttpServletRequest request, HttpServletResponse response);
}
