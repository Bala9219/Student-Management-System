package com.student.student_management_system.mapper;

import com.student.student_management_system.dto.StudentRequestDTO;
import com.student.student_management_system.dto.StudentResponseDTO;
import com.student.student_management_system.model.Course;
import com.student.student_management_system.model.Student;

import java.util.Set;
import java.util.stream.Collectors;

public class StudentMapper {

    public static Student toEntity(StudentRequestDTO dto){
        return Student.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .build();
    }

    public static StudentResponseDTO toDTO(Student student){
        Set<Long> courseIds = student.getCourses()
                .stream()
                .map(Course::getId)
                .collect(Collectors.toSet());

        Set<String> courseTitles = student.getCourses()
                .stream()
                .map(Course::getTitle)
                .collect(Collectors.toSet());

        return StudentResponseDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .age(student.getAge())
                .courseIds(courseIds)
                .courseTitles(courseTitles)
                .build();
    }
}
