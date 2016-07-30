package com.oyajiro.controller;

import com.oyajiro.entity.File;
import com.oyajiro.service.FileRepositoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

import static com.oyajiro.exception.RestControllerException.internalServerErrorException;
import static com.oyajiro.service.FileService.deleteFile;
import static com.oyajiro.service.FileService.writeToDisk;
import static com.oyajiro.utils.EntityUtils.getOrThrowNotFound;

@RestController
public class FileController {

	private FileRepositoryService repository;

	@ApiOperation("Create file")
	@RequestMapping(method = RequestMethod.POST, value = "/upload")
	@ResponseStatus(HttpStatus.OK)
	public File create(@RequestParam("file") MultipartFile data) {
		File entityFile = new File(data.getOriginalFilename(), data.getContentType(), data.getSize());
		String path = writeToDisk(data);
		entityFile.setPath(path);
		if (entityFile.getPath().isEmpty()) {
			throw internalServerErrorException();
		}
		//TODO set user
		repository.save(entityFile);
		return entityFile;
	}

	@ApiOperation("Update file data")
	@RequestMapping(method = RequestMethod.PUT, value = "/upload")
	@ResponseStatus(HttpStatus.OK)
	public File upload(@RequestParam("file") MultipartFile data, String id) {
		File entityFile = getOrThrowNotFound(repository.findById(id), id);
		String oldPath = entityFile.getPath();
		String path = writeToDisk(data);
		entityFile.setPath(path);
		if (entityFile.getPath().isEmpty()) {
			throw internalServerErrorException();
		}
		//TODO set user
		repository.save(entityFile);
		deleteFile(oldPath);
		return entityFile;
	}

	@ApiOperation("Get file by id")
	@RequestMapping(method = RequestMethod.GET, value = "/get")
	@ResponseStatus(HttpStatus.OK)
	public File get(@RequestParam("id") String id) {
		return getOrThrowNotFound(repository.findById(id), id);
	}

	@ApiOperation("Get all files")
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	@ResponseStatus(HttpStatus.OK)
	public List<File> getAll() {
		return repository.findAll();
	}

	@ApiOperation("Search files")
	@RequestMapping(method = RequestMethod.GET, value = "/search")
	@ResponseStatus(HttpStatus.OK)
	public List<File> search(@RequestParam("id") String query) {
		return Collections.emptyList();
	}

	@ApiOperation("Update file")
	@RequestMapping(method = RequestMethod.PUT, value = "/upload")
	@ResponseStatus(HttpStatus.OK)
	public File update(File file, String id) {
		File entityFile = getOrThrowNotFound(repository.findById(id), id);
		repository.updateFile(file, entityFile);
		return entityFile;
	}

	@Autowired
	protected void setRepository(FileRepositoryService repository) {
		this.repository = repository;
	}
}
