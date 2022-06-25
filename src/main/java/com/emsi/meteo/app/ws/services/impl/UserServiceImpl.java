package com.emsi.meteo.app.ws.services.impl;

import com.emsi.meteo.app.ws.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.emsi.meteo.app.ws.entities.UserEntity;
import com.emsi.meteo.app.ws.repositorys.UserRepository;
import com.emsi.meteo.app.ws.services.UserService;
import com.emsi.meteo.app.ws.shared.dto.UserDto;

import java.util.ArrayList;


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

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) throw new UsernameNotFoundException(email);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity,userDto);//copier les donne de userEntity to userDto
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null) throw new UsernameNotFoundException(userId);

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userEntity,userDto);//copier les donne de userEntity to userDto

		return userDto;
	}

	@Override
	public UserDto updateUser(String userId,UserDto userDto) {

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null) throw new UsernameNotFoundException(userId);

		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());

		UserEntity userUpdated = userRepository.save(userEntity);

		UserDto user = new UserDto();

		BeanUtils.copyProperties(userUpdated,user);//copier les donne de userEntity to userDto

		return user;
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null) throw new UsernameNotFoundException(userId);

		userRepository.delete(userEntity);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),new ArrayList<>());
	}
}
