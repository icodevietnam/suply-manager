package com.icoding.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.dao.BriefDao;
import com.icoding.domain.Brief;
import com.icoding.domain.Customer;
import com.icoding.dto.BriefNote;
import com.icoding.dto.Graph;

@Repository
public class BriefDaoImpl implements BriefDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Brief getBrief(int id) {
		List<Brief> briefLists = new ArrayList<Brief>();
		Query query = getCurrentSession().createQuery("from Brief d where d.id = :id");
		query.setParameter("id", id);
		briefLists = query.list();
		if (briefLists.size() > 0)
			return briefLists.get(0);
		else
			return null;
	}

	@Override
	public List<Brief> getAll() {
		List<Brief> briefLists = new ArrayList<Brief>();
		Query query = getCurrentSession().createQuery(" from Brief ");
		briefLists = query.list();
		return briefLists;
	}

	@Override
	public void saveOrUpdate(Brief brief) {
		getCurrentSession().saveOrUpdate(brief);

	}

	@Override
	public void delete(Brief brief) {
		getCurrentSession().delete(brief);
	}

	@Override
	public void update(Brief brief) {
		getCurrentSession().update(brief);
	}

	@Override
	public List<Customer> searchCustomer(String code) {
		List<Customer> listCustomers = new ArrayList<Customer>();
		String sql = "";
		String fullCode = "";
		if (!code.equalsIgnoreCase("")) {
			sql = " where c.code LIKE :code ";
			fullCode = "PE" + code;
		}
		Query query = getCurrentSession().createQuery("from Customer c " + sql);
		if (!code.equalsIgnoreCase("")) {
			query.setParameter("code", "%" + fullCode + "%");
		}
		listCustomers = query.list();
		return listCustomers;
	}

	@Override
	public List<Brief> searchBrief(int cusId) {
		List<Brief> listBriefs = new ArrayList<Brief>();
		Query query = getCurrentSession().createSQLQuery(" Select * from Brief b where b.customer_id = :cusId ");
		query.setParameter("cusId", cusId);
		listBriefs = query.list();
		return listBriefs;
	}

	@Override
	public List<Brief> showNoneBorrow() {
		List<Brief> listBriefs = new ArrayList<Brief>();
		Query query = getCurrentSession().createQuery(" from Brief b where b.note is null ");
		listBriefs = query.list();
		return listBriefs;
	}

	@Override
	public List<BriefNote> getListBriefNote() {
		List<BriefNote> listBriefNote = new ArrayList<BriefNote>();
		Query query = getCurrentSession()
				.createSQLQuery(
						" Select b.note as 'noteType' ,count(1) as 'count' " + " from brief b " + " GROUP BY note ")
				.addScalar("noteType").addScalar("count", LongType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(BriefNote.class));
		listBriefNote = query.list();
		return listBriefNote;
	}

	@Override
	public List<Graph> listBriefByDepartment() {
		List<Graph> listGraph = new ArrayList<Graph>();
		Query query = getCurrentSession()
				.createSQLQuery(
						" Select d.name  as 'name', count(b.id) as 'count' " 
						+ " from brief b, department d, note n "	
						+ " where b.note = n.code AND n.department = d.id AND b.note IS NOT NULL "
						+ " GROUP BY d.name "
						)
				.addScalar("name").addScalar("count", LongType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(Graph.class));
		listGraph = query.list();
		return listGraph;
	}

	@Override
	public List<Graph> listGraphByStock() {
		List<Graph> listGraph = new ArrayList<Graph>();
		Query query = getCurrentSession()
				.createSQLQuery(
						" Select s.name  as 'name', count(b.id) as 'count' " 
						+ " from stock s, brief b "	
						+ " where b.stock_id = s.id  "
						+ " GROUP BY s.name "
						)
				.addScalar("name").addScalar("count", LongType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(Graph.class));
		listGraph = query.list();
		return listGraph;
	}

	@Override
	public List<Graph> listBriefFromTo(Date fromDate, Date toDate) {
		return null;
	}

}
