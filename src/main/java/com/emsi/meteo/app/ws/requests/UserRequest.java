package com.emsi.meteo.app.ws.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRequest {
	@NotNull(message = "first name should not be NULL !")
	@Size(min = 3,message = "first name should be more than 3 caracter")
	private String firstName;
	@NotNull(message = "last name should not be NULL !")
	@Size(min = 3,message = "last name should be more than 3 caracter")
	private String lastName;
	@NotNull(message = "email should not be NULL !")
	@Email(message = "EX test@test.com")
	private String email;
	@NotNull(message = "password should not be NULL !")
	@Size(min = 8,max = 25,message = "password should be betwen 8 and 25 caracter !")
	private String password;
	

}
