package com.tsdigisol.proj.controller.address;

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
import com.tsdigisol.proj.controller.address.dto.AddressDTO;
import com.tsdigisol.proj.exception.ServiceException;
import com.tsdigisol.proj.service.address.IAddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private IAddressService addressService;
	private static Logger logger = Logger.getLogger(AddressController.class);
	private ObjectMapper mapper;

	public AddressController() {
		logger.info("Created " + this.getClass().getSimpleName());
		this.mapper = new ObjectMapper();
	}

	@PostMapping("/create")
	public ResponseEntity<String> createAddress(@RequestBody AddressDTO addressDTO) {
		try {
			addressService.createAddress(addressDTO);
			return new ResponseEntity<String>("Address created successfully.", HttpStatus.CREATED);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody AddressDTO addressDTO) {
		try {
			addressService.update(addressDTO);
			return new ResponseEntity<String>("Address updated successfully.", HttpStatus.CREATED);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<String> getById(@PathVariable int id) {
		try {
			AddressDTO addressDTO = addressService.getById(id);
			return new ResponseEntity<String>(mapper.writeValueAsString(addressDTO), HttpStatus.CREATED);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<String> getAll() {
		try {
			List<AddressDTO> addressDTOs = addressService.getAllAddress();
			return new ResponseEntity<String>(mapper.writeValueAsString(addressDTOs), HttpStatus.CREATED);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		try {
			addressService.deteteAddress(id);
			return new ResponseEntity<String>("Address deleted successfully.", HttpStatus.CREATED);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
