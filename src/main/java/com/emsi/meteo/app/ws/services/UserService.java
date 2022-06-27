package com.emsi.meteo.app.ws.services;

import com.emsi.meteo.app.ws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
	 UserDto createUser(UserDto userDto) ;
	UserDto getUser(String email);
	UserDto getUserByUserId(String id) throws UsernameNotFoundException;
	UserDto updateUser(String userId,UserDto userDto);
	void deleteUser(String userId);


    List<UserDto> getUsers(int page, int limit, String kw);
}
