package com.cz.blogtwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})//
public class BlogtwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogtwoApplication.class, args);
    }

}
