package com.icoding.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.dao.ImageDao;
import com.icoding.domain.File;

@Repository
public class ImageDaoImpl implements ImageDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public File getImage(int id) {
		List<File> imageLists = new ArrayList<File>();
		Query query = getCurrentSession().createQuery(
				"from Image d where d.id = :id");
		query.setParameter("id", id);
		imageLists = query.list();
		if (imageLists.size() > 0)
			return imageLists.get(0);
		else
			return null;
	}

	@Override
	public List<File> getAll() {
		List<File> listImages = new ArrayList<File>();
		listImages = getCurrentSession().createCriteria(File.class).list();
		return listImages;
	}

	@Override
	public void saveOrUpdate(File image) {
		getCurrentSession().saveOrUpdate(image);

	}

	@Override
	public void delete(File image) {
		getCurrentSession().delete(image);
	}

	@Override
	public void update(File image) {
		getCurrentSession().update(image);
	}

}
