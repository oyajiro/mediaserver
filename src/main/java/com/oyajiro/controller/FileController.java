package com.oyajiro.controller;

import com.oyajiro.entity.MediaFile;
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
	@RequestMapping(method = RequestMethod.POST, value = "/create")
	@ResponseStatus(HttpStatus.OK)
	public MediaFile create(@RequestParam("fileData") MultipartFile data) {
		MediaFile mediaFile = new MediaFile(data.getOriginalFilename(), data.getContentType(), data.getSize());
		String path = writeToDisk(data);
		mediaFile.setPath(path);
		if (mediaFile.getPath().isEmpty()) {
			throw internalServerErrorException();
		}
		//TODO set user
		repository.save(mediaFile);
		return mediaFile;
	}

	@ApiOperation("Update file data")
	@RequestMapping(method = RequestMethod.PUT, value = "/upload")
	@ResponseStatus(HttpStatus.OK)
	public MediaFile upload(@RequestParam("file") MultipartFile data, String id) {
		MediaFile mediaFile = getOrThrowNotFound(repository.findById(id), id);
		String oldPath = mediaFile.getPath();
		String path = writeToDisk(data);
		mediaFile.setPath(path);
		if (mediaFile.getPath().isEmpty()) {
			throw internalServerErrorException();
		}
		//TODO set user
		repository.save(mediaFile);
		deleteFile(oldPath);
		return mediaFile;
	}

	@ApiOperation("Get file by id")
	@RequestMapping(method = RequestMethod.GET, value = "/get")
	@ResponseStatus(HttpStatus.OK)
	public MediaFile get(@RequestParam("id") String id) {
		return getOrThrowNotFound(repository.findById(id), id);
	}

	@ApiOperation("Get all files")
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	@ResponseStatus(HttpStatus.OK)
	public List<MediaFile> getAll() {
		return repository.findAll();
	}

	@ApiOperation("Search files")
	@RequestMapping(method = RequestMethod.GET, value = "/search")
	@ResponseStatus(HttpStatus.OK)
	public List<MediaFile> search(@RequestParam("id") String query) {
		return Collections.emptyList();
	}

	@ApiOperation("Update mediaFile")
	@RequestMapping(method = RequestMethod.PUT, value = "/update")
	@ResponseStatus(HttpStatus.OK)
	public MediaFile update(MediaFile mediaFile, String id) {
		MediaFile entityFile = getOrThrowNotFound(repository.findById(id), id);
		repository.updateFile(mediaFile, entityFile);
		return mediaFile;
	}

	@Autowired
	protected void setRepository(FileRepositoryService repository) {
		this.repository = repository;
	}
}
