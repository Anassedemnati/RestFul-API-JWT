package com.emsi.meteo.app.ws.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRequest {
	@NotNull
	@Size(min = 3)
	private String firstName;
	@NotNull
	@Size(min = 3)
	private String lastName;
	@NotNull
	@Email
	private String email;
	@NotNull
	@Size(min = 8,max = 25)
	private String password;
	

}
