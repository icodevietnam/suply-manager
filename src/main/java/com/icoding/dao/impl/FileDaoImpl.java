package com.icoding.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.dao.FileDao;
import com.icoding.domain.File;

@Repository
public class FileDaoImpl implements FileDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public File getFile(int id) {
		List<File> fileLists = new ArrayList<File>();
		Query query = getCurrentSession().createQuery(
				"from File d where d.id = :id");
		query.setParameter("id", id);
		fileLists = query.list();
		if (fileLists.size() > 0)
			return fileLists.get(0);
		else
			return null;
	}

	@Override
	public List<File> getAll() {
		List<File> listFiles = new ArrayList<File>();
		listFiles = getCurrentSession().createCriteria(
				File.class).list();
		return listFiles;
	}

	@Override
	public void saveOrUpdate(File file) {
		getCurrentSession().saveOrUpdate(file);

	}

	@Override
	public void delete(File file) {
		getCurrentSession().delete(file);
	}

	@Override
	public void update(File file) {
		getCurrentSession().update(file);
	}

}
