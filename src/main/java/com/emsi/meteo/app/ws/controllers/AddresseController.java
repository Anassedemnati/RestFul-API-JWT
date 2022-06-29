package com.emsi.meteo.app.ws.controllers;

import com.emsi.meteo.app.ws.responses.AddressesResponse;
import com.emsi.meteo.app.ws.services.AddresseService;
import com.emsi.meteo.app.ws.shared.dto.AddressesDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/addresses")
@AllArgsConstructor
public class AddresseController {

    AddresseService addresseService;

    @GetMapping
    public ResponseEntity<List<AddressesResponse>> getAddresses(Principal principal){
        if (principal==null || principal.getName().isBlank()) {
            throw new RuntimeException("Principal null or blank!");
        }
        List<AddressesDto> allAddresses = addresseService.getAllAddresses(principal.getName());

        Type listType = new TypeToken<List<AddressesResponse>>() {}.getType();
        List<AddressesResponse> addressesResponseList = new ModelMapper().map(allAddresses, listType);

        return new  ResponseEntity<List<AddressesResponse>>(addressesResponseList, HttpStatus.OK);
    }
}
