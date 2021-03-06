package com.plc.exception.ExceptionService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound() {
        super();
    }

    public ResourceNotFound(String msg) {
        super(msg);
    }

    public ResourceNotFound(String msg, Throwable cause) {
        super(msg, cause);
    }
}
