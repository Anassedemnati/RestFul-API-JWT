package com.emsi.meteo.app.ws.responses;

import com.emsi.meteo.app.ws.shared.dto.UserDto;
import lombok.Data;

@Data
public class AddressesResponse {
    private String addresseId;
    private String city;
    private String country;
    private String street;
    private String zipCode;
    private String type;

}
