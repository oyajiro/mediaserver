package com.oyajiro.controller;

import com.oyajiro.document.FileDocument;
import com.oyajiro.entity.MediaFile;
import com.oyajiro.service.FileRepositoryService;
import com.oyajiro.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.oyajiro.exception.RestControllerException.internalServerErrorException;
import static com.oyajiro.service.FileService.deleteFile;
import static com.oyajiro.service.FileService.writeToDisk;
import static com.oyajiro.utils.EntityUtils.getOrThrowNotFound;

@RestController
public class FileController {

	private FileRepositoryService fileService;
	private UserService userService;

	@ApiOperation("Create file")
	@RequestMapping(method = RequestMethod.POST, value = "/create", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public MediaFile create(@RequestParam("fileData") MultipartFile data) {
		MediaFile mediaFile = new MediaFile(data.getOriginalFilename(), data.getContentType());
		String path = writeToDisk(data);
		mediaFile.setPath(path);
		if (mediaFile.getPath().isEmpty()) {
			throw internalServerErrorException();
		}
		//TODO set user
		mediaFile.setOwner(userService.getCurrentUser());
		fileService.save(mediaFile);
		return mediaFile;
	}

	@ApiOperation("Update file data")
	@RequestMapping(method = RequestMethod.PUT, value = "/upload", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public MediaFile upload(@RequestParam("file") MultipartFile data, String id) {
		MediaFile mediaFile = getOrThrowNotFound(fileService.findById(id), id);
		String oldPath = mediaFile.getPath();
		String path = writeToDisk(data);
		mediaFile.setPath(path);
		if (mediaFile.getPath().isEmpty()) {
			throw internalServerErrorException();
		}
		//TODO set user
		fileService.save(mediaFile);
		deleteFile(oldPath);
		return mediaFile;
	}

	@ApiOperation("Get file by id")
	@RequestMapping(method = RequestMethod.GET, value = "/get", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public MediaFile get(@RequestParam("id") String id) {
		return getOrThrowNotFound(fileService.findById(id), id);
	}

	@ApiOperation("Get all files")
	@RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<MediaFile> getAll() {
		return fileService.findAll();
	}

	@ApiOperation("Search files")
	@RequestMapping(method = RequestMethod.GET, value = "/search", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<FileDocument> search(@RequestParam("search") String search) {
		return fileService.search(search);
	}

	@ApiOperation("Update mediaFile")
	@RequestMapping(method = RequestMethod.PUT, value = "/update", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public MediaFile update(MediaFile mediaFile, String id) {
		MediaFile entityFile = getOrThrowNotFound(fileService.findById(id), id);
		fileService.updateFile(mediaFile, entityFile);
		return mediaFile;
	}

	@Autowired
	protected void setFileService(FileRepositoryService fileService) {
		this.fileService = fileService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
