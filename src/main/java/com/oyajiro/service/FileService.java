package com.oyajiro.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static com.oyajiro.exception.RestControllerException.notCreatedException;

@Service
public class FileService {

	public static String ROOT = "/tmp";

	//TODO implement logger
	//public static Logger =

	public static String writeToDisk(MultipartFile file) {
		try {
			Path filePath =  getFilePath(getRandomFileName());
			Files.copy(file.getInputStream(), filePath);
			return filePath.toAbsolutePath().toString();
		} catch (IOException e) {
			//TODO write log
			throw notCreatedException(e);
		}
	}

	//TODO DELETE with Scheduler not in realtime!!!
	public static void deleteFile(String path) {
		try {
			Files.delete(Paths.get(path));
		} catch (IOException e) {
			//TODO write log, mark as not deleted
		}
	}

	public static UUID getRandomFileName() {
		UUID uuid = UUID.randomUUID();
		if (Files.exists(getFilePath(uuid))) {
			return getRandomFileName();
		}
		return uuid;
	}

	public static Path getFilePath(UUID uuid) {
		return Paths.get(ROOT, uuid.toString());
	}


}
