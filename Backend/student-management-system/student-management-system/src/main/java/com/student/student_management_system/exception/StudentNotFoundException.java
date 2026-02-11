package com.student.student_management_system.exception;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(Long id){
        super("Student is not found with id: " + id);
    }
}
