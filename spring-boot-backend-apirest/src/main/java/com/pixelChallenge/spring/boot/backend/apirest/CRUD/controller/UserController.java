package com.pixelChallenge.spring.boot.backend.apirest.CRUD.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.pixelChallenge.spring.boot.backend.apirest.CRUD.dto.ResponseSaveDto;
import com.pixelChallenge.spring.boot.backend.apirest.CRUD.dto.UserDto;
import com.pixelChallenge.spring.boot.backend.apirest.CRUD.entity.User;
import com.pixelChallenge.spring.boot.backend.apirest.CRUD.service.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
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
	public ResponseEntity<User> getOne(@PathVariable("id") String id) {
		try {
			User user = userService.getOneById(Integer.parseInt(id));
			if(user != null) {
				return ResponseEntity.ok(user);
			}else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}

	}
	
	@PostMapping("/verify")
	public ResponseEntity<ResponseSaveDto> verify(@RequestBody UserDto dto ) {
        Map<String, String> response = new HashMap<>();
		try {
			User user = this.userService.checkUser(dto);
			return this.proccesRequest(user);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}

	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseSaveDto> register(@RequestBody UserDto dto) {
        Map<String, String> response = new HashMap<>();
		try {
			User user = this.userService.insertOne(dto);
			return this.proccesRequest(user);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}

	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseSaveDto> update(@RequestBody UserDto dto) {
		try {
			User user = this.userService.update(dto);
			if(user != null) {
				return ResponseEntity.ok(prepareResponseSaveData(Boolean.toString(true)));
			}else {
				return ResponseEntity.internalServerError().build();
			}

		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	private ResponseEntity<ResponseSaveDto> proccesRequest(User user) {
		if(user != null) {
			return ResponseEntity.ok(this.prepareResponseSaveData(Integer.toString(user.getId())));
		}else {
			return ResponseEntity.ok(this.prepareResponseSaveData(""));
		}
	}
		
	private ResponseSaveDto prepareResponseSaveData(String value) {
		ResponseSaveDto responseSaveDto = new ResponseSaveDto(value);
		return responseSaveDto;
	}
	

	
}
