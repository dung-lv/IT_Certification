package com.vandung.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.entity.Result;

@Repository
@Transactional
public class ResultRepositoryImp implements ResultRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> getAllByExamination(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from " + Result.class.getName() + " r where r.examination.idExamination = :id";
		Query<Result> query = session.createQuery(sql);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public Boolean update(Result entity) {
		Session session = sessionFactory.getCurrentSession();
		Result db = session.find(Result.class, entity.getIdResult());
		{
			db.setSightScores(entity.getSightScores());
			db.setDrillScores(entity.getDrillScores());
			session.update(db);
			return true;
		}
	}
}
