package com.tsdigisol.proj.controller.user;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsdigisol.proj.controller.user.dto.UserDTO;
import com.tsdigisol.proj.exception.ServiceException;
import com.tsdigisol.proj.service.user.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	private static Logger logger = Logger.getLogger(UserController.class);
	private ObjectMapper mapper;

	public UserController() {
		logger.info("Created " + this.getClass().getSimpleName());
		this.mapper = new ObjectMapper();
	}

	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
		try {
			userService.createUser(userDTO);
			return new ResponseEntity<String>("User created successfully.", HttpStatus.CREATED);
		} catch (ServiceException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
		try {
			userService.updateUser(userDTO);
			return new ResponseEntity<String>("User updated successfully.", HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<String> getById(@PathVariable int id) {
		try {
			UserDTO userDTO = userService.getUserById(id);
			return new ResponseEntity<String>(mapper.writeValueAsString(userDTO), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<String> getAll() {
		try {
			List<UserDTO> userDTOs = userService.getAllUser();
			return new ResponseEntity<String>(mapper.writeValueAsString(userDTOs), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
		try {
			userService.delete(id);
			return new ResponseEntity<String>("User updated successfully.", HttpStatus.CREATED);
		} catch (ServiceException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
