package com.icoding.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.icoding.domain.BriefType;
import com.icoding.domain.Stock;
import com.icoding.service.BriefTypeService;
import com.icoding.service.StockService;

@Controller
public class ReportController {

	@Autowired
	private BriefTypeService briefTypeService;

	@Autowired
	private StockService stockService;

	@RequestMapping(value = { "/admin/report" }, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("pageName", "Báo cáo");
		model.addAttribute("title", "Báo cáo");
		List<BriefType> listBrieftType = briefTypeService.getAll();
		List<Stock> listStock = stockService.getAll();
		model.addAttribute("listBrieftType", listBrieftType);
		model.addAttribute("listStock", listStock);
		return "report/index";
	}
}
