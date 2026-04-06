package com.student.student_management_system.controller;

import com.student.student_management_system.dto.CourseRequestDTO;
import com.student.student_management_system.dto.CourseResponseDTO;
import com.student.student_management_system.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CourseResponseDTO createCourse(@Valid @RequestBody CourseRequestDTO request){
        return courseService.createCourse(request);
    }
}
