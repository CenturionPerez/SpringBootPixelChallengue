package com.pixelChallenge.spring.boot.backend.apirest.CRUD.dto;

import java.util.Map;

public class ResponseSaveDto {
	
	public Map<String, String> response;
	
	public ResponseSaveDto(Map<String, String> response) {
		this.response = response;
	}
	
    public Map<String, String> getResponse() {
        return response;
    }

    public void setResponse(String name, String value) {
        this.response.put(name, value);
    }
}
