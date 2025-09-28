package com.ems_jdbc_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.ems"})
@EntityScan(basePackages = {"com.ems.entity"})
@EnableJpaRepositories(basePackages = {"com.ems.repository"})
public class EmsJdbcAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmsJdbcAuthApplication.class, args);
    }

}
