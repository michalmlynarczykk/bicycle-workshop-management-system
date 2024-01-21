package com.michalmlynarczyk.workshopmanagementservice;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableMongoRepositories
@ComponentScan(basePackages = "com.michalmlynarczyk.*")
public class WorkshopManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkshopManagementServiceApplication.class, args);
    }


    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}
