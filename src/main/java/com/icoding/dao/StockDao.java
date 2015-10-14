package com.icoding.dao;

import java.util.List;

import com.icoding.domain.Stock;

public interface StockDao {

	Stock getStock(int id);

	List<Stock> getAll();

	void saveOrUpdate(Stock stock);

	void delete(Stock stock);

	void update(Stock stock);

}
