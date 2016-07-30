package com.oyajiro;

import com.oyajiro.repository.FileRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = FileRepository.class)
public class MediahostingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediahostingApplication.class, args);
	}
}

