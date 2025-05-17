package com.example.api_gateway.config;

import com.example.api_gateway.jwt.AuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final AuthFilter authFilter;

    public GatewayConfig(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 유저 서버 인증 ㄴㄴ
                .route("user-service", r -> r.path("/api/user/**")
                        .uri("http://user-service:8081"))

                .route("post-service", r -> r.path("/api/post/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://post-service:8082"))

                .build();
    }
}
