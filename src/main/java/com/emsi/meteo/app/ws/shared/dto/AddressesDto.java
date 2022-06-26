package com.emsi.meteo.app.ws.shared.dto;

import lombok.Data;

import java.io.Serializable;



@Data
public class AddressesDto implements Serializable {
    private static final long serialVersionUID = -7990827679129560515L;
    private long id;
    private String addresseId;
    private String city;
    private String country;
    private String street;
    private String zipCode;
    private String type;
    private UserDto user;
}
