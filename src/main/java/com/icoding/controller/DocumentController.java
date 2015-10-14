package com.icoding.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.icoding.domain.Brief;
import com.icoding.domain.Document;
import com.icoding.domain.DocumentType;
import com.icoding.domain.FileAttached;
import com.icoding.service.BriefService;
import com.icoding.service.DocumentService;
import com.icoding.service.DocumentTypeService;
import com.icoding.service.FileAttachedService;

@Controller
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private BriefService briefService;

	@Autowired
	private DocumentTypeService documentTypeService;

	@Autowired
	private FileAttachedService fileAttachedService;

	@RequestMapping(value = { "/admin/document", "/admin/document/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String displayPage(Model model) {
		List<Brief> listBriefs = briefService.getAll();
		List<DocumentType> listDocumentTypes = documentTypeService.getAll();
		model.addAttribute("pageName", "Quản lý Đơn Từ");
		model.addAttribute("title", "Quản lý Đơn Từ");
		model.addAttribute("listBriefs", listBriefs);
		model.addAttribute("listDocumentTypes", listDocumentTypes);
		return "document/index";
	}

	// Response document as json
	@RequestMapping(value = "/document/getAll", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Document> getAll(Model model) {
		List<Document> listDocuments = new ArrayList<Document>();
		listDocuments = documentService.getAll();
		return listDocuments;
	}

	@RequestMapping(value = "/document/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDocument(@RequestParam(value = "itemId") String itemId) {
		Integer id = Integer.parseInt(itemId);
		Document document = documentService.getDocument(id);
		document.setBrief(null);
		document.setDocumentType(null);
		// Delete document
		documentService.delete(document);
		return "true";
	}

	@RequestMapping(value = "/document/new", method = RequestMethod.POST)
	@ResponseBody
	public String addDocument(@RequestParam(value = "content") String content,
			@RequestParam(value = "briefBox") String briefId,
			@RequestParam(value = "documentTypeBox") String documentTypeId,
			@RequestParam(value = "fileUpload") MultipartFile fileUpload) {
		Document document = new Document();
		document.setContent(content);
		document.setCreatedDate(new Date());
		document.setBrief(briefService.getBrief(Integer.parseInt(briefId)));
		document.setDocumentType(documentTypeService.getDocumentType(Integer
				.parseInt(documentTypeId)));
		FileAttached isUploaded = uploadImageFile(fileUpload, document);
		try {
			documentService.saveOrUpdate(document);
			if (null != isUploaded)
				return "Upload thành công";
			else
				return "Upload lỗi";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/document/update", method = RequestMethod.POST)
	@ResponseBody
	public String updateDocument(
			@RequestParam(value = "documentId") String documentId,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "briefBox") String briefId,
			@RequestParam(value = "documentTypeA") String documentTypeId,
			@RequestParam(value = "fileUpload") MultipartFile fileUpload) {
		Document document = documentService.getDocument(Integer
				.parseInt(documentId));
		document.setContent(content);
		document.setCreatedDate(new Date());
		document.setBrief(briefService.getBrief(Integer.parseInt(briefId)));
		document.setDocumentType(documentTypeService.getDocumentType(Integer
				.parseInt(documentTypeId)));
		FileAttached isUploaded = uploadImageFile(fileUpload, document);
		try {
			documentService.saveOrUpdate(document);
			if (null != isUploaded)
				return "Upload thành công";
			else
				return "Upload lỗi";
		} catch (Exception e) {
			return "false";
		}
	}

	@RequestMapping(value = "/document/get", method = RequestMethod.GET)
	@ResponseBody
	public Document getDocument(@RequestParam(value = "itemId") String idemId) {
		Document document = documentService.getDocument(Integer
				.parseInt(idemId));
		return document;
	}

	public FileAttached uploadImageFile(MultipartFile file, Document document) {
		FileAttached fileAttached = new FileAttached();
		if (!file.isEmpty()) {
			try {
				//
				byte[] bytes = file.getBytes();
				// Tao thu muc
				String rootApp = servletContext.getRealPath("/");
				String relativeFolder = File.separator + "resources"
						+ File.separator + "default" + File.separator
						+ "images" + File.separator;
				File dir = new File(rootApp + relativeFolder);
				if (!dir.exists()) {
					dir.mkdir();
				}
				// Create a file on server
				String originalName = file.getOriginalFilename();
				if (originalName.contains(".jpg")
						|| originalName.equalsIgnoreCase(".png")
						|| originalName.equalsIgnoreCase(".jpeg")) {
					File serverFile = new File(dir + File.separator + file.getOriginalFilename());
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					fileAttached.setName(file.getOriginalFilename());
					fileAttached.setPath(dir.getAbsolutePath() + File.separator
							+ file.getOriginalFilename());
					fileAttached.setDocument(document);
					fileAttachedService.saveOrUpdate(fileAttached);
					return fileAttached;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
		return fileAttached;
	}

}
