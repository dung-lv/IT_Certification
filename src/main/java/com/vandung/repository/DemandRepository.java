package com.vandung.repository;

import java.util.List;

import com.vandung.entity.Demand;

public interface DemandRepository {
	
	public List<Demand> getAll();
	public void add(Demand db);
	public Boolean update(Demand db);
	public Boolean delete(Integer id);
	public Demand findDemand(String object, String formal, String level);
}
