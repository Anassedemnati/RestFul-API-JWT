package com.emsi.meteo.app.ws.controllers;

import com.emsi.meteo.app.ws.exceptions.UserException;
import com.emsi.meteo.app.ws.responses.ErrorMessages;
import org.modelmapper.ModelMapper;
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
@CrossOrigin(origins = "*") //autorize all client on the methods of controller
@RestController
@RequestMapping("/users")//localhost:8080/users
public class UserController {
	@Autowired
	UserService userService;//injection des depondance
	
	@GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})//la foction va produire XML LA SERIALSATION
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {

		UserDto userDto = userService.getUserByUserId(id);

		ModelMapper modelMapper = new ModelMapper();
		UserResponse userResponse = modelMapper.map(userDto,UserResponse.class);

		return new  ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
	}
	//@CrossOrigin(origins = "*") autorize all client in this method
	//@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4000"})
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<UserResponse> getAllUsers(@RequestParam(value = "page",defaultValue = "1") int page,
										  @RequestParam(value = "limit",defaultValue = "15") int limit,
										  @RequestParam(value = "keyword",defaultValue = "")String keyword,
										  @RequestParam(value = "status",defaultValue = "1")int status){
		List<UserResponse> listUserResponse = new ArrayList<>();
		List<UserDto> users = userService.getUsers(page,limit,keyword,status);
		users.forEach(userDto ->{
			ModelMapper modelMapper = new ModelMapper();
			UserResponse userResponse = modelMapper.map(userDto,UserResponse.class);
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
		//UserDto userDto = new UserDto();
		//BeanUtils.copyProperties(userRequest, userDto);//COUCHE REPRESONTATION
		ModelMapper modelMapper = new ModelMapper();

		UserDto userDto = modelMapper.map(userRequest,UserDto.class);//COUCHE REPRESONTATION

		UserDto createUser = userService.createUser(userDto);//COUCHE SERVICE

		UserResponse userResponse = modelMapper.map(createUser,UserResponse.class);
		
		return new  ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED) ; // RETOURNER LES INFORMATION VOULU DE LUTULISATEUR avec status 201
	}
	
	@PutMapping(path = "/{id}",
			consumes =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces =  {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id,@RequestBody UserRequest userRequest) {
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto =modelMapper.map(userRequest,UserDto.class);//COUCHE REPRESONTATION

		UserDto updateUser = userService.updateUser(id,userDto);//COUCHE SERVICE


		UserResponse userResponse = modelMapper.map(updateUser,UserResponse.class);

		return new  ResponseEntity<UserResponse>(userResponse,HttpStatus.ACCEPTED); // RETOURNER LES INFORMATION VOULU DE LUTULISATEUR avec status 202
	}
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {

		userService.deleteUser(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


}
