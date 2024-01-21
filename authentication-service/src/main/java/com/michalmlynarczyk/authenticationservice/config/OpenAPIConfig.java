package com.michalmlynarczyk.authenticationservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${openapi.devUrl}")
    private String localDevUrl;


    @Bean
    public OpenAPI myOpenAPI() {
        Server localDev = new Server();
        localDev.setUrl(localDevUrl);
        localDev.setDescription("Server URL in Development environment");


        Info info = new Info()
                .title("Bicycle workshop management system API")
                .version("1.0");

        return new OpenAPI().info(info).servers(List.of(localDev));
    }
}
