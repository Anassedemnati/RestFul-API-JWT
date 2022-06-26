package com.emsi.meteo.app.ws.shared.dto;

import java.io.Serializable;
import java.util.List;

import com.emsi.meteo.app.ws.requests.AddressesRequest;
import lombok.Data;
@Data
public class UserDto implements Serializable{
	private static final long serialVersionUID = -7900847679121560515L;
	
	private long id;//PRAVATE ID IN DATA BASE
	private String userId;//PUBLIC ID IN API
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String encryptedPassword;
	private String emailVerificationToken;
	private Boolean emailVerificationStatus=false;
	private List<AddressesDto> addresses;
}
