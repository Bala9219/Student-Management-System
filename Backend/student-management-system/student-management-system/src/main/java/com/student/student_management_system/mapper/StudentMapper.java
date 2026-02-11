package com.student.student_management_system.mapper;

import com.student.student_management_system.dto.StudentRequestDTO;
import com.student.student_management_system.dto.StudentResponseDTO;
import com.student.student_management_system.model.Student;

public class StudentMapper {

    public static Student toEntity(StudentRequestDTO dto){
        return Student.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .course(dto.getCourse())
                .build();
    }

    public static StudentResponseDTO toDTO(Student student){
        return StudentResponseDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .age(student.getAge())
                .course(student.getCourse())
                .build();
    }
}
