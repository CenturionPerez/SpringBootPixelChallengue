package com.pixelChallenge.spring.boot.backend.apirest.CRUD.dto;

public class ResponseSaveDto {

    private String data;

    public ResponseSaveDto(String value) {
        this.data = value;
    }

    public String getData() {
        return data;
    }

    public void setData(String value) {
        this.data = value;
    }
}
