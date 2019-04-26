package com.vandung.repository;

import java.util.List;

import com.vandung.entity.Ranks;

public interface RankRepository {

	public List<Ranks> getAll();
	public Ranks getRankById(Integer id);
	public Ranks getRankByName(String name);
}
