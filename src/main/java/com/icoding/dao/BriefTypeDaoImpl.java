package com.icoding.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.domain.BriefType;

@Repository
public class BriefTypeDaoImpl implements BriefTypeDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public BriefType getBriefType(int id) {
		List<BriefType> briefTypeLists = new ArrayList<BriefType>();
		Query query = getCurrentSession().createQuery(
				"from BriefType d where d.id = :id");
		query.setParameter("id", id);
		briefTypeLists = query.list();
		if (briefTypeLists.size() > 0)
			return briefTypeLists.get(0);
		else
			return null;
	}

	@Override
	public List<BriefType> getAll() {
		List<BriefType> listBriefTypes = new ArrayList<BriefType>();
		listBriefTypes = getCurrentSession().createCriteria(BriefType.class)
				.list();
		return listBriefTypes;
	}

	@Override
	public void saveOrUpdate(BriefType briefType) {
		getCurrentSession().saveOrUpdate(briefType);

	}

	@Override
	public void delete(BriefType briefType) {
		getCurrentSession().delete(briefType);
	}

	@Override
	public void update(BriefType briefType) {
		getCurrentSession().update(briefType);
	}

}
