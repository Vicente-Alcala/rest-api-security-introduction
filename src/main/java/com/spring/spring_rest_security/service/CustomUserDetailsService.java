package com.spring.spring_rest_security.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.spring_rest_security.entity.Auth_Role;
import com.spring.spring_rest_security.entity.Auth_User;
import com.spring.spring_rest_security.repository.AuthUserRepository;

/**
 * Custom implementation of Spring Security's
 * {@link UserDetailsService} that loads
 * user-specific authentication and authorization
 * data from the database.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepo;

    /**
     * Constructs a {@code CustomUserDetailsService}
     * with the specified {@link AuthUserRepository}.
     *
     * @param authUserRepo the repository used to
     * retrieve {@link Auth_User} entities
     */
    public CustomUserDetailsService(AuthUserRepository authUserRepo) {
        this.authUserRepo = authUserRepo;
    }

    /**
     * Locates a user by their username and converts their
     * stored credentials and roles into a Spring Security
     * {@link UserDetails} object.
     *
     * @param theUsername the username identifying
     * the user whose data is required
     * @return a fully populated {@link UserDetails} record
     * @throws UsernameNotFoundException if no
     * user is found with the given username
     */
    public UserDetails loadUserByUsername(String theUsername) {
        Auth_User user = authUserRepo.findByUsername(theUsername)
                .orElseThrow(
                    () -> new UsernameNotFoundException("Username " +
                        theUsername + "does not exist")
                );
        return new User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    /**
     * Converts a list of {@link Auth_Role} entities into a
     * collection of Spring Security {@link GrantedAuthority} instances.
     *
     * @param roles the list of {@link Auth_Role} entities assigned to the user
     * @return a collection of {@link SimpleGrantedAuthority}
     * objects corresponding to the role names
     */
    private Collection<GrantedAuthority> mapRolesToAuthorities(
        List<Auth_Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(
            role.getName())).collect(Collectors.toList());
    }


}
