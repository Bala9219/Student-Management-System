package com.student.student_management_system.service;

import com.student.student_management_system.dto.CourseRequestDTO;
import com.student.student_management_system.dto.CourseResponseDTO;
import com.student.student_management_system.exception.DuplicateCourseException;
import com.student.student_management_system.mapper.CourseMapper;
import com.student.student_management_system.model.Course;
import com.student.student_management_system.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO request) {
        if(courseRepository.existsByTitle(request.getTitle())){
            throw new DuplicateCourseException(request.getTitle());
        }
        Course course = CourseMapper.toEntity(request);
        return CourseMapper.toDTO(courseRepository.save(course));
    }

    @Override
    public Page<CourseResponseDTO> getAllCourses(Pageable pageable) {
        return null;
    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {
        return null;
    }

    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO request) {
        return null;
    }

    @Override
    public void deleteStudent(Long id) {

    }

    @Override
    public Page<CourseResponseDTO> searchAllCourses(String keyword, Pageable pageable) {
        return null;
    }
}
