package com.icoding.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icoding.domain.Department;
import com.icoding.domain.User;
import com.icoding.service.DepartmentService;
import com.icoding.service.UserService;

@Controller
public class DepartmentController {

	@Autowired
	private UserService userService;
	
	@Autowired 
	private DepartmentService departmentService;

	@RequestMapping(value = "/deparment/getCurrentUserDepartment", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getCurrentUserDepartment() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		User currentUser = userService.getUser(userDetails.getUsername());
		return currentUser.getDepartment().getName();
	}

	@RequestMapping(value = { "/admin/department", "/admin/department/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@Secured("ROLE_ADMIN")
	public String displayPage(Model model) {
		model.addAttribute("pageName", "Quản lý phòng ban");
		model.addAttribute("title", "Quản lý phòng ban");
		return "department/index";
	}

	// Response department as json
	@RequestMapping(value = "/department/getAll", method = RequestMethod.GET,produces = "application/json")
	@ResponseBody
	public List<Department> getAll(Model model) {
		List<Department> listDepartments = new ArrayList<Department>();
		listDepartments = departmentService.getAll();
		return listDepartments;
	}
	
	@RequestMapping(value = "/department/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDepartment(@RequestParam(value="itemId") String itemId){
		Integer id = Integer.parseInt(itemId);
		Department department = departmentService.getDepartment(id);
		if(department.getListBrief().size()==0 && department.getListDocumentTypes().size()==0 && department.getListUsers().size() == 0){
			departmentService.delete(department);
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(value = "/department/new", method = RequestMethod.POST)
	@ResponseBody
	public String addDepartment(@RequestParam(value="departmentName") String departmentName){
		Department department = new Department();
		department.setName(departmentName);
		try {
			departmentService.saveOrUpdate(department);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}
	
	@RequestMapping(value = "/department/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateDepartment(@RequestParam(value="departmentId") String departmentId
			,@RequestParam(value="departmentName") String departmentName){
		Department department = departmentService.getDepartment(Integer.parseInt(departmentId));
		department.setName(departmentName);
		try {
			departmentService.update(department);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}
	
	@RequestMapping(value = "/department/get", method = RequestMethod.GET)
	@ResponseBody
	public Department getDepartment(@RequestParam(value="itemId") String idemId){
		Department department = departmentService.getDepartment(Integer.parseInt(idemId));
		return department;
	}

}
