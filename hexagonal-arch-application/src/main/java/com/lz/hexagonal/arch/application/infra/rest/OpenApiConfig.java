package com.lz.hexagonal.arch.application.infra.rest;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hexagonal Arch API")
                        .version("1.0.0")
                        .description("API documentation for Hexagonal Architecture project"));
    }
}
