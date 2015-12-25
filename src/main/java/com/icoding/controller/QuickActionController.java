package com.icoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuickActionController {
	
	@RequestMapping(value = { "/admin/quickAction", "/admin/quickAction/page" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String displayPage(Model model){
		model.addAttribute("pageName", "Chỉnh sửa nhanh hồ sơ");
		model.addAttribute("title", "Chỉnh sửa nhanh hồ sơ");
		return "quickaction/index";
	}
	
	@RequestMapping(value = {"/showPercentageToday"},method = RequestMethod.GET,produces = "application/json")
	@ResponseBody
	public String showPercentageToday(){
		return "";
	}
}
