package com.michalmlynarczyk.authenticationservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Bicycle workshop management system API",
                version = "1.0",
                description = "API for bicycle workshop management system - authentication service"
        ),
        servers = {
                @io.swagger.v3.oas.annotations.servers.Server(url = "http://localhost:8081", description = "Local development server"),
        }
)
public class OpenAPIConfig {
}
