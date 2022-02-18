package com.emsi.meteo.app.ws.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;


@Data
@Entity(name = "user")
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = -2516375549986814526L;
	
	@Id
	@GeneratedValue
	private long id;//PRAVATE ID IN DATA BASE
	
	@Column(nullable = false)
	private String userId;//PUBLIC ID IN API
	
	@Column(nullable = false, length = 50)
	private String firstName;
	
	@Column(nullable = false, length = 50)
	private String lastName;
	
	@Column(nullable = false, length = 120, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String encryptedPassword;
	
	@Column(nullable = true)
	private String emailVerificationToken;
	
	@Column(nullable = false)
	private Boolean emailVerificationStatus=false;

}
