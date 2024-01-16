package com.pixelChallenge.spring.boot.backend.apirest.CRUD.dto;

import java.util.Map;

public class ResponseSaveDto {
	
	public Map<String, Boolean> response;
	
	public ResponseSaveDto(Map<String, Boolean> response) {
		this.response = response;
	}
	
    public Map<String, Boolean> getResponse() {
        return response;
    }

    public void setResponse(String name, Boolean value) {
        this.response.put(name, value);
    }
}
