package com.emsi.meteo.app.ws.requests;

import lombok.Data;


public @Data class UserRequest {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	

}
