package com.emsi.meteo.app.ws.services;

import com.emsi.meteo.app.ws.shared.dto.AddressesDto;

import java.util.List;

public interface AddresseService {
    List<AddressesDto> getAllAddresses(String email);

    AddressesDto createAddress(AddressesDto addressDto, String name);

    AddressesDto getAddress(String addressId);

    void deleteAddress(String addressId);
}
