package com.vandung.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.entity.Certificate;

@Repository
@Transactional
public class CertificateRepositoryImp implements CertificateRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Certificate> getAllByExamination(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from " + Certificate.class.getName() + " r where r.examination.idExamination = :id";
		Query<Certificate> query = session.createQuery(sql);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public Boolean update(Certificate entity) {
		Session session = sessionFactory.getCurrentSession();
		Certificate db = session.find(Certificate.class, entity.getIdCertificate());
		{
			db.setDiplomaNumber(entity.getDiplomaNumber());
			db.setNotebookNumber(entity.getNotebookNumber());
			session.update(db);
			return true;
		}
	}
}
