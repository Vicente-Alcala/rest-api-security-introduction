package com.spring.spring_rest_security.dto;

public class AuthUserRegisterDto {

    private String username;
    private String password;

    public AuthUserRegisterDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
