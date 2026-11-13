package com.spring.spring_rest_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.spring_rest_security.entity.Student;

/**
 * Spring Data JPA repository interface for
 * performing CRUD and persistence operations
 * on {@link Student} entities.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}
