package com.icoding.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.icoding.domain.Department;
import com.icoding.domain.User;
import com.icoding.service.DepartmentService;
import com.icoding.service.UserService;

@Controller
public class ProfileController {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserService userService;

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value = "/admin/profile", method = RequestMethod.GET)
	public String displayPage(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		User currentUser = userService.getUser(userDetails.getUsername());
		List<Department> allDepartments = departmentService.getAll();
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("pageName", "Thông tin người dùng");
		model.addAttribute("title", "Thông tin người dùng");
		model.addAttribute("allDepartments", allDepartments);
		return "profile/index";
	}

	@RequestMapping(value = "admin/profile/change-password", method = RequestMethod.GET)
	public String changePassword(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		User currentUser = userService.getUser(userDetails.getUsername());
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("pageName", "Đổi mật khẩu người dùng");
		model.addAttribute("title", "Thông tin người dùng");
		return "profile/changePassword";
	}

	@RequestMapping(value = "admin/profile/change-password", method = RequestMethod.POST)
	public String changePassword(
			@RequestParam(value = "oldpassword") String oldPassword,
			@RequestParam(value = "newpassword") String newPassword, Model model,HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		User currentUser = userService.getUser(userDetails.getUsername());
		currentUser.setPassword(encoder.encode(newPassword));
		userService.saveOrUpdate(currentUser);
		model.addAttribute("success", "Bạn đã đổi mật khẩu thành công");
		return "profile/changePassword";
	}

	@RequestMapping(value = "admin/profile/updateProfile", method = RequestMethod.POST)
	public String updateProfile(@RequestParam(value = "id") String id,
			@RequestParam(value = "fullname") String fullName,
			@RequestParam(value = "birthdate") String birthDate,
			@RequestParam(value = "address") String address,
			@RequestParam(value = "phone") String phone,
			@RequestParam(value = "gender") String gender,
			@RequestParam(value = "state") String state,
			@RequestParam(value = "department") String department)
			throws ParseException {

		Integer userId = Integer.parseInt(id);
		User user = userService.getUser(userId);
		user.setAddress(address);
		user.setBirthDate(birthDate);
		user.setFullName(fullName);
		user.setPhone(phone);
		user.setState(state);
		if (gender.equalsIgnoreCase("true"))
			user.setGender(true);
		else
			user.setGender(false);
		Integer departmentId = Integer.parseInt(department);
		Department departmentChange = departmentService
				.getDepartment(departmentId);
		user.setDepartment(departmentChange);
		// Save Or Update user
		userService.saveOrUpdate(user);
		// da update thong tin
		return "redirect:/admin/profile";
	}
}
