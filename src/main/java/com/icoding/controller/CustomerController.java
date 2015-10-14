package com.icoding.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icoding.domain.Customer;
import com.icoding.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = { "/admin/customer", "/admin/customer/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String displayPage(Model model) {
		model.addAttribute("pageName", "Quản lý Khách hàng");
		model.addAttribute("title", "Quản lý Khách hàng");
		return "customer/index";
	}

	// Response customer as json
	@RequestMapping(value = "/customer/getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Customer> getAll(Model model) {
		List<Customer> listCustomers = new ArrayList<Customer>();
		listCustomers = customerService.getAll();
		return listCustomers;
	}

	@RequestMapping(value = "/customer/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteCustomer(@RequestParam(value = "itemId") String itemId) {
		Integer id = Integer.parseInt(itemId);
		Customer customer = customerService.getCustomer(id);
		if (customer.getListBriefs().size() == 0) {
			customerService.delete(customer);
			return "true";
		}
		return "false";
	}

	@RequestMapping(value = "/customer/new", method = RequestMethod.POST)
	@ResponseBody
	public String addCustomer(
			@RequestParam(value = "customerName") String customerName,
			@RequestParam(value = "customerBirthDay") String customerBirthDay,
			@RequestParam(value = "customerEmail") String customerEmail,
			@RequestParam(value = "customerAddress") String customerAddress,
			@RequestParam(value = "customerPhone") String customerPhone) {
		Customer customer = new Customer();
		customer.setName(customerName);
		customer.setBirthDate(customerBirthDay);
		customer.setEmail(customerEmail);
		customer.setAddress(customerAddress);
		customer.setPhone(customerPhone);
		try {
			customerService.saveOrUpdate(customer);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/customer/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateCustomer(
			@RequestParam(value = "customerId") String customerId,
			@RequestParam(value = "customerName") String customerName,
			@RequestParam(value = "customerBirthDay") String customerBirthDay,
			@RequestParam(value = "customerEmail") String customerEmail,
			@RequestParam(value = "customerAddress") String customerAddress,
			@RequestParam(value = "customerPhone") String customerPhone) {
		Customer customer = customerService.getCustomer(Integer
				.parseInt(customerId));
		customer.setName(customerName);
		customer.setBirthDate(customerBirthDay);
		customer.setEmail(customerEmail);
		customer.setAddress(customerAddress);
		customer.setPhone(customerPhone);
		try {
			customerService.update(customer);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/customer/get", method = RequestMethod.GET)
	@ResponseBody
	public Customer getCustomer(@RequestParam(value = "itemId") String idemId) {
		Customer customer = customerService.getCustomer(Integer
				.parseInt(idemId));
		return customer;
	}
}
