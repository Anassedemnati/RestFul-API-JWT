package com.emsi.meteo.app.ws.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emsi.meteo.app.ws.requests.UserRequest;
import com.emsi.meteo.app.ws.responses.UserResponse;
import com.emsi.meteo.app.ws.services.UserService;
import com.emsi.meteo.app.ws.shared.dto.UserDto;

@RestController
@RequestMapping("/users")//localhost:8080/users
public class UserController {
	@Autowired
	UserService userService;//injection des depondance
	
	@GetMapping
	public String getUser() {
		return "get list of users ...";
	}
	@PostMapping
	public UserResponse createUser(@RequestBody UserRequest userRequest) {
		UserDto userDto = new UserDto(); 
		BeanUtils.copyProperties(userRequest, userDto);//COUCHE REPRESONTATION
		
		UserDto createUser = userService.createUser(userDto);//COUCHE SERVICE
		
		UserResponse userResponse = new UserResponse();
		
		BeanUtils.copyProperties(createUser, userResponse);
		
		return userResponse; // RETOURNER LES INFORMATION VOULU DE LUTULISATEUR 
	}
	
	@PutMapping
	public String updateUser() {
		return " user updated !";
	}
	@DeleteMapping
	public String deleteUser() {
		return "delete user !";
	}


}
