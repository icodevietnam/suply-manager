package com.icoding.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.domain.DocumentType;

@Repository
public class DocumentTypeDaoImpl implements DocumentTypeDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentType> getAll() {
		List<DocumentType> listDocumentTypes = new ArrayList<DocumentType>();
		listDocumentTypes = getCurrentSession().createCriteria(DocumentType.class)
				.list();
		return listDocumentTypes;
	}

	@Override
	public DocumentType getDocumentType(int id) {
		List<DocumentType> documentTypeLists = new ArrayList<DocumentType>();
		Query query = getCurrentSession().createQuery(
				"from DocumentType d where d.id = :id");
		query.setParameter("id", id);
		documentTypeLists = query.list();
		if (documentTypeLists.size() > 0)
			return documentTypeLists.get(0);
		else
			return null;
	}

	@Override
	public void saveOrUpdate(DocumentType documentType) {
		try {
			getCurrentSession().saveOrUpdate(documentType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(DocumentType documentType) {
		try {
			getCurrentSession().delete(documentType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(DocumentType documentType) {
		try {
			getCurrentSession().update(documentType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
