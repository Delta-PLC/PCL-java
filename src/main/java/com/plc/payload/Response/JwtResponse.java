package com.plc.payload.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String useremail;
    private String mobilenumber;

    private Date datetime;
    private final List<String> roles;

    public JwtResponse(String accessToken, Long id, String useremail, String username,
                       String mobilenumber, Date datetime,
                       List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.useremail = useremail;
        this.username = username;
        this.mobilenumber = mobilenumber;
        this.datetime = datetime;
        this.roles = roles;
    }

}
