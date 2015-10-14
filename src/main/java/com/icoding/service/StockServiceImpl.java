package com.icoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icoding.dao.StockDao;
import com.icoding.domain.Stock;

@Service
@Transactional
public class StockServiceImpl implements StockService {

	@Autowired
	private StockDao stockDao;

	@Override
	public List<Stock> getAll() {
		return stockDao.getAll();
	}

	@Override
	public Stock getStock(int id) {
		return stockDao.getStock(id);
	}

	@Override
	public void saveOrUpdate(Stock stock) {
		stockDao.saveOrUpdate(stock);
	}

	@Override
	public void delete(Stock stock) {
		stockDao.delete(stock);
	}

	@Override
	public void update(Stock stock) {
		stockDao.update(stock);
	}

}
