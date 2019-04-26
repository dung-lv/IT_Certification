package com.vandung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vandung.entity.Demand;
import com.vandung.repository.DemandRepository;

@Service
public class DemandServiceImp implements DemandService {

	@Autowired
	private DemandRepository demandRepository;
	
	@Override
	public Demand findDemand(String object, String formal, String level) {
		Demand demand = demandRepository.findDemand(object, formal, level);
		return demand;
	}

	@Override
	public List<Demand> getAll() {
		return demandRepository.getAll();
	}

	@Override
	public void add(Demand db) {
		demandRepository.add(db);
	}

	@Override
	public Boolean update(Demand db) {
		return demandRepository.update(db);
	}

	@Override
	public Boolean delete(Integer id) {
		return demandRepository.delete(id);
	}

}
