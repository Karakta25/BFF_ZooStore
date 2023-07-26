package com.example.bff.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.bff"})
//@EntityScan(basePackages = { "com.example.zoostorestorage.persistence.entities"})
//@EnableJpaRepositories(basePackages = {"com.example.zoostorestorage.persistence.repositories"})
public class BFFApplication {
            public static void main(String[] args) {
            SpringApplication.run(BFFApplication.class, args);
        }
}
