package com.vandung.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.entity.Member;

@Repository
@Transactional
public class MemberRepositoryImmp implements MemberRepository{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from m.nameMember, m.rank.nameRank" + Member.class.getName() + " m";
		Query<Member> query = session.createQuery(sql);
		return query.getResultList();
	}

	@Override
	public void add(Member db) {
		Session session = sessionFactory.getCurrentSession();
		session.save(db);
	}

	@Override
	public Boolean update(Member entity) {
		Session session = sessionFactory.getCurrentSession();
		Member db = session.find(Member.class, entity.getIdMember());
		if(db != null) {
			db.setNameMember(entity.getNameMember());
			db.setRank(entity.getRank());
			db.setWorkUnit(entity.getWorkUnit());
			db.setNote(entity.getNote());
			db.setIsUse(entity.getIsUse());
			session.update(db);
			return true;
		}
		return false;
	}

	@Override
	public Boolean delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Member db = session.find(Member.class, id);
		if(db != null) {
			session.delete(db);
			return true;
		}
		return false;
	}

	@Override
	public Member getMemberById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(Member.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getPagination(Integer pagination) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from " + Member.class.getName();
		Query<Member> query = session.createQuery(sql);
		query.setFirstResult((pagination-1)*10);
        query.setMaxResults(10);
		return query.getResultList();
	}

	@Override
	public long getCount() {
		Session session = sessionFactory.getCurrentSession();
		return (long) session.createQuery("select count(*) from " + Member.class.getName()).uniqueResult();
	}
}
