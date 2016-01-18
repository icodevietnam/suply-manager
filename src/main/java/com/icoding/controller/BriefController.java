package com.icoding.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.icoding.domain.Brief;
import com.icoding.domain.BriefType;
import com.icoding.domain.Customer;
import com.icoding.domain.Department;
import com.icoding.domain.File;
import com.icoding.domain.Note;
import com.icoding.domain.Stock;
import com.icoding.dto.BriefNote;
import com.icoding.dto.Graph;
import com.icoding.process.ImageProcess;
import com.icoding.service.BriefService;
import com.icoding.service.BriefTypeService;
import com.icoding.service.CustomerService;
import com.icoding.service.DepartmentService;
import com.icoding.service.FileService;
import com.icoding.service.NoteService;
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

	@Autowired
	private FileService fileService;

	@Autowired
	private NoteService noteService;

	// Show trang hồ sơ trong admin
	@RequestMapping(value = { "/admin/brief",
			"/admin/brief/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
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

	// Trả ra toàn bộ hồ sơ
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
		List<File> listFiles = brief.getListImage();
		for (File file : listFiles) {
			file.setBrief(null);
			fileService.delete(file);
		}
		try {
			briefService.delete(brief);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

	@RequestMapping(value = "/brief/new", method = RequestMethod.POST)
	@ResponseBody
	public String addBrief(HttpServletRequest request, @RequestParam(value = "content") String content,
			@RequestParam(value = "stockBox") String stockBox,
			@RequestParam(value = "briefTypeBox") String briefTypeBox,
			@RequestParam(value = "customerBox") String customerBox,
			@RequestParam(value = "departmentBox") String departmentBox,
			@RequestParam(value = "file1") MultipartFile file1, @RequestParam(value = "file2") MultipartFile file2,
			@RequestParam(value = "file3") MultipartFile file3) {
		Brief brief = new Brief();
		brief.setContent(content);
		brief.setCreateDate(new Date());
		brief.setBriefType(briefTypeService.getBriefType(Integer.parseInt(briefTypeBox)));
		brief.setStock(stockService.getStock(Integer.parseInt(stockBox)));
		brief.setCustomer(customerService.getCustomer(customerBox));
		brief.setDepartment(departmentService.getDepartment(Integer.parseInt(departmentBox)));
		ImageProcess imageProcess = new ImageProcess();
		try {
			briefService.saveOrUpdate(brief);
			if (!file1.isEmpty()) {
				imageProcess.uploadImage(file1, request, fileService, brief);
			}
			if (!file2.isEmpty()) {
				imageProcess.uploadImage(file2, request, fileService, brief);
			}
			if (!file3.isEmpty()) {
				imageProcess.uploadImage(file3, request, fileService, brief);
			}
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/brief/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateBrief(HttpServletRequest request, @RequestParam(value = "briefId") String briefId,
			@RequestParam(value = "content") String content, @RequestParam(value = "stockBox") String stockBox,
			@RequestParam(value = "briefTypeBox") String briefTypeBox,
			@RequestParam(value = "customerBox") String customerBox,
			@RequestParam(value = "departmentBox") String departmentBox,
			@RequestParam(value = "file1") MultipartFile file1, @RequestParam(value = "file2") MultipartFile file2,
			@RequestParam(value = "file3") MultipartFile file3) {
		Brief brief = briefService.getBrief(Integer.parseInt(briefId));
		brief.setContent(content);
		brief.setBriefType(briefTypeService.getBriefType(Integer.parseInt(briefTypeBox)));
		brief.setStock(stockService.getStock(Integer.parseInt(stockBox)));
		brief.setCustomer(customerService.getCustomer(customerBox));
		brief.setDepartment(departmentService.getDepartment(Integer.parseInt(departmentBox)));
		ImageProcess imageProcess = new ImageProcess();
		try {
			briefService.update(brief);
			if (!file1.isEmpty()) {
				imageProcess.uploadImage(file1, request, fileService, brief);
			}
			if (!file2.isEmpty()) {
				imageProcess.uploadImage(file2, request, fileService, brief);
			}
			if (!file3.isEmpty()) {
				imageProcess.uploadImage(file3, request, fileService, brief);
			}
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

	@RequestMapping(value = "/brief/searchName", method = RequestMethod.GET)
	@ResponseBody
	public List<Brief> searchBriefByName(@RequestParam(value = "searchName") String searchName) {
		Customer customer = customerService.searchName(searchName);
		List<Brief> listBriefs = customer.getListBriefs();
		return listBriefs;
	}

	@RequestMapping(value = "/brief/searchCustomer", method = RequestMethod.GET)
	@ResponseBody
	public List<Customer> getCustomer(@RequestParam(value = "code") String code,
			@RequestParam(value = "name") String name, @RequestParam(value = "email") String email) {
		/*
		 * List<Customer> listCustomers = new ArrayList<Customer>();
		 * listCustomers = briefService.searchCustomer(code);
		 */
		List<Customer> listCustomers = customerService.searchCustomer(code, email, name);
		return listCustomers;
	}

	@RequestMapping(value = "/brief/searchBrief", method = RequestMethod.GET)
	@ResponseBody
	public List<Brief> getBriefNe(@RequestParam(value = "cusId") String cusId) {
		List<Brief> listBriefs = new ArrayList<Brief>();
		listBriefs = briefService.searchBrief(Integer.parseInt(cusId));
		return listBriefs;
	}

	@RequestMapping(value = "/brief/{code}", method = RequestMethod.GET)
	public String getBriefCode(Model model, @PathVariable(value = "code") String code) {
		Customer customer = customerService.getCustomer(code);
		List<Brief> listBriefs = briefService.getAll();
		List<Brief> filterBrief = new ArrayList<Brief>();
		for (Brief brief : listBriefs) {
			if (brief.getCustomer().getCode().toLowerCase().equalsIgnoreCase(code.toLowerCase())) {
				filterBrief.add(brief);
			}
		}
		model.addAttribute("listBrief", filterBrief);
		model.addAttribute("title", "Hồ Sơ của : " + customer.getName());
		return "home/showBrief";
	}

	@RequestMapping(value = "/brief/searchNotBorrow", method = RequestMethod.GET)
	@ResponseBody
	public List<Brief> getBriefNotBorrow() {
		List<Brief> listBriefs = briefService.showNoneBorrow();
		return listBriefs;
	}

	@RequestMapping(value = "/brief/searchBorrow", method = RequestMethod.GET)
	@ResponseBody
	public List<Brief> searchBorrow() {
		List<Brief> listBriefs = briefService.showBriefBorrow();
		return listBriefs;
	}

	@RequestMapping(value = "/brief/updateNote", method = RequestMethod.POST)
	@ResponseBody
	public String updateBrief(HttpServletRequest request, @RequestParam(value = "briefId") String briefId,
			@RequestParam(value = "noteId") String noteId) {
		Brief brief = briefService.getBrief(Integer.parseInt(briefId));
		Note note = noteService.getNote(noteId);
		brief.setNote(note);
		try {
			briefService.update(brief);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/brief/listBriefNote", method = RequestMethod.GET)
	@ResponseBody
	public List<BriefNote> listBriefNote(Model model) {
		List<BriefNote> listBriefNote = briefService.getListBriefNote();
		return listBriefNote;
	}

	@RequestMapping(value = "/brief/listBriefByDepartment", method = RequestMethod.GET)
	@ResponseBody
	public List<Graph> listBriefByDepartment(Model model) {
		List<Graph> listGraphs = briefService.listBriefByDepartment();
		return listGraphs;
	}

	@RequestMapping(value = "/brief/listGraphByStock", method = RequestMethod.GET)
	@ResponseBody
	public List<Graph> listGraphByStock(Model model) {
		List<Graph> listGraphs = briefService.listGraphByStock();
		return listGraphs;
	}

	@RequestMapping(value = "/brief/searchBriefsInReport", method = RequestMethod.GET)
	@ResponseBody
	public List<Brief> searchBrief(@RequestParam(value = "fromDate") String fromDateStr,
			@RequestParam(value = "toDate") String toDateStr, @RequestParam(value = "code") String code,
			@RequestParam(value = "customerName") String customerName,
			@RequestParam(value = "briefTypeBox") String briefTypeBox,
			@RequestParam(value = "stockBox") String stockBox) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		List<Brief> listBriefs = new ArrayList<Brief>();
		try {
			Date fromDate = formatter.parse(fromDateStr);
			Date toDate = formatter.parse(toDateStr);
			listBriefs = briefService.listBriefFromTo(fromDate, toDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listBriefs;
	}

}
