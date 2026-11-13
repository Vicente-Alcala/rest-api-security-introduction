package com.spring.spring_rest_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Spring Boot REST API application.
 * Bootstraps the application context, auto-configuration,
 * and embedded web server.
 */
@SpringBootApplication
public class SpringRestApplication {

	/**
	 * Launches the Spring Boot application.
	 *
	 * @param args command-line arguments passed during application startup
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

}
