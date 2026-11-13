package com.spring.spring_rest_security.mapper;

import com.spring.spring_rest_security.dto.StudentDto;
import com.spring.spring_rest_security.entity.Student;

/**
 * Utility mapper class responsible for converting between {@link Student} JPA
 * entities
 * and {@link StudentDto} data transfer objects.
 */
public class StudentMapper {

    /**
     * Converts a {@link Student} entity into a {@link StudentDto}.
     *
     * @param theStudent the {@code Student} entity to convert
     * @return the corresponding {@code StudentDto}
     *         containing the student's data
     */
    public static StudentDto mapToStudentDto(Student theStudent) {
        return new StudentDto(theStudent.getId(),
                theStudent.getFirstName(),
                theStudent.getLastName(),
                theStudent.getEmail());
    }

    /**
     * Converts a {@link StudentDto} into a {@link Student} entity.
     *
     * @param theStudentDto the {@code StudentDto} to convert
     * @return the corresponding {@code Student} entity ready for persistence
     */
    public static Student mapToStudent(StudentDto theStudentDto) {
        return new Student(theStudentDto.getId(),
                theStudentDto.getFirstName(),
                theStudentDto.getLastName(),
                theStudentDto.getEmail());
    }
}
