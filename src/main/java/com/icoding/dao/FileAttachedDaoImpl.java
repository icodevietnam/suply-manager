package com.icoding.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.domain.FileAttached;

@Repository
public class FileAttachedDaoImpl implements FileAttachedDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public FileAttached getFileAttached(int id) {
		List<FileAttached> fileAttachedLists = new ArrayList<FileAttached>();
		Query query = getCurrentSession().createQuery(
				"from FileAttached d where d.id = :id");
		query.setParameter("id", id);
		fileAttachedLists = query.list();
		if (fileAttachedLists.size() > 0)
			return fileAttachedLists.get(0);
		else
			return null;
	}

	@Override
	public List<FileAttached> getAll() {
		List<FileAttached> listFileAttacheds = new ArrayList<FileAttached>();
		listFileAttacheds = getCurrentSession().createCriteria(
				FileAttached.class).list();
		return listFileAttacheds;
	}

	@Override
	public void saveOrUpdate(FileAttached fileAttached) {
		getCurrentSession().saveOrUpdate(fileAttached);

	}

	@Override
	public void delete(FileAttached fileAttached) {
		getCurrentSession().delete(fileAttached);
	}

	@Override
	public void update(FileAttached fileAttached) {
		getCurrentSession().update(fileAttached);
	}

}
