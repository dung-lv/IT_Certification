package com.vandung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vandung.entity.Ranks;
import com.vandung.repository.RankRepository;

@Service
public class RankServiceImp implements RankService{

	@Autowired
	private RankRepository rankRepository;
	
	@Override
	public List<Ranks> getAll() {
		return rankRepository.getAll();
	}

	@Override
	public Ranks getRankById(Integer id) {
		return rankRepository.getRankById(id);
	}

	@Override
	public Ranks getRankByName(String name) {
		return rankRepository.getRankByName(name);
	}

}
