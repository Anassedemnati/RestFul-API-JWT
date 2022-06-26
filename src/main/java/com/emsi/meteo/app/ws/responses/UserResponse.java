package com.emsi.meteo.app.ws.responses;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private List<AddressesResponse> addresses;
	private ContactResponse contact;

}
