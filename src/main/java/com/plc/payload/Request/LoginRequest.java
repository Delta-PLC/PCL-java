package com.plc.payload.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginRequest {
    private Long mobilenumber;
    private String password;
}
