package com.vaan.task.talk.chat_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.vaan.task.talk.chat_service")
public class MongoConfig {
    // Configuration for MongoDB
} 