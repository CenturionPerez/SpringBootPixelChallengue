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
	
	public User checkUser(UserDto dto) {
		List<User> usersList = userRepository.findAll();
		if(!usersList.isEmpty()) {
			User user = usersList.stream().filter((User userIterable) -> userIterable.getEmail().equals(dto.getEmail())).findFirst().orElse(null);
			if(user != null && user.getPassword().equals(dto.getPassword())) {
				return user;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
	
	public User insertOne(UserDto dto) {
		List<User> usersList = userRepository.findAll();
		if(!usersList.isEmpty()) {
			User userDetected = usersList.stream().filter((User userIterable) -> userIterable.getEmail().equals(dto.getEmail())).findFirst().orElse(null);
			if(userDetected != null) {
				return null;
			}else {
				return userRepository.save(generateUser(dto));
			}
		}else {
			return userRepository.save(generateUser(dto));
		}
	}
	
	public User update(UserDto dto) {
		User user = userRepository.findById(dto.getId()).orElse(null);
		if(user == null) {
			return null;
		}else {
			return userRepository.save(this.prepareValuesUserUpdate(user, dto));
		}
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
	
	private User generateUser(UserDto dto) {
		return new User(
			this.autoIncrement(),
			dto.getName(),
			dto.getEmail(),
			dto.getNationality(),
			dto.getRank(),
			dto.getPassword(),
			dto.getScore(),
			dto.getMsisdn()
		);
	}
	
	private User prepareValuesUserUpdate(User user,UserDto dto) {
		if(dto.getEmail() != null && !dto.getEmail().isBlank() && !user.getEmail().contains(dto.getEmail())) {
			user.setEmail(dto.getEmail());
		}
		if(dto.getName() != null && !dto.getName().isBlank() && !user.getName().contains(dto.getName())) {
			user.setName(dto.getName());
		}
		if(dto.getPassword() != null && !dto.getPassword().isBlank() && !user.getPassword().contains(dto.getPassword())) {
			user.setPassword(dto.getPassword());
		}
		return user;
	}
	
}
