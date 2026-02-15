package com.student.student_management_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) //Optional while using ResponseEntity
public class DuplicateMailException extends RuntimeException{

    public DuplicateMailException(String email){

        super("Email already exists: " + email);
    }
}
