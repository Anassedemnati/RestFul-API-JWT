package com.emsi.meteo.app.ws.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "addresse")
@Data @AllArgsConstructor @NoArgsConstructor
public class AddresseEntity implements Serializable {
	
	private static final long serialVersionUID = -2536375749986834526L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;//PRAVATE ID IN DATA BASE
	
	@Column(nullable = false)
	private String addresseId;//PUBLIC ID IN API
	
	@Column(nullable = false, length = 20)
	private String city;
	
	@Column(nullable = false, length = 30)
	private String country;
	
	@Column(nullable = false, length = 120)
	private String street;
	
	@Column(nullable = false,length = 8)
	private String zipCode;
	
	@Column(nullable = false,length = 20)
	private String type;
	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity user;
	


}
