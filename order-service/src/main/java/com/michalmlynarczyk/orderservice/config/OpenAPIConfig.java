package com.michalmlynarczyk.orderservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
        info = @Info(
                title = "Bicycle workshop management system API",
                version = "1.0",
                description = "API for bicycle workshop management system - order service"
        ),
        servers = {
                @Server(url = "http://localhost:8083", description = "Local development server"),
        }
)
public class OpenAPIConfig {
}
