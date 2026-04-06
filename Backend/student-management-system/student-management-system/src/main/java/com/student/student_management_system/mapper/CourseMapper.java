package com.student.student_management_system.mapper;

import com.student.student_management_system.dto.CourseRequestDTO;
import com.student.student_management_system.dto.CourseResponseDTO;
import com.student.student_management_system.model.Course;

public class CourseMapper {

    public static Course toEntity(CourseRequestDTO dto){
        return Course.builder()
                .title(dto.getTitle())
                .duration(dto.getDuration())
                .fee(dto.getFee())
                .build();
    }

    public static CourseResponseDTO toDTO(Course course){
        return CourseResponseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .duration(course.getDuration())
                .fee(course.getFee())
                .build();
    }
}
