package com.emsi.meteo.app.ws.services.impl;

import com.emsi.meteo.app.ws.entities.AddresseEntity;
import com.emsi.meteo.app.ws.entities.UserEntity;
import com.emsi.meteo.app.ws.repositorys.AddresseRepository;

import com.emsi.meteo.app.ws.repositorys.UserRepository;
import com.emsi.meteo.app.ws.services.AddresseService;
import com.emsi.meteo.app.ws.shared.Utils;
import com.emsi.meteo.app.ws.shared.dto.AddressesDto;
import com.emsi.meteo.app.ws.shared.dto.UserDto;
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
    UserRepository userRepository;
    Utils utils;
    @Override
    public List<AddressesDto> getAllAddresses(String email) {
        UserEntity currentUser = userRepository.findByEmail(email);

        List<AddresseEntity> addresseEntityList = currentUser.getIsAdmin()==true
                ? addresseRepository.findAll()
                :addresseRepository.findByUser(currentUser);

        Type listType = new TypeToken<List<AddressesDto>>() {}.getType();
        List<AddressesDto> addressesDto = new ModelMapper().map(addresseEntityList, listType);

        return addressesDto;
    }

    @Override
    public AddressesDto createAddress(AddressesDto address, String email) {
        UserEntity currentUser=userRepository.findByEmail(email);
        ModelMapper modelMapper=new ModelMapper();
        UserDto userDto=modelMapper.map(currentUser,UserDto.class);

        address.setAddresseId(utils.generateStringId(30));
        address.setUser(userDto);

        AddresseEntity addressEntity=modelMapper.map(address,AddresseEntity.class);
        AddresseEntity newAddress=addresseRepository.save(addressEntity);
        AddressesDto addressDto=modelMapper.map(newAddress,AddressesDto.class);
        return addressDto;
    }

}
