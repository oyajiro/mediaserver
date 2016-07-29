package com.oyajiro.repository;

import com.oyajiro.entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<File, Integer> {
	File findByName(String name);
}
