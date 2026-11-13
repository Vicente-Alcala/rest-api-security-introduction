package com.spring.spring_rest_security.exception;

/**
 * Custom unchecked exception thrown when a
 * requested student record cannot be found.
 */
public class StudentNotFoundException extends RuntimeException {

    /**
     * Serialization version identifier.
     */
    private static final long serialVersionUID = -2690100046087976236L;

    /**
     * Constructs a new {@code StudentNotFoundException}
     * with the specified detail message and cause.
     *
     * @param message the detail message explaining
     *                why the student was not found
     * @param cause   the underlying cause
     *                of the exception
     */
    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code StudentNotFoundException}
     * with the specified detail message.
     *
     * @param message the detail message explaining why
     *                the student was not found
     */
    public StudentNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code StudentNotFoundException}
     * with the specified cause.
     *
     * @param cause the underlying cause of the exception
     */
    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }
}
