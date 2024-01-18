package com.pixelChallenge.spring.boot.backend.apirest.CRUD.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pixelChallenge.spring.boot.backend.apirest.CRUD.dto.ResponseSaveDto;
import com.pixelChallenge.spring.boot.backend.apirest.CRUD.dto.UserDto;
import com.pixelChallenge.spring.boot.backend.apirest.CRUD.entity.User;
import com.pixelChallenge.spring.boot.backend.apirest.CRUD.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	private static final String REGISTER = "register";
	private static final String UPDATE = "update";
	
	@Autowired
	UserService userService;

	
	@GetMapping("/classification")
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(userService.getAll());
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getOne(@PathVariable("id") int id) {
		return ResponseEntity.ok(userService.getOneById(id));
	}
	
	@PostMapping("/verify") //Terminar de hacer el verify porque debemos pasarle email y password no el id
	public ResponseEntity<ResponseSaveDto> verify(@RequestBody UserDto dto ) {
        Map<String, Boolean> response = new HashMap<>();
		try {
			User user = this.userService.checkUser(dto);
			return this.proccesRequest(user);
		} catch (Exception e) {
			response.put("data", false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(this.prepareResponseSaveData(response));
		}

	}
	
	@PostMapping(value = "/register" , consumes = "application/json")
	public ResponseEntity<ResponseSaveDto> register(@RequestBody UserDto dto) {
        Map<String, Boolean> response = new HashMap<>();
		try {
			User user = this.userService.insertOne(dto);
			return this.proccesRequest(user);
		} catch (Exception e) {
			response.put("data", false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(this.prepareResponseSaveData(response));
		}

	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseSaveDto> update(@RequestBody UserDto dto) {
        Map<String, Boolean> response = new HashMap<>();
		try {
			User user = this.userService.update(dto);
			return this.proccesRequest(user);
		} catch (Exception e) {
			response.put("data", false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(this.prepareResponseSaveData(response));
		}
	}
	
	private ResponseEntity<ResponseSaveDto> proccesRequest(User user) {
        Map<String, Boolean> response = new HashMap<>();
		if(user != null) {
			response.put("data", true);
			return ResponseEntity.ok(this.prepareResponseSaveData(response));
		}else {
			response.put("data", false);
			return ResponseEntity.ok(this.prepareResponseSaveData(response));
		}
	}
		
	private ResponseSaveDto prepareResponseSaveData(Map<String, Boolean> data) {
		return new ResponseSaveDto(data);
	}
	

	
}
