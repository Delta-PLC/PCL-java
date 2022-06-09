package com.plc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFound extends RuntimeException{

    public RoleNotFound(String message) {
        super(message);
    }

    public RoleNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleNotFound() {        super();
    }
}
