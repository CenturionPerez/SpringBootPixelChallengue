package com.pixelChallenge.spring.boot.backend.apirest.CRUD.dto;

public class UserDto {
	private Integer id;
	private String name;
	private String email;
	private String nationality;
	private String rank;
	private String password;
	private String score;
	private String msisdn;
	
	public UserDto() {
		
	}

	public UserDto(Integer id, String name, String email, String nationality, String rank, String password, String score, String msisdn) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.nationality = nationality;
		this.rank = rank;
		this.password = password;
		this.score = score;
		this.msisdn = msisdn;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
}
