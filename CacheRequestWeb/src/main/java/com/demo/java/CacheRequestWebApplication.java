package com.demo.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CacheRequestWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheRequestWebApplication.class, args);
    }

}
