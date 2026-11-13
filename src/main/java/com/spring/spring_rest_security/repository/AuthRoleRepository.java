package com.spring.spring_rest_security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.spring_rest_security.entity.Auth_Role;

/**
 * Spring Data JPA repository for managing {@link Auth_Role} entities.
 */
public interface AuthRoleRepository extends JpaRepository<Auth_Role, Long> {

    /**
     * Retrieves an authorization role by its unique name.
     *
     * @param theName the name of the role to search for
     * @return an {@link Optional} containing the matching
     * {@link Auth_Role}, or empty if not found
     */
    Optional<Auth_Role> findByName(String theName);

    /**
     * Checks whether an authorization role
     * with the given name already exists.
     *
     * @param theUsername the name of the role to check
     * @return {@code true} if a role with the specified
     * name exists, {@code false} otherwise
     */
    Boolean existsByName(String theUsername);
}
