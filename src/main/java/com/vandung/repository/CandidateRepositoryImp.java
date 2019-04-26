package com.vandung.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.entity.Candidate;
import com.vandung.entity.Demand;
import com.vandung.entity.ExamList;

@Repository
@Transactional
public class CandidateRepositoryImp implements CandidateRepository{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Candidate> getAll() {
		String sql = "from " + Candidate.class.getName();
		Session session = sessionFactory.getCurrentSession();
		Query<Candidate> query = session.createQuery(sql);
		List<Candidate> candidates = query.getResultList();
		return candidates;
	}

	@Override
	public void add(Candidate db) {
		Session session = sessionFactory.getCurrentSession();
		Integer id = (Integer) session.save(db);
		Candidate entity = session.find(Candidate.class, id);
		entity.setRegistrationNumber(entity.getNameCandidate().substring(0, 1) + id);
		session.update(entity);
		
		Demand demandDb = db.getDemand();
		ExamList examlistDb = new ExamList();
		examlistDb.setTotalCost((double) (demandDb.getReviewCost() + demandDb.getExamCost()));
		examlistDb.setCandidate(entity);
		examlistDb.setConfirm(false);
		examlistDb.setExamination(entity.getExamination());
		session.save(examlistDb);
	}

}
