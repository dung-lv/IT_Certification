package com.vandung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vandung.entity.Member;
import com.vandung.repository.MemberRepository;

@Service
public class MemberServiceImp implements MemberService{

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public List<Member> getAll() {
		return memberRepository.getAll();
	}

	@Override
	public void add(Member member) {
		memberRepository.add(member);
	}

	@Override
	public Boolean update(Member member) {		
		return memberRepository.update(member);
	}

	@Override
	public Boolean delete(Integer id) {
		return memberRepository.delete(id);
	}

	@Override
	public Member getMemberById(Integer id) {
		return memberRepository.getMemberById(id);
	}
	
	@Override
	public List<Member> getPagination(Integer pagination) {
		return memberRepository.getPagination(pagination);
	}

	@Override
	public long getCount() {
		return memberRepository.getCount();
	}

}
