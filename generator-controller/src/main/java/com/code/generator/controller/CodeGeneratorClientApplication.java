package com.code.generator.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author lasia
 * @date 2020/4/25
 */
@EnableEurekaClient
@SpringBootApplication(exclude={SecurityAutoConfiguration.class,  SecurityFilterAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
public class CodeGeneratorClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeGeneratorClientApplication.class, args);
    }
}
