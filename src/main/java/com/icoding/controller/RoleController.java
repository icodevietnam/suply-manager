package com.icoding.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icoding.domain.Role;
import com.icoding.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = { "/admin/role", "/admin/role/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@Secured("ROLE_ADMIN")
	public String displayPage(Model model) {
		model.addAttribute("pageName", "Quản lý quyền");
		model.addAttribute("title", "Quản lý quyền");
		return "role/index";
	}

	@RequestMapping(value = "/role/getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Role> getAll(Model model) {
		List<Role> listRoles = new ArrayList<Role>();
		listRoles = roleService.getAll();
		return listRoles;
	}

	@RequestMapping(value = "/role/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteRole(@RequestParam(value = "itemId") String itemId) {
		Integer id = Integer.parseInt(itemId);
		Role role = roleService.getRole(id);
		if (role.getUserRoles().size() == 0) {
			roleService.delete(role);
			return "true";
		}
		return "false";
	}

	@RequestMapping(value = "/role/new", method = RequestMethod.POST)
	@ResponseBody
	public String addrole(@RequestParam(value = "roleName") String roleName,@RequestParam(value="roleDescription") String roleDescription) {
		Role role = new Role();
		role.setName(roleName);
		role.setDescription(roleDescription);
		try {
			roleService.saveOrUpdate(role);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}
	
	@RequestMapping(value = "/role/update", method = RequestMethod.POST)
	@ResponseBody
	public String updaterole(@RequestParam(value = "roleId") String roleId,
			@RequestParam(value = "roleName") String roleName,@RequestParam(value = "roleDescription") String roleDescription) {
		Role role = roleService.getRole(Integer.parseInt(roleId));
		role.setName(roleName);
		role.setDescription(roleDescription);
		try {
			roleService.saveOrUpdate(role);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/role/get", method = RequestMethod.GET)
	@ResponseBody
	public Role getrole(@RequestParam(value = "itemId") String idemId) {
		Role role = roleService.getRole(Integer.parseInt(idemId));
		return role;
	}

}
