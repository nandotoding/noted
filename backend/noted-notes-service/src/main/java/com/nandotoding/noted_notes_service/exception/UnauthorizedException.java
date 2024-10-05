package com.nandotoding.noted_notes_service.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
@Data
public class UnauthorizedException extends RuntimeException {
    private int code = 401;
    private String status = "UNAUTHORIZED";

    public UnauthorizedException() {
        super("Unauthorized");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
