package com.spring.spring_rest_security.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.spring_rest_security.entity.StudentErrorResponse;

/**
 * Global exception handler for intercepting
 * exceptions thrown across REST controllers
 * and returning structured JSON error responses.
 */
@ControllerAdvice
public class StudentExceptionHandler {

    /**
     * Handles {@link StudentNotFoundException} when a
     * requested student record does not exist.
     *
     * @param ex the thrown {@code StudentNotFoundException}
     * @return a {@link ResponseEntity} containing a
     *         {@link StudentErrorResponse} with HTTP status
     *         {@code 404 Not Found}
     */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(
            StudentNotFoundException ex) {

        StudentErrorResponse err = new StudentErrorResponse();

        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setMessage(ex.getMessage());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles generic exceptions, such as path variable
     * type mismatches where non-integer IDs are provided.
     *
     * @param ex the thrown {@code Exception}
     * @return a {@link ResponseEntity} containing a
     *         {@link StudentErrorResponse} with HTTP status
     *         {@code 400 Bad Request}
     */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(
            Exception ex) {

        StudentErrorResponse err = new StudentErrorResponse();

        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setMessage("Invalid data. Only integers are accepted");
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles bean validation failures triggered
     * by {@code @Valid} on request bodies.
     * Extracts field-level validation errors and maps
     * each field name to its default error message.
     *
     * @param ex the thrown {@link MethodArgumentNotValidException}
     * @return a {@link ResponseEntity} containing a map of field
     *         names to error messages with HTTP status {@code 400 Bad Request}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> err = new HashMap<>();

        // extract every field error and its custom message
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            err.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

}
