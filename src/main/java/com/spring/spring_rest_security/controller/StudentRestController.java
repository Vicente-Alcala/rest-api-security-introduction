package com.spring.spring_rest_security.controller;

import org.springframework.web.bind.annotation.RestController;

import com.spring.spring_rest_security.dto.StudentDto;
import com.spring.spring_rest_security.service.StudentService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * REST controller for managing student resources.
 * Exposes CRUD endpoints mapped under the base path
 * {@code /api/students}.
 */
@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private StudentService studentService;

    /**
     * Constructs a new {@code StudentRestController} with
     * the specified service. @param studentService the service
     * layer handling student business logic
     */
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Creates a new student record from
     * the provided request payload.
     *
     * @param theStudentDto the validated student data transfer object
     * @return a {@link ResponseEntity} containing the created
     *         {@link StudentDto} and HTTP status {@code 201 Created}
     */
    @PostMapping
    public ResponseEntity<StudentDto> createStudents(
            @Valid @RequestBody StudentDto theStudentDto) {
        StudentDto saved = studentService.createStudent(theStudentDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * Retrieves a single student by their unique identifier.
     *
     * @param id the ID of the student to retrieve
     * @return a {@link ResponseEntity} containing
     *         the matching {@link StudentDto} and HTTP status
     *         {@code 200 OK}
     */
    @GetMapping("{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        StudentDto studentDto = studentService.getStudentById(id);
        return ResponseEntity.ok(studentDto);
    }

    /**
     * Retrieves all student records.
     *
     * @return a {@link ResponseEntity} containing
     *         a list of all {@link StudentDto} objects and
     *         HTTP status {@code 200 OK}
     */
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    /**
     * Completely updates an existing student record by ID.
     *
     * @param id            the ID of the student to update
     * @param theStudentDto the validated student data
     *                      transfer object containing
     *                      updated fields
     * @return a {@link ResponseEntity} containing the updated
     *         {@link StudentDto} and HTTP status {@code 200 OK}
     */
    @PutMapping("{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id,
            @Valid @RequestBody StudentDto theStudentDto) {
        StudentDto studentDto = studentService.updateStudent(
                id, theStudentDto);
        return ResponseEntity.ok(studentDto);
    }

    /**
     * Partially updates an existing student
     * record by ID, modifying only non-null
     * fields provided in the payload.
     *
     * @param id            the ID of the student to
     *                      partially update
     * @param theStudentDto the validated student data
     *                      transfer object containing
     *                      fields to update
     * @return a {@link ResponseEntity} containing the
     *         updated {@link StudentDto} and HTTP status
     *         {@code 200 OK}
     */
    @PatchMapping("{id}")
    public ResponseEntity<StudentDto> patchStudent(@PathVariable Long id,
            @Valid @RequestBody StudentDto theStudentDto) {
        StudentDto studentDto = studentService.patchStudent(
                id, theStudentDto);
        return ResponseEntity.ok(studentDto);
    }

    /**
     * Deletes a student record by their unique identifier.
     *
     * @param id the ID of the student to delete
     * @return a {@link ResponseEntity} containing a confirmation
     *         message and HTTP status {@code 200 OK}
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok("Student deleted successfully!");
    }

}
