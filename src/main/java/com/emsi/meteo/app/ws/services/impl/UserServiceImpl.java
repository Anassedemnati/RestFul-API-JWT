package com.emsi.meteo.app.ws.services.impl;

import com.emsi.meteo.app.ws.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.emsi.meteo.app.ws.entities.UserEntity;
import com.emsi.meteo.app.ws.repositorys.UserRepository;
import com.emsi.meteo.app.ws.services.UserService;
import com.emsi.meteo.app.ws.shared.dto.UserDto;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils utils;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public UserDto createUser(UserDto user) {
		UserEntity checkUser = userRepository.findByEmail(user.getEmail());
		if (checkUser!=null) throw new RuntimeException("User already exist ! ");
		UserEntity userEntity = new UserEntity();
		
		BeanUtils.copyProperties(user, userEntity);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(utils.generateStringId(32));

		
		UserEntity newUser = userRepository.save(userEntity);
		
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(newUser, userDto);
		
		return userDto;
	}

}
