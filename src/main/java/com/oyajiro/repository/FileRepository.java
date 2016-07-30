package com.oyajiro.repository;

import com.oyajiro.entity.MediaFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<MediaFile, Integer> {
	MediaFile findByName(String name);
	MediaFile findById(String id);
}
