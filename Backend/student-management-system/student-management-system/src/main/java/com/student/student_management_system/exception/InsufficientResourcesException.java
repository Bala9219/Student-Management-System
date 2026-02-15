package com.student.student_management_system.exception;

public class InsufficientResourcesException extends RuntimeException{

    public InsufficientResourcesException(String message){
        super(message);
    }
}
