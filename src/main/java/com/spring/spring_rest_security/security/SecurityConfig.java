package com.spring.spring_rest_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.spring.spring_rest_security.service.CustomUserDetailsService;

/**
 * Spring Security configuration class defining
 * authentication, password encoding,
 * and HTTP security filter chain rules for the application.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private CustomUserDetailsService userDetailsService;

    /**
     * Constructs a {@code SecurityConfig} with the
     * specified {@link CustomUserDetailsService}.
     *
     * @param userDetailsService the custom service
     * used to load user-specific authentication data
     */
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.setUserDetailsService(userDetailsService);
    }

    /**
     * Returns the configured {@link CustomUserDetailsService}.
     *
     * @return the custom user details service
     */
    public CustomUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    /**
     * Sets the {@link CustomUserDetailsService} for this configuration.
     *
     * @param userDetailsService the custom user details service to set
     */
    public void setUserDetailsService(
        CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Configures the HTTP security filter chain,
     * disabling CSRF protection, permitting
     * unauthenticated access to {@code /api/auth/**}
     * endpoints, requiring authentication
     * for all other requests, and enabling HTTP Basic authentication.
     *
     * @param security the {@link HttpSecurity} builder to configure
     * @return the built {@link SecurityFilterChain}
     * @throws Exception if an error occurs while configuring HTTP security
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity security)
            throws Exception {
        return security
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                    "/api/auth/**").permitAll().anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    /**
     * Provides a {@link BCryptPasswordEncoder} bean
     * for hashing and verifying user passwords.
     *
     * @return a new {@link BCryptPasswordEncoder} instance
     */
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Exposes the {@link AuthenticationManager} bean from the Spring Security
     * {@link AuthenticationConfiguration}.
     *
     * @param authentication the {@link AuthenticationConfiguration}
     * used to retrieve the manager
     * @return the configured {@link AuthenticationManager}
     * @throws Exception if an error occurs while
     * retrieving the authentication manager
     */
    @Bean
    AuthenticationManager authenticationManager(
        AuthenticationConfiguration authentication) throws Exception {
        return authentication.getAuthenticationManager();
    }
}
