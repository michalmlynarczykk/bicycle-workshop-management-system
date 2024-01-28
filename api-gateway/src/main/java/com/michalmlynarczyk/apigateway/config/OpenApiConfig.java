package com.michalmlynarczyk.apigateway.config;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class OpenApiConfig {


    private final RouteDefinitionLocator locator;


    @Bean
    public List<GroupedOpenApi> apis() {
        final List<GroupedOpenApi> groups = new ArrayList<>();
        final List<RouteDefinition> definitions = locator
                .getRouteDefinitions().collectList().block();
        assert definitions != null;
        definitions.stream().filter(routeDefinition -> routeDefinition
                        .getId()
                        .matches(".*-service"))
                .forEach(routeDefinition -> {
                    String name = routeDefinition.getId()
                            .replaceAll("-service", "");
                    groups.add(GroupedOpenApi.builder()
                            .pathsToMatch("/" + name + "/**").group(name).build());
                });
        return groups;
    }
}
