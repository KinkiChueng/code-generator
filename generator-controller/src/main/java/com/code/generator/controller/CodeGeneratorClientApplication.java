package com.code.generator.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author lasia
 * @date 2020/4/25
 */
@SpringBootApplication
@EnableEurekaClient
public class CodeGeneratorClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeGeneratorClientApplication.class, args);
    }
}
