package com.emsi.meteo.app.ws.services.impl;

import com.emsi.meteo.app.ws.shared.Utils;
import com.emsi.meteo.app.ws.shared.dto.AddressesDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import java.util.List;


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
		for (int i = 0; i <user.getAddresses().size() ; i++)
		{
			AddressesDto addressesDto = user.getAddresses().get(i);
			addressesDto.setUser(user);
			addressesDto.setAddresseId(utils.generateStringId(30));
			user.getAddresses().set(i,addressesDto);
		}
		user.getContact().setContactId(utils.generateStringId(30));
		user.getContact().setUser(user);

		ModelMapper modelMapper = new ModelMapper();

		UserEntity userEntity = modelMapper.map(user,UserEntity.class);



		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(utils.generateStringId(32));

		UserEntity newUser = userRepository.save(userEntity);
		
		UserDto userDto = modelMapper.map(newUser,UserDto.class);

		
		return userDto;
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) throw new UsernameNotFoundException(email);

		ModelMapper modelMapper = new ModelMapper();

		UserDto userDto = modelMapper.map(userEntity,UserDto.class);//copier les donne de userEntity to userDto

		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null) throw new UsernameNotFoundException(userId);

		ModelMapper modelMapper = new ModelMapper();

		UserDto userDto = modelMapper.map(userEntity,UserDto.class);//copier les donne de userEntity to userDto

		return userDto;
	}

	@Override
	public UserDto updateUser(String userId,UserDto userDto) {

		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null) throw new UsernameNotFoundException(userId);

		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());

		UserEntity userUpdated = userRepository.save(userEntity);

		ModelMapper modelMapper = new ModelMapper();

		UserDto user = modelMapper.map(userUpdated,UserDto.class);//copier les donne de userEntity to userDto

		return user;
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null) throw new UsernameNotFoundException(userId);

		userRepository.delete(userEntity);
	}

	@Override
	public List<UserDto> getUsers(int page, int limit, String kw,int status) {
		if (page>0)page-=1;
		List<UserDto> userDtoList = new ArrayList<>();
		Page<UserEntity> usersPage;
		if (kw.isEmpty()){
			usersPage = userRepository.findAllUsers(PageRequest.of(page, limit));
		}else {
			usersPage = userRepository.findByFirstNameAndLastName(PageRequest.of(page, limit),kw,status);
		}
		usersPage.getContent().forEach(userEntity -> {
			ModelMapper modelMapper = new ModelMapper();
			UserDto userDto = modelMapper.map(userEntity,UserDto.class);
			userDtoList.add(userDto);
		});
		return userDtoList;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),new ArrayList<>());
	}
}
