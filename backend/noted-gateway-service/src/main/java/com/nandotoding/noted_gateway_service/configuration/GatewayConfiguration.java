package com.nandotoding.noted_gateway_service.configuration;

import com.nandotoding.noted_gateway_service.filter.RequestValidationFilter;
import com.nandotoding.noted_gateway_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
    @Value("${uri.service.account}")
    private String accountServiceUri;
    @Value("${uri.service.notes}")
    private String notesServiceUri;
    private final JwtUtil jwtUtil;
    private final RequestValidationFilter requestValidationFilter;

    @Autowired
    public GatewayConfiguration(JwtUtil jwtUtil, RequestValidationFilter requestValidationFilter) {
        this.jwtUtil = jwtUtil;
        this.requestValidationFilter = requestValidationFilter;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("account-service", r -> r
                        .path("/account/**")
                        .uri(accountServiceUri))
                .route("notes-service", r -> r
                        .path("/notes/**")
                        .filters(f -> f.filter(requestValidationFilter))
                        .uri(notesServiceUri))
                .build();
    }
}
