package com.icoding.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icoding.domain.Stock;
import com.icoding.domain.Stock;

@Repository
public class StockDaoImpl implements StockDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Stock getStock(int id) {
		List<Stock> stockLists = new ArrayList<Stock>();
		Query query = getCurrentSession().createQuery(
				"from Stock d where d.id = :id");
		query.setParameter("id", id);
		stockLists = query.list();
		if (stockLists.size() > 0)
			return stockLists.get(0);
		else
			return null;
	}

	@Override
	public List<Stock> getAll() {
		List<Stock> listStocks = new ArrayList<Stock>();
		listStocks = getCurrentSession().createCriteria(Stock.class)
				.list();
		return listStocks;
	}

	@Override
	public void saveOrUpdate(Stock stock) {
		getCurrentSession().saveOrUpdate(stock);

	}

	@Override
	public void delete(Stock stock) {
		getCurrentSession().delete(stock);
	}

	@Override
	public void update(Stock stock) {
		getCurrentSession().update(stock);
	}

}
