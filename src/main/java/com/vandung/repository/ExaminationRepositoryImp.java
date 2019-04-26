package com.vandung.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.entity.Examination;
import com.vandung.entity.Member;

@Repository
@Transactional
public class ExaminationRepositoryImp implements ExaminationRepository{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Examination> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from " + Examination.class.getName() + " e ORDER BY e.dateExam desc";
		Query<Examination> query = session.createQuery(sql);
		return query.getResultList();
	}

	@Override
	public void add(Examination entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Examination getExaminationNow() {
		Session session = sessionFactory.getCurrentSession();
		String sql1 = "from " + Examination.class.getName() + " e ORDER BY e.dateExam desc";
		Query<Examination> query = session.createQuery(sql1);
        query.setFirstResult(0);
        query.setMaxResults(1);
        Examination db = new Examination();
        try {
			db = query.getSingleResult();
			return db;
		} catch (Exception e) {
			return db;
		}
	}

	@Override
	public void update(Examination entity) {
		Session session = sessionFactory.getCurrentSession();
		Examination db = session.find(Examination.class, entity.getIdExamination());
		List<Member> memberEntity = entity.getMembers();
		if(db != null) {
			List<Member> memberDb = db.getMembers();
			memberEntity.forEach((m)->{
				memberDb.add(m);
			});
			db.setDateExam(entity.getDateExam());
			db.setCode(entity.getCode());
			db.setMembers(memberDb);
		}
		session.update(db);
	}
}