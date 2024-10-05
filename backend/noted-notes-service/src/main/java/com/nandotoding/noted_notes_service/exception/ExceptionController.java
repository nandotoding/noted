package com.nandotoding.noted_notes_service.exception;

import com.nandotoding.noted_notes_service.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity handleNotFoundException(NotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getStatus(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UnauthorizedException.class)
    ResponseEntity handleUnauthorizedException(UnauthorizedException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getStatus(), e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}
