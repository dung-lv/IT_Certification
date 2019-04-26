package com.vandung.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.entity.Ranks;

@Repository
@Transactional
public class RankRepositoryImp implements RankRepository{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ranks> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from " + Ranks.class.getName();
		Query<Ranks> query = session.createQuery(sql);
		return query.getResultList();
	}

	@Override
	public Ranks getRankById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(Ranks.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Ranks getRankByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from " + Ranks.class.getName() + " r where r.nameRank = :name";
		Query<Ranks> query = session.createQuery(sql);
		query.setParameter("name", name);
		return query.getSingleResult();
	}

}
