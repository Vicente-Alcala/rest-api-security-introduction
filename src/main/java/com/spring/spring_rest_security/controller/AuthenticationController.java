package com.spring.spring_rest_security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.spring_rest_security.dto.AuthRoleRegisterDto;
import com.spring.spring_rest_security.entity.Auth_Role;
import com.spring.spring_rest_security.repository.AuthRoleRepository;
import com.spring.spring_rest_security.repository.AuthUserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private AuthUserRepository authUserRepo;
    private AuthRoleRepository authRoleRepo;
    private BCryptPasswordEncoder passwordEncoder;

    public AuthenticationController(AuthenticationManager authenticationManager,
            AuthUserRepository authUserRepo, AuthRoleRepository authRoleRepo,
            BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.authUserRepo = authUserRepo;
        this.authRoleRepo = authRoleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registered_authorized_role")
    public ResponseEntity<String> register_authRole(
        @RequestBody AuthRoleRegisterDto authRoleRegisterDto) {
            if(authRoleRepo.existsByName(authRoleRegisterDto.getName())) {
                return new ResponseEntity<>(authRoleRegisterDto.getName() + " already created.",
                HttpStatus.BAD_REQUEST);
            }
        
        Auth_Role auth_role = new Auth_Role();
        auth_role.setName(authRoleRegisterDto.getName());

        authRoleRepo.save(auth_role);

        return new ResponseEntity<>(auth_role.getName() + " is successfully created",
        HttpStatus.OK);
    }
    
}
