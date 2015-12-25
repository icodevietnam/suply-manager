package com.icoding.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icoding.domain.Brief;
import com.icoding.domain.File;
import com.icoding.service.BriefService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private BriefService briefService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/admin/home", "/admin" }, method = RequestMethod.GET)
	public String adminHome(Locale locale, Model model) {
		model.addAttribute("pageName", "Trang chính");
		model.addAttribute("title", "Trang chính");
		return "home";
	}

	@RequestMapping(value = { "/home", "/" }, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("title", "Tìm Hồ Sơ");
		return "home/index";
	}

	@RequestMapping(value = {"/showDetailBrief"},method = RequestMethod.GET)
	@ResponseBody
	public Brief detailBrief(Model model,@RequestParam(value="briefId") String briefId){
		Brief brief = briefService.getBrief(Integer.parseInt(briefId));
		return brief;
	}
	
	@RequestMapping(value = {"/showImageBrief"},method = RequestMethod.GET)
	@ResponseBody
	public List<File> detailImageBrief(Model model,@RequestParam(value="briefId") String briefId){
		Brief brief = briefService.getBrief(Integer.parseInt(briefId));
		List<File> listFiles = brief.getListImage();
		return listFiles;
	}


}
