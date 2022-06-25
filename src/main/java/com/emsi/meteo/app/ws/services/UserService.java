package com.emsi.meteo.app.ws.services;

import com.emsi.meteo.app.ws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
	 UserDto createUser(UserDto userDto) ;
	UserDto getUser(String email);
	UserDto getUserByUserId(String id) throws UsernameNotFoundException;
	UserDto updateUser(String userId,UserDto userDto);
	void deleteUser(String userId);
}
