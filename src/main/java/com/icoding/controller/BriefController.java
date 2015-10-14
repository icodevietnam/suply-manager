package com.icoding.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icoding.domain.Brief;
import com.icoding.domain.BriefType;
import com.icoding.domain.Customer;
import com.icoding.domain.Department;
import com.icoding.domain.Stock;
import com.icoding.service.BriefService;
import com.icoding.service.BriefTypeService;
import com.icoding.service.CustomerService;
import com.icoding.service.DepartmentService;
import com.icoding.service.StockService;

@Controller
public class BriefController {

	@Autowired
	private BriefService briefService;

	@Autowired
	private BriefTypeService briefTypeService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private StockService stockService;

	@RequestMapping(value = { "/admin/brief", "/admin/brief/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String displayPage(Model model) {
		List<BriefType> listBriefTypes = new ArrayList<BriefType>();
		listBriefTypes = briefTypeService.getAll();
		List<Customer> listCustomers = new ArrayList<Customer>();
		listCustomers = customerService.getAll();
		List<Stock> listStocks = new ArrayList<Stock>();
		listStocks = stockService.getAll();
		List<Department> listDepartments = new ArrayList<Department>();
		listDepartments = departmentService.getAll();
		model.addAttribute("pageName", "Quản lý Hồ Sơ");
		model.addAttribute("title", "Quản lý Hồ Sơ");
		model.addAttribute("listBriefTypes", listBriefTypes);
		model.addAttribute("listCustomers", listCustomers);
		model.addAttribute("listStocks", listStocks);
		model.addAttribute("listDepartments", listDepartments);
		return "brief/index";
	}

	// Response brief as json
	@RequestMapping(value = "/brief/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Brief> getAll(Model model) {
		return briefService.getAll();
	}

	@RequestMapping(value = "/brief/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteBrief(@RequestParam(value = "itemId") String itemId) {
		Integer id = Integer.parseInt(itemId);
		Brief brief = briefService.getBrief(id);
		brief.setBriefType(null);
		brief.setDepartment(null);
		brief.setStock(null);
		brief.setCustomer(null);
		
		//Delete this brief anymore
		
		briefService.delete(brief);
		return "true";
	}

	@RequestMapping(value = "/brief/new", method = RequestMethod.POST)
	@ResponseBody
	public String addBrief(@RequestParam(value = "content") String content,
			@RequestParam(value = "stockId") String stockId,
			@RequestParam(value = "briefTypeId") String briefTypeId,
			@RequestParam(value = "customerId") String customerId,
			@RequestParam(value = "departmentId") String departmentId) {
		Brief brief = new Brief();
		brief.setContent(content);
		brief.setCreateDate(new Date());
		brief.setBriefType(briefTypeService.getBriefType(Integer
				.parseInt(briefTypeId)));
		brief.setStock(stockService.getStock(Integer.parseInt(stockId)));
		brief.setCustomer(customerService.getCustomer(Integer
				.parseInt(customerId)));
		brief.setDepartment(departmentService.getDepartment(Integer
				.parseInt(departmentId)));
		try {
			briefService.saveOrUpdate(brief);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/brief/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateBrief(@RequestParam(value = "briefId") String briefId,
			String content,
			@RequestParam(value = "stockId") String stockId,
			@RequestParam(value = "briefTypeId") String briefTypeId,
			@RequestParam(value = "customerId") String customerId,
			@RequestParam(value = "departmentId") String departmentId) {
		Brief brief = briefService.getBrief(Integer.parseInt(briefId));
		brief.setContent(content);
		brief.setBriefType(briefTypeService.getBriefType(Integer
				.parseInt(briefTypeId)));
		brief.setStock(stockService.getStock(Integer.parseInt(stockId)));
		brief.setCustomer(customerService.getCustomer(Integer
				.parseInt(customerId)));
		brief.setDepartment(departmentService.getDepartment(Integer
				.parseInt(departmentId)));
		try {
			briefService.update(brief);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/brief/get", method = RequestMethod.GET)
	@ResponseBody
	public Brief getBrief(@RequestParam(value = "itemId") String idemId) {
		Brief brief = briefService.getBrief(Integer.parseInt(idemId));
		return brief;
	}
	
	@RequestMapping(value = "/brief/searchCustomer", method = RequestMethod.GET)
	@ResponseBody
	public List<Customer> getCustomer(@RequestParam(value = "name") String name,@RequestParam(value = "email") String email) {
		List<Customer> listCustomers = new ArrayList<Customer>();
		listCustomers = briefService.searchCustomer(name, email);
		return listCustomers;
	}
	
	@RequestMapping(value = "/brief/searchBrief", method = RequestMethod.GET)
	@ResponseBody
	public List<Brief> getBriefNe(@RequestParam(value = "cusId") String cusId) {
		List<Brief> listBriefs = new ArrayList<Brief>();
		listBriefs = briefService.searchBrief(Integer.parseInt(cusId));
		return listBriefs;
	}
}
