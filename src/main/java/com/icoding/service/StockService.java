package com.icoding.service;

import java.util.List;

import com.icoding.domain.Stock;

public interface StockService {
	
	List<Stock> getAll();
	Stock getStock(int id);
	void saveOrUpdate(Stock stock);
	void delete(Stock stock);
	
	void update(Stock stock);

}
