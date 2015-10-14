package com.icoding.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.domain.Document;
import com.icoding.domain.Document;

@Repository
public class DocumentDaoImpl implements DocumentDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Document getDocument(int id) {
		List<Document> documentLists = new ArrayList<Document>();
		Query query = getCurrentSession().createQuery(
				"from Document d where d.id = :id");
		query.setParameter("id", id);
		documentLists = query.list();
		if (documentLists.size() > 0)
			return documentLists.get(0);
		else
			return null;
	}

	@Override
	public List<Document> getAll() {
		List<Document> listDocuments = new ArrayList<Document>();
		listDocuments = getCurrentSession().createCriteria(Document.class)
				.list();
		return listDocuments;
	}

	@Override
	public void saveOrUpdate(Document document) {
		getCurrentSession().saveOrUpdate(document);

	}

	@Override
	public void delete(Document document) {
		getCurrentSession().delete(document);
	}

	@Override
	public void update(Document document) {
		getCurrentSession().update(document);
	}

}
