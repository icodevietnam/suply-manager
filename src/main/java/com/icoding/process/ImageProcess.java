package com.icoding.process;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.icoding.domain.Brief;
import com.icoding.domain.File;
import com.icoding.service.FileService;

public class ImageProcess {
	
	private static final Logger logger = LoggerFactory.getLogger(ImageProcess.class);

	public File uploadImage(MultipartFile file, HttpServletRequest request, FileService fileService,Brief brief) {
		File image = null;
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				// Creating the directory to storefile
				java.io.File dir = new java.io.File(request.getServletContext().getRealPath("") + java.io.File.separator + "resources" + java.io.File.separator+"default" + java.io.File.separator + "images");
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				java.io.File serverFile = new java.io.File(dir.getAbsolutePath() + java.io.File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				// We will process Image Object
				String fileName = file.getOriginalFilename();
				String[] strArray = fileName.split("\\.");
				String ext = strArray[strArray.length - 1];
				image = new File();
				image.setExtension(ext);
				image.setAbsolutelyPath("/suply-manager/resources/default/images/" + file.getOriginalFilename());
				image.setName(brief.getBriefType().getName()+"-"+brief.getCustomer().getName()+"-"+brief.getCustomer().getCode());
				image.setPath(serverFile.toString());
				image.setBrief(brief);
				fileService.saveOrUpdate(image);

				logger.info("Server File Location=" + serverFile.getAbsolutePath());
				logger.debug("Debug: Save Image Into Upload Folder Successfully");
				return image;

			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("Debug: Save Image Into Upload Folder Fail");
				return image;
			}
		} else {
			logger.debug("Debug: Save Image Into Upload Folder Fail");
			return image;
		}
	}

}
