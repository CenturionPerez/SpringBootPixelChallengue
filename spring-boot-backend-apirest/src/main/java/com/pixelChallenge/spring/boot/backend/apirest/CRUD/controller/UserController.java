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
		return this.proccesRequest(dto);
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseSaveDto> register(@RequestBody UserDto dto) {
		return this.proccesRequest(dto);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseSaveDto> update(@RequestBody UserDto dto) {
		return this.proccesRequest(dto);
	}
	
	private ResponseEntity<ResponseSaveDto> proccesRequest(UserDto dto) {
        Map<String, Boolean> response = new HashMap<>();
		try {
			User user = userService.insertOne(dto);
			if(user != null) {
				response.put("data", true);
				return ResponseEntity.ok(this.prepareResponseSaveData(response));
			}else {
				response.put("data", true);
				return ResponseEntity.ok(this.prepareResponseSaveData(response));
			}
		} catch (Exception e) {
			response.put("data", false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(this.prepareResponseSaveData(response));
		}	
	}
	
	private ResponseSaveDto prepareResponseSaveData(Map<String, Boolean> data) {
		return new ResponseSaveDto(data);
	}
	

	
}
