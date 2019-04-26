package com.vandung.repository;

import java.util.List;

import com.vandung.entity.Member;

public interface MemberRepository {
	
	public List<Member> getAll();
	public List<Member> getPagination(Integer pagination);
	public long getCount();
	public Member getMemberById(Integer id);
	public void add(Member db);
	public Boolean update(Member db);
	public Boolean delete(Integer id);
}
