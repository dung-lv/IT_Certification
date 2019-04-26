package com.vandung.repository;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.entity.Demand;

@Repository
@Transactional
public class DemandRepositoryImp implements DemandRepository{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public Demand findDemand(String object, String formal, String level) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from " + Demand.class.getName() + " d where d.object = :object and d.formal = :formal and d.level = :level";
		Query<Demand> query = session.createQuery(sql);
		query.setParameter("object", object);
		query.setParameter("formal", formal);
		query.setParameter("level", level);
		return query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Demand> getAll() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from " + Demand.class.getName();
		Query<Demand> query = session.createQuery(sql);
		return query.getResultList();
	}

	@Override
	public void add(Demand db) {
		Session session = sessionFactory.getCurrentSession();
		session.save(db);
	}

	@Override
	public Boolean update(Demand db) {
		Session session = sessionFactory.getCurrentSession();
		Demand entity = session.find(Demand.class, db.getIdDemand());
		if(entity != null) {
			entity.setObject(db.getObject());
			entity.setLevel(db.getLevel());
			entity.setFormal(db.getFormal());
			entity.setReviewCost(db.getReviewCost());
			entity.setExamCost(db.getExamCost());
			Date sqlDate = new Date(Calendar.getInstance().getTime().getTime());
			entity.setUpdateDate(sqlDate);
			session.update(entity);
			return true;
		}
		return false;
	}

	@Override
	public Boolean delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Demand db = session.find(Demand.class, id);
		if(db != null) {
			session.delete(db);
			return true;
		}
		return false;
	}

}
