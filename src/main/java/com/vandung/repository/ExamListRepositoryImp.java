package com.vandung.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.entity.ExamList;

@Repository
@Transactional
public class ExamListRepositoryImp implements ExamListRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExamList> getListByConfirm() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from " + ExamList.class.getName() + " e where e.confirm = 1";
		Query<ExamList> query = session.createQuery(sql);
		return query.getResultList();
	}

	@Override
	public void add(ExamList db) {
		Session session = sessionFactory.getCurrentSession();
		session.save(db);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExamList> getListByNotConfirm() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from " + ExamList.class.getName() + " e where e.confirm = 0";
		Query<ExamList> query = session.createQuery(sql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateCandidateConfirm(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from " + ExamList.class.getName() + " e where e.candidate.idCandidate = :id";
		Query<ExamList> query = session.createQuery(sql);
		query.setParameter("id", id);
		ExamList entity = query.getSingleResult();
		if(entity != null) {
			entity.setConfirm(true);
			session.update(entity);
		}
	}
}
