package com.emsi.meteo.app.ws.services;

import com.emsi.meteo.app.ws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	 UserDto createUser(UserDto userDto) ;
	UserDto getUser(String email);
	UserDto getUserByUserId(String id);
	UserDto updateUser(String userId,UserDto userDto);
	void deleteUser(String userId);
}
