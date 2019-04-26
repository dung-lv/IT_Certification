package com.vandung.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vandung.entity.Certificate;

public interface CertificateService {

	public List<Certificate> getAllByExamination();
	public Boolean update(Certificate entity);
	public Boolean createExcel(List<Certificate> entity, ServletContext context, HttpServletRequest request, HttpServletResponse response);
}
