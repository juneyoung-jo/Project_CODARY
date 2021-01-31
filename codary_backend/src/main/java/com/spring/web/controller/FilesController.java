package com.spring.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("file")
public class FilesController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("upload")
	public ResponseEntity<String> uploadFiles(@RequestPart List<MultipartFile> files) {
		
		logger.info("file upload method start");
		
		for (MultipartFile file : files) {
			String originalfileName = file.getOriginalFilename();
			File dest = new File("C:/Image/" + originalfileName);
			try {
				file.transferTo(dest);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

}
