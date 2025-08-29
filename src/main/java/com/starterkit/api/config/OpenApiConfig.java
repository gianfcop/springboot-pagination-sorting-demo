/*
 * Spring Boot API Starter Kit
 * Copyright (c) 2025 Gianfranco Coppola. All rights reserved.
 * Commercial use permitted. Redistribution prohibited.
 */
package com.starterkit.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Spring Boot Pagination & Sorting API")
                .version("1.0.0")
                .description("\"A practical starter kit for building RESTful APIs in Spring Boot, featuring pagination, sorting, DTO mapping, and an extensible architecture ready for real-world projects.")
                .contact(new Contact()
                    .name("Your Name")
                    .email("contact@example.com"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://springdoc.org")));
    }
}