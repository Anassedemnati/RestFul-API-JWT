package com.emsi.meteo.app.ws.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "user")
@Data @AllArgsConstructor @NoArgsConstructor
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = -2516375549986814526L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<AddresseEntity> addresses;
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private ContactEntity contact;

}
