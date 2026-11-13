package com.spring.spring_rest_security.service;

import java.util.List;

import com.spring.spring_rest_security.dto.StudentDto;

/**
 * Service interface defining business operations
 * for managing {@link StudentDto} resources.
 */
public interface StudentService {

    /**
     * Creates and persists a new student record.
     *
     * @param theStudentDto the student data transfer
     *                      object to save
     * @return the saved {@link StudentDto} including
     *         its generated identifier
     */
    StudentDto createStudent(StudentDto theStudentDto);

    /**
     * Retrieves a student record by its unique identifier.
     *
     * @param theId the ID of the student to retrieve
     * @return the matching {@link StudentDto}
     */
    StudentDto getStudentById(Long theId);

    /**
     * Retrieves all student records.
     *
     * @return a {@link List} of all {@link StudentDto} objects
     */
    List<StudentDto> getAllStudents();

    /**
     * Completely updates an existing student record with new details.
     *
     * @param theId         the ID of the student to update
     * @param theStudentDto the student data transfer object
     *                      containing updated values
     * @return the updated {@link StudentDto}
     */
    StudentDto updateStudent(Long theId, StudentDto theStudentDto);

    /**
     * Partially updates an existing student record, modifying only provided
     * non-null fields.
     *
     * @param theId         the ID of the student to partially update
     * @param theStudentDto the student data transfer object
     *                      containing fields to update
     * @return the updated {@link StudentDto}
     */
    StudentDto patchStudent(Long theId, StudentDto theStudentDto);

    /**
     * Deletes a student record by its unique identifier.
     *
     * @param theId the ID of the student to delete
     */
    void deleteStudentById(Long theId);
}
