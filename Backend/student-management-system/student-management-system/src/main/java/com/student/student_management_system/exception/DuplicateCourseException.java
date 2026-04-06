package com.student.student_management_system.exception;

public class DuplicateCourseException extends RuntimeException{

    public DuplicateCourseException(String title){
        super("Course with title name already exists: " + title);
    }
}
