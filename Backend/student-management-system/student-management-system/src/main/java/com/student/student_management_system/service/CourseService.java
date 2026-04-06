package com.student.student_management_system.service;

import com.student.student_management_system.dto.CourseRequestDTO;
import com.student.student_management_system.dto.CourseResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    CourseResponseDTO createCourse(CourseRequestDTO request);

    Page<CourseResponseDTO> getAllCourses(Pageable pageable);

    CourseResponseDTO getCourseById(Long id);

    CourseResponseDTO updateCourse(Long id, CourseRequestDTO request);

    void deleteStudent(Long id);

    Page<CourseResponseDTO> searchAllCourses(String keyword, Pageable pageable);
}
