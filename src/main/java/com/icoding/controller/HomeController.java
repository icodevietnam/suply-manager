package com.icoding.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/admin/home", "/admin" }, method = RequestMethod.GET)
	public String adminHome(Locale locale, Model model) {
		logger.warn("Test xem co vao khong ?");
		model.addAttribute("pageName", "Quản trị người dùng");
		return "home";
	}

	@RequestMapping(value = { "/home", "/" }, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("title", "Tìm Hồ Sơ");
		return "home/index";
	}

}
