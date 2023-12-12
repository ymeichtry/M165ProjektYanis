package com.backend.MongoDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.backend.MongoDB.repository")
@SpringBootApplication
public class MongoDbApplication {
	public static void main(String[] args) {
		SpringApplication.run(MongoDbApplication.class, args);
	}
}
