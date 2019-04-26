package com.vandung.service;

import java.util.List;

import com.vandung.entity.Member;

public interface MemberService {

	public List<Member> getAll();
	public List<Member> getPagination(Integer pagination);
	public long getCount();
	public Member getMemberById(Integer id);
	public void add(Member model);
	public Boolean update(Member model);
	public Boolean delete(Integer id);
}
