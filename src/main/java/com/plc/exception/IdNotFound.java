package com.plc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdNotFound extends RuntimeException{

    public IdNotFound(String message) {
        super(message);
    }

    public IdNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public IdNotFound() {
        super();
    }
}
