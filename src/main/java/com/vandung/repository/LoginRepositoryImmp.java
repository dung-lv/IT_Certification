package com.vandung.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.entity.Login;

@Repository
@Transactional
public class LoginRepositoryImmp implements LoginRepository{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void add(Login db) {
		Session session = sessionFactory.getCurrentSession();
		session.save(db);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean login(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from " + Login.class.getName() + " e where e.username = :username and e.password = :password";
		Query<Login> query = session.createQuery(sql);
		query.setParameter("username", username);
		query.setParameter("password", password);
		Login db = query.getSingleResult();
		if(db != null) {
			return true;
		}
		return false;
	}
}
