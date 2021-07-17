package com.hh18.tenorbackendservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TenorBackendServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenorBackendServiceApplication.class, args);
    }

}
