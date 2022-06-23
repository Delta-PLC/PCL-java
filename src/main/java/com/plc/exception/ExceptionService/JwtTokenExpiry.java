package com.plc.exception.ExceptionService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class JwtTokenExpiry extends RuntimeException {
    public JwtTokenExpiry() {
        super();
    }

    public JwtTokenExpiry(String msg) {
        super(msg);
    }

    public JwtTokenExpiry(String msg, Throwable cause) {
        super(msg, cause);
    }

}

