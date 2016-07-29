package com.oyajiro.controller;

import com.oyajiro.entity.File;
import com.oyajiro.service.FileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Path("/")
public class FileController {

	@RequestMapping(method = RequestMethod.POST, value = "/greeting")
	public File upload(@RequestParam("file") MultipartFile data) {
		File entityFile = new File(data.getOriginalFilename(), data.getContentType(), data.getSize());
		String path = FileService.writeToDisk(data);
		entityFile.setPath(path);
		if (entityFile.getPath().isEmpty()) {
			throw new WebApplicationException("File path is null", INTERNAL_SERVER_ERROR);
		}
		return entityFile;
	}
}
