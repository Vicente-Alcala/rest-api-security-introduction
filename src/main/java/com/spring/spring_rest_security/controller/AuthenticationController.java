package com.spring.spring_rest_security.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication
.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.spring_rest_security.dto.AuthLoginDto;
import com.spring.spring_rest_security.dto.AuthRoleRegisterDto;
import com.spring.spring_rest_security.dto.AuthUserRegisterDto;
import com.spring.spring_rest_security.entity.Auth_Role;
import com.spring.spring_rest_security.entity.Auth_User;
import com.spring.spring_rest_security.repository.AuthRoleRepository;
import com.spring.spring_rest_security.repository.AuthUserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * REST controller handling authentication and authorization operations,
 * including role registration, user registration, and user login.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private AuthUserRepository authUserRepo;
    private AuthRoleRepository authRoleRepo;
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Constructs an {@code AuthenticationController}
     * with the required dependencies.
     *
     * @param authenticationManager
     * the manager used to authenticate user credentials
     * @param authUserRepo
     * the repository for accessing and persisting user entities
     * @param authRoleRepo
     * the repository for accessing and persisting role entities
     * @param passwordEncoder
     * the encoder used to hash user passwords
     */
    public AuthenticationController(
            AuthenticationManager authenticationManager,
            AuthUserRepository authUserRepo, AuthRoleRepository authRoleRepo,
            BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.authUserRepo = authUserRepo;
        this.authRoleRepo = authRoleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new authorization role in
     * the system if it does not already exist.
     *
     * @param authRoleRegisterDto the DTO containing
     * the name of the role to register
     * @return a {@link ResponseEntity} with HTTP 200
     * OK if created successfully, or HTTP 400 Bad Request
     * if the role already exists
     */
    @PostMapping("/register_authorized_role")
    public ResponseEntity<String> register_authRole(
        @RequestBody AuthRoleRegisterDto authRoleRegisterDto) {
            if(authRoleRepo.existsByName(authRoleRegisterDto.getName())) {
                return new ResponseEntity<>(
                    authRoleRegisterDto.getName() + " already created.",
                HttpStatus.BAD_REQUEST);
            }
        
        Auth_Role auth_role = new Auth_Role();
        auth_role.setName(authRoleRegisterDto.getName());

        authRoleRepo.save(auth_role);

        return new ResponseEntity<>(
            auth_role.getName() + " is successfully created",
        HttpStatus.OK);
    }

    /**
     * Registers a new user with an encoded password
     * and assigns the default {@code USER} role.
     *
     * @param authUserRegisterDto the DTO containing
     * the new user's username and raw password
     * @return a {@link ResponseEntity} with HTTP 200
     * OK if registration succeeds, or HTTP 400 Bad Request
     * if the username is already taken
     * @throws RuntimeException if the default {@code USER}
     * role is not found in the database
     */
    @PostMapping("/register_authorized_user")
    public ResponseEntity<String> register_authUser(
        @RequestBody AuthUserRegisterDto authUserRegisterDto) {
            if(authUserRepo.existsByUsername(
                authUserRegisterDto.getUsername())) {
                return new ResponseEntity<>(
                    authUserRegisterDto.getUsername() + " is already taken.",
                HttpStatus.BAD_REQUEST);
            }
        
        Auth_User auth_user = new Auth_User();
        auth_user.setUsername(authUserRegisterDto.getUsername());
        auth_user.setPassword(passwordEncoder.encode(
            authUserRegisterDto.getPassword()));

        Auth_Role auth_role = authRoleRepo.findByName(
            "USER").orElseThrow(
                () -> new RuntimeException("Roles USER not found"));
        auth_user.setRoles(Collections.singletonList(auth_role));

        authUserRepo.save(auth_user);

        return new ResponseEntity<>(
            auth_user.getUsername() + " is successfully registered",
        HttpStatus.OK);
    }

    /**
     * Authenticates a user using their username
     * and password and stores the resulting
     * {@link Authentication} object in the
     * {@link SecurityContextHolder}.
     *
     * @param authLoginDto the DTO containing
     * the user's login credentials
     * @return a {@link ResponseEntity} with HTTP 200
     * OK upon successful authentication
     */
    @PostMapping("/auth_login")
    public ResponseEntity<String> login(
        @RequestBody AuthLoginDto authLoginDto) {
        System.out.println("Username: " + authLoginDto.getUsername());
        System.out.println("Password: " + authLoginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authLoginDto.getUsername(),
        authLoginDto.getPassword()));
        System.out.println("2 Username: " + authLoginDto.getUsername());
        System.out.println("2 Password: " + authLoginDto.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return new ResponseEntity<>("Sign in successfully!",HttpStatus.OK);
    }
}
