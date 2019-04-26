package com.vandung.repository;

import java.util.List;

import com.vandung.entity.Result;

public interface ResultRepository {

	public List<Result> getAllByExamination(Integer id);
	public Boolean update(Result entity);
}
