package com.spring.spring_rest_security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.spring_rest_security.entity.Auth_User;

/**
 * Spring Data JPA repository for managing {@link Auth_User} entities.
 */
public interface AuthUserRepository extends JpaRepository<Auth_User, Long> {

    /**
     * Retrieves an authenticated user by their unique username.
     *
     * @param theUsername the username of the user to search for
     * @return an {@link Optional} containing the matching
     * {@link Auth_User}, or empty if not found
     */
    Optional<Auth_User> findByUsername(String theUsername);

    /**
     * Checks whether a user with the given username already exists.
     *
     * @param theUsername the username to check
     * @return {@code true} if a user with the specified
     * username exists, {@code false} otherwise
     */
    Boolean existsByUsername(String theUsername);
}
