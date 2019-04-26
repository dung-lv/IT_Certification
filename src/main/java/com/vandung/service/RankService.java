package com.vandung.service;

import java.util.List;

import com.vandung.entity.Ranks;

public interface RankService {

	public List<Ranks> getAll();
	public Ranks getRankById(Integer id);
	public Ranks getRankByName(String name);
}
