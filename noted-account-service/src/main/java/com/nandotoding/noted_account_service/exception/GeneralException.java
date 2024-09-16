package com.nandotoding.noted_account_service.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
@Data
public class GeneralException extends RuntimeException {
    private int code = 1001;
    private String status = "ERROR";

    public GeneralException() {
        super("Error");
    }

    public GeneralException(String message) {
        super(message);
    }
}
