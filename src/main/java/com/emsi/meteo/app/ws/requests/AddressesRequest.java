package com.emsi.meteo.app.ws.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
public class AddressesRequest {
   private String city;

   private String country;

   private String street;

   private String zipCode;

   private String type;
}
