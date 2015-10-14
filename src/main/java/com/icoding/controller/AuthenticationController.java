package com.icoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showPage(Model model) {
		model.addAttribute("title", "Đăng nhập quản lý");
		return "login/login";
	}

	// Error : Authentication Error
	@RequestMapping(value = "/error1", method = RequestMethod.GET)
	public String authenticateError(Model model) {
		model.addAttribute("error1", "Có lỗi xảy ra khi đăng nhập !!!");
		return "login/login";
	}
	
	@RequestMapping(value="/accessDenied",method =RequestMethod.GET )
	public String showAccessDeniedPage(Model model) {
		model.addAttribute("title", "Không có quyền truy cập");
		model.addAttribute("errorNumber", "403");
		model.addAttribute("errorName", "Không vào được.");
		model.addAttribute("message", "Bạn không có quyền truy cập");
		return "error";
	}
}
