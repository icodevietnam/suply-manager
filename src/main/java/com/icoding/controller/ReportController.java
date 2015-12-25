package com.icoding.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportController {
	@RequestMapping(value = { "/admin/report"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("pageName", "Báo cáo");
		model.addAttribute("title", "Báo cáo");
		return "report/index";
	}
}
