package com.emsi.meteo.app.ws.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.emsi.meteo.app.ws.requests.UserRequest;
import com.emsi.meteo.app.ws.responses.UserResponse;
import com.emsi.meteo.app.ws.services.UserService;
import com.emsi.meteo.app.ws.shared.dto.UserDto;

@RestController
@RequestMapping("/users")//localhost:8080/users
public class UserController {
	@Autowired
	UserService userService;//injection des depondance
	
	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) {

		UserDto userDto = userService.getUserByUserId(id);

		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(userDto, userResponse);

		return userResponse;
	}


	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
		UserDto userDto = new UserDto(); 
		BeanUtils.copyProperties(userRequest, userDto);//COUCHE REPRESONTATION
		
		UserDto createUser = userService.createUser(userDto);//COUCHE SERVICE
		
		UserResponse userResponse = new UserResponse();
		
		BeanUtils.copyProperties(createUser, userResponse);
		
		return new  ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED) ; // RETOURNER LES INFORMATION VOULU DE LUTULISATEUR avec status 201
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id,@RequestBody UserRequest userRequest) {
		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userRequest, userDto);//COUCHE REPRESONTATION

		UserDto updateUser = userService.updateUser(id,userDto);//COUCHE SERVICE

		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(updateUser, userResponse);

		return new  ResponseEntity<UserResponse>(userResponse,HttpStatus.ACCEPTED); // RETOURNER LES INFORMATION VOULU DE LUTULISATEUR avec status 202
	}
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {

		userService.deleteUser(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


}
