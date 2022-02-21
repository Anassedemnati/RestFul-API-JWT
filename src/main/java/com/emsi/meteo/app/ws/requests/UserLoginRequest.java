package com.emsi.meteo.app.ws.requests;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String email,password;

}
