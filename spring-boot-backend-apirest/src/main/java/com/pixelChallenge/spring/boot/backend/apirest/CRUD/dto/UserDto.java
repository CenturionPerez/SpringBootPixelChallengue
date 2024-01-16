package com.pixelChallenge.spring.boot.backend.apirest.CRUD.dto;

public class UserDto {
	private String name;
	private String email;
	private String nationality;
	private String rank;
	private String password;
	
	public UserDto() {
		
	}

	public UserDto(String name, String email, String nationality, String rank, String password) {
		this.name = name;
		this.email = email;
		this.nationality = nationality;
		this.rank = rank;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
