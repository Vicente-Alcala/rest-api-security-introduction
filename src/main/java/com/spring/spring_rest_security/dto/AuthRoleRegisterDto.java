package com.spring.spring_rest_security.dto;

/**
 * Data Transfer Object (DTO) representing the payload
 * submitted when registering a new authorization role.
 */
public class AuthRoleRegisterDto {

    private String name;

    public AuthRoleRegisterDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
