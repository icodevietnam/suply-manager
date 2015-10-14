package com.icoding.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icoding.domain.Stock;
import com.icoding.service.StockService;

@Controller
public class StockController {

	@Autowired
	private StockService stockService;

	@RequestMapping(value = { "/admin/stock", "/admin/stock/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String displayPage(Model model) {
		model.addAttribute("pageName", "Quản lý Kho");
		model.addAttribute("title", "Quản lý Kho");
		return "stock/index";
	}

	// Response stock as json
	@RequestMapping(value = "/stock/getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Stock> getAll(Model model) {
		List<Stock> listStocks = new ArrayList<Stock>();
		listStocks = stockService.getAll();
		return listStocks;
	}

	@RequestMapping(value = "/stock/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteStock(@RequestParam(value = "itemId") String itemId) {
		Integer id = Integer.parseInt(itemId);
		Stock stock = stockService.getStock(id);
		if (stock.getListBriefs().size() == 0) {
			stockService.delete(stock);
			return "true";
		}
		return "false";
	}

	@RequestMapping(value = "/stock/new", method = RequestMethod.POST)
	@ResponseBody
	public String addStock(@RequestParam(value = "stockName") String stockName,
			@RequestParam(value = "stockPosition") String stockPosition) {
		Stock stock = new Stock();
		stock.setName(stockName);
		stock.setPosition(stockPosition);
		try {
			stockService.saveOrUpdate(stock);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/stock/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateStock(@RequestParam(value = "stockId") String stockId,
			@RequestParam(value = "stockName") String stockName,
			@RequestParam(value = "stockPosition") String stockPosition) {
		Stock stock = stockService.getStock(Integer.parseInt(stockId));
		stock.setName(stockName);
		stock.setPosition(stockPosition);
		try {
			stockService.update(stock);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/stock/get", method = RequestMethod.GET)
	@ResponseBody
	public Stock getStock(@RequestParam(value = "itemId") String idemId) {
		Stock stock = stockService.getStock(Integer.parseInt(idemId));
		return stock;
	}

}
