package com.emsi.meteo.app.ws.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class UserRequest {
	@NotNull(message = "first name should not be NULL !")
	@NotBlank
	@Size(min = 3,message = "first name should be more than 3 caracter")
	private String firstName;
	@NotBlank
	@NotNull(message = "last name should not be NULL !")
	@Size(min = 3,message = "last name should be more than 3 caracter")
	private String lastName;
	@NotNull(message = "email should not be NULL !")
	@Email(message = "EX test@test.com")
	private String email;
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
			message = "password should contains at least eight characters, including at least one number and includes both lower and uppercase letters and special characters, for example #, ?, !.")
	@NotNull(message = "password should not be NULL !")
	@Size(min = 8,max = 25,message = "password should be betwen 8 and 25 caracter !")
	private String password;

	private List<AddressesRequest> addresses;

}
