package com.vandung.repository;

import java.util.List;

import com.vandung.entity.Certificate;

public interface CertificateRepository {

	public List<Certificate> getAllByExamination(Integer id);
	public Boolean update(Certificate entity);
}
