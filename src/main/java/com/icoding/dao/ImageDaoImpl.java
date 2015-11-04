package com.icoding.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.domain.Media;

@Repository
public class ImageDaoImpl implements ImageDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Media getImage(int id) {
		List<Media> imageLists = new ArrayList<Media>();
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
	public List<Media> getAll() {
		List<Media> listImages = new ArrayList<Media>();
		listImages = getCurrentSession().createCriteria(Media.class).list();
		return listImages;
	}

	@Override
	public void saveOrUpdate(Media image) {
		getCurrentSession().saveOrUpdate(image);

	}

	@Override
	public void delete(Media image) {
		getCurrentSession().delete(image);
	}

	@Override
	public void update(Media image) {
		getCurrentSession().update(image);
	}

}
