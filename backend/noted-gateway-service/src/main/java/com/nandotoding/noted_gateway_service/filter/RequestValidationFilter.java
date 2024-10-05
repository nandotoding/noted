package com.nandotoding.noted_gateway_service.filter;

import com.nandotoding.noted_gateway_service.exception.UnauthorizedException;
import com.nandotoding.noted_gateway_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class RequestValidationFilter implements GatewayFilter {
    private final JwtUtil jwtUtil;

    @Autowired
    public RequestValidationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return Mono.error(new UnauthorizedException("Invalid Request Header"));
        }

        String token = authorizationHeader.replace("Bearer ", "");

        try {
            jwtUtil.validateToken(token);
        } catch (RuntimeException e) {
            return Mono.error(new UnauthorizedException("Invalid JWT"));
        }

        return chain.filter(exchange);
    }
}
