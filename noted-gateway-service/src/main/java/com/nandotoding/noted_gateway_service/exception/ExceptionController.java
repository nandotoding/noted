package com.nandotoding.noted_gateway_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(UnauthorizedException.class)
    public Mono<ResponseEntity> handleUnauthorizedException(UnauthorizedException e) {
        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage()));
    }
}
