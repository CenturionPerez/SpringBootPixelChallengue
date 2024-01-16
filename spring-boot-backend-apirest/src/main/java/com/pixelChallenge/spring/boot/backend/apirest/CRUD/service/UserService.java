package com.pixelChallenge.spring.boot.backend.apirest.CRUD.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixelChallenge.spring.boot.backend.apirest.CRUD.dto.UserDto;
import com.pixelChallenge.spring.boot.backend.apirest.CRUD.entity.User;
import com.pixelChallenge.spring.boot.backend.apirest.CRUD.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	public User getOneById(int id) {
		return userRepository.findById(id).get();
	}
	
	public User insertOne(UserDto dto) {
		User user = new User(
			this.autoIncrement(),
			dto.getName(),
			dto.getEmail(),
			dto.getNationality(),
			dto.getPassword(),
			dto.getRank()
		);
		return userRepository.save(user);
	}
	
	public User update(int id, UserDto dto) {
		User user = userRepository.findById(id).get();
		user.setEmail(dto.getEmail());
		user.setName(dto.getName());
		user.setPassword(dto.getPassword());
		user.setNationality(dto.getNationality());
		return userRepository.save(user);
	}
	
	public User delete(int id) {
		User user = userRepository.findById(id).get();
		userRepository.delete(user);
		return user;
	}
	
	// Metodos privados
	private int autoIncrement() {
		List<User> users = userRepository.findAll();
		return users.isEmpty()? 1 : 
			users.stream().max(Comparator.comparing(User::getId)).get().getId() +1;
	}
	
}
