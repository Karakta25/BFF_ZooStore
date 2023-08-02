package com.example.bff.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.bff"})
@EntityScan(basePackages = { "com.example.bff.persistence.entities"})
@EnableJpaRepositories(basePackages = {"com.example.bff.persistence.repositories"})
public class BFFApplication {
    public static void main(String[] args) {
            SpringApplication.run(BFFApplication.class, args);
        }
}
