package com.spring.spring_rest_security.mapper;

import com.spring.spring_rest_security.entity.Student;
import com.spring.spring_rest_security.dto.StudentDto;

/**
 * Utility class for converting between Student entities
 * and StudentDto objects.
 */
public class StudentMapper {

    /**
     * Converts a Student entity into a StudentDto.
     * @return the corresponding student DTO
     */
    public static StudentDto mapToStudentDto(Student theStudent) {
        return new StudentDto(theStudent.getId(), 
        theStudent.getFirstName(), 
        theStudent.getLastName(), 
        theStudent.getEmail()
        );
    }

    /**
     * Converts a StudentDto entity into a Student.
     * @return the corresponding student
     */
    public static Student mapToStudent(StudentDto theStudentDto) {
        return new Student(theStudentDto.getId(), 
        theStudentDto.getFirstName(), 
        theStudentDto.getLastName(), 
        theStudentDto.getEmail()
        );
    }
}
