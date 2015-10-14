package com.icoding.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icoding.domain.Department;
import com.icoding.domain.Role;
import com.icoding.domain.User;
import com.icoding.service.DepartmentService;
import com.icoding.service.RoleService;
import com.icoding.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@RequestMapping(value = "/user/checkPasswordExist", method = RequestMethod.GET)
	@ResponseBody
	public String checkPasswordExist(
			@RequestParam(value = "oldpassword") String oldPassword) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		User currentUser = userService.getUser(userDetails.getUsername());
		if (encoder.matches(oldPassword, currentUser.getPassword()))
			return "false";
		else
			return "true";
	}

	@RequestMapping(value = { "/admin/user", "/admin/user/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@Secured("ROLE_ADMIN")
	public String displayPage(Model model) {
		List<Department> listDepartments = new ArrayList<Department>();
		List<Role> listRoles = new ArrayList<Role>();
		listDepartments = departmentService.getAll();
		listRoles = roleService.getAll();
		model.addAttribute("pageName", "Quản lý Người dùng");
		model.addAttribute("title", "Quản lý Người dùng");
		model.addAttribute("listRoles", listRoles);
		model.addAttribute("listDepartments", listDepartments);
		return "user/index";
	}

	// Response user as json
	@RequestMapping(value = "/user/getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<User> getAll(Model model) {
		List<User> listUsers = new ArrayList<User>();
		listUsers = userService.getAll();
		return listUsers;
	}

	@RequestMapping(value = "/user/new", method = RequestMethod.POST)
	@ResponseBody
	public String addUser(@RequestParam(value = "password") String password,
			@RequestParam(value = "userName") String userName,
			@RequestParam(value = "fullname") String fullname,
			@RequestParam(value = "birthDate") String birthDate,
			@RequestParam(value = "address") String address,
			@RequestParam(value = "roleId") String roleId,
			@RequestParam(value = "phone") String phone,
			@RequestParam(value = "state") String state,
			@RequestParam(value = "gender") String gender,
			@RequestParam(value = "departmentId") String departmentId) {

		User user = new User();
		user.setUsername(userName);
		user.setPassword(encoder.encode(password));
		user.setFullName(fullname);
		user.setBirthDate(birthDate);
		user.setAddress(address);
		user.setRole(roleService.getRole(Integer.parseInt(roleId)));
		user.setState(state);
		user.setDepartment(departmentService.getDepartment(Integer
				.parseInt(departmentId)));
		if (gender.equalsIgnoreCase("true")) {
			user.setGender(true);
		}
		user.setGender(false);
		user.setPhone(phone);
		try {
			userService.saveOrUpdate(user);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUser(@RequestParam(value = "itemId") String itemId) {
		Integer id = Integer.parseInt(itemId);
		User user = userService.getUser(id);
		if (!itemId.equalsIgnoreCase("1")) {
			user.setRole(null);
			userService.delete(user);
			return "true";
		}
		return "false";
	}

	@RequestMapping(value = "/user/get", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@RequestParam(value = "itemId") String idemId) {
		User user = userService.getUser(Integer.parseInt(idemId));
		return user;
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateUser(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "fullname") String fullname,
			@RequestParam(value = "birthDate") String birthDate,
			@RequestParam(value = "address") String address,
			@RequestParam(value = "roleId") String roleId,
			@RequestParam(value = "phone") String phone,
			@RequestParam(value = "state") String state,
			@RequestParam(value = "gender") String gender,
			@RequestParam(value = "departmentId") String departmentId) {
		User user = userService.getUser(Integer.parseInt(userId));
		user.setFullName(fullname);
		user.setBirthDate(birthDate);
		user.setAddress(address);
		user.setRole(roleService.getRole(Integer.parseInt(roleId)));
		user.setState(state);
		user.setDepartment(departmentService.getDepartment(Integer
				.parseInt(departmentId)));
		if (gender.equalsIgnoreCase("true")) {
			user.setGender(true);
		}
		user.setGender(false);
		user.setPhone(phone);
		try {
			userService.update(user);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}
	
	@RequestMapping(value = "/user/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public String updateUser(@RequestParam(value = "userId") String userId,
			@RequestParam(value = "password") String password) {
		User user = userService.getUser(Integer.parseInt(userId));
		user.setPassword(encoder.encode(password));
		try {
			userService.update(user);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}
}
