package com.example.Notify.MongoDB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;


@Component
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @PostConstruct
    public void printConnectionString() {
        System.out.println("MongoDB Connection String: " + mongoUri);
    }
}
