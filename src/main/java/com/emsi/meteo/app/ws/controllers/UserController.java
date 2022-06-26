package com.emsi.meteo.app.ws.controllers;

import com.emsi.meteo.app.ws.exceptions.UserException;
import com.emsi.meteo.app.ws.responses.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.emsi.meteo.app.ws.requests.UserRequest;
import com.emsi.meteo.app.ws.responses.UserResponse;
import com.emsi.meteo.app.ws.services.UserService;
import com.emsi.meteo.app.ws.shared.dto.UserDto;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")//localhost:8080/users
public class UserController {
	@Autowired
	UserService userService;//injection des depondance
	
	@GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})//la foction va produire XML LA SERIALSATION
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {

		UserDto userDto = userService.getUserByUserId(id);

		UserResponse userResponse = new UserResponse();

		BeanUtils.copyProperties(userDto, userResponse);

		return new  ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
	}
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<UserResponse> getAllUsers(@RequestParam(value = "page",defaultValue = "1") int page,
										  @RequestParam(value = "limit",defaultValue = "15") int limit){
		List<UserResponse> listUserResponse = new ArrayList<>();
		List<UserDto> users = userService.getUsers(page,limit);
		users.forEach(userDto ->{
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(userDto,userResponse);
			listUserResponse.add(userResponse);
		} );


		return listUserResponse;
	}

	@PostMapping(
			consumes =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {
		if (userRequest.getFirstName().isEmpty()
				||userRequest.getLastName().isEmpty()
		) throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		UserDto userDto = new UserDto(); 
		BeanUtils.copyProperties(userRequest, userDto);//COUCHE REPRESONTATION
		
		UserDto createUser = userService.createUser(userDto);//COUCHE SERVICE
		
		UserResponse userResponse = new UserResponse();
		
		BeanUtils.copyProperties(createUser, userResponse);
		
		return new  ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED) ; // RETOURNER LES INFORMATION VOULU DE LUTULISATEUR avec status 201
	}
	
	@PutMapping(path = "/{id}",
			consumes =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
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
