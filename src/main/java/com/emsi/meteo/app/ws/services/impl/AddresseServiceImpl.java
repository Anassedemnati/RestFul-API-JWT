package com.emsi.meteo.app.ws.services.impl;

import com.emsi.meteo.app.ws.entities.AddresseEntity;
import com.emsi.meteo.app.ws.repositorys.AddresseRepository;

import com.emsi.meteo.app.ws.services.AddresseService;
import com.emsi.meteo.app.ws.shared.dto.AddressesDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.lang.reflect.Type;
import java.util.List;


@Service
@AllArgsConstructor
public class AddresseServiceImpl implements AddresseService {
    AddresseRepository addresseRepository;
    @Override
    public List<AddressesDto> getAllAddresses() {
        List<AddresseEntity> addresseEntityList = addresseRepository.findAll();

        Type listType = new TypeToken<List<AddressesDto>>() {}.getType();
        List<AddressesDto> addressesDto = new ModelMapper().map(addresseEntityList, listType);

        return addressesDto;
    }

}
