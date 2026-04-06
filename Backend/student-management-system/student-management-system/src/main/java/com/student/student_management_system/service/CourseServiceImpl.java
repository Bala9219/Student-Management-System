package com.student.student_management_system.service;

import com.student.student_management_system.dto.CourseRequestDTO;
import com.student.student_management_system.dto.CourseResponseDTO;
import com.student.student_management_system.exception.CourseNotFoundException;
import com.student.student_management_system.exception.DuplicateCourseException;
import com.student.student_management_system.exception.InsufficientResourcesException;
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
        return courseRepository.findAll(pageable)
                .map(CourseMapper::toDTO);
    }

    @Override
    public CourseResponseDTO findCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        return CourseMapper.toDTO(course);
    }

    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO request) {
        Course existing = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        if(!existing.getTitle().equals(request.getTitle()) &&
                courseRepository.existsByTitle(request.getTitle())){
            throw new DuplicateCourseException(request.getTitle());
        }

        existing.setTitle(request.getTitle());
        existing.setDuration(request.getDuration());
        existing.setFee(request.getFee());

        return CourseMapper.toDTO(courseRepository.save(existing));
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        courseRepository.delete(course);
    }

    @Override
    public Page<CourseResponseDTO> searchAllCourses(String keyword, Pageable pageable) {
        if(keyword==null || keyword.trim().length()<3){
            throw new InsufficientResourcesException("Search keyword must be at least 3 characters long.");
        }

        Page<Course> courses;
        if(keyword.matches(".*\\d.*")){
            courses = courseRepository.findByDurationContainingIgnoringCase(keyword, pageable);
        }else{
            courses = courseRepository.findByTitleContainingIgnoringCase(keyword, pageable);
        }

        return courses.map(CourseMapper::toDTO);
    }
}
