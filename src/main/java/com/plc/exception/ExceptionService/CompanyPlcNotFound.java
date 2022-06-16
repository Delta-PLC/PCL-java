package com.plc.exception.ExceptionService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CompanyPlcNotFound extends RuntimeException {
    public CompanyPlcNotFound() {
        super();
    }

    public CompanyPlcNotFound(String msg) {
        super(msg);
    }

    public CompanyPlcNotFound(String msg, Throwable cause) {
        super(msg, cause);
    }
}
