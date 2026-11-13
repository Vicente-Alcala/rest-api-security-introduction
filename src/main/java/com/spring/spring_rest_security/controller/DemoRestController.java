package com.spring.spring_rest_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling basic test endpoints.
 * Mapped to the base path {@code /test}.
 */
@RestController
@RequestMapping("/test")
public class DemoRestController {

    /**
     * Handles HTTP GET requests to {@code /test/hello}.
     *
     * @return a greeting string ({@code "Hello World!"})
     */
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }
}
