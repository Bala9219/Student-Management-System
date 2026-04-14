package com.student.student_management_system.service;

import com.student.student_management_system.dto.EnrollmentRequestDTO;
import com.student.student_management_system.dto.EnrollmentResponseDTO;
import com.student.student_management_system.exception.CourseNotFoundException;
import com.student.student_management_system.exception.StudentNotFoundException;
import com.student.student_management_system.mapper.EnrollmentMapper;
import com.student.student_management_system.model.Course;
import com.student.student_management_system.model.Enrollment;
import com.student.student_management_system.model.Student;
import com.student.student_management_system.repository.CourseRepository;
import com.student.student_management_system.repository.EnrollmentRepository;
import com.student.student_management_system.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public EnrollmentResponseDTO enroll(EnrollmentRequestDTO request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(request.getStudentId()));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(request.getCourseId()));

        enrollmentRepository.findByStudentIdAndCourseId(student.getId(), course.getId())
                .ifPresent(e -> {
                    throw new IllegalStateException("Student already enrolled in this course");
                });

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .progress(0)
                .completed(false)
                .enrolledAt(LocalDateTime.now())
                .build();

        return EnrollmentMapper.toDTO(enrollmentRepository.save(enrollment));
    }

    @Override
    public List<EnrollmentResponseDTO> getByStudent(Long studentId) {

        studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));

        return enrollmentRepository.findByStudentId(studentId)
                .stream()
                .map(EnrollmentMapper::toDTO)
                .toList();
    }

    @Override
    public List<EnrollmentResponseDTO> getByCourse(Long courseId) {

        courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        return enrollmentRepository.findByCourseId(courseId)
                .stream()
                .map(EnrollmentMapper::toDTO)
                .toList();
    }

    @Override
    public EnrollmentResponseDTO updateProgress(Long enrollmentId, Integer progress) {

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalStateException("Enrollment not found"));

        if (progress < 0 || progress > 100) {
            throw new RuntimeException("Progress must be between 0 and 100");
        }

        enrollment.setProgress(progress);

        if (progress == 100) {
            enrollment.setCompleted(true);
        }

        return EnrollmentMapper.toDTO(enrollmentRepository.save(enrollment));
    }

    @Override
    public EnrollmentResponseDTO markCompleted(Long enrollmentId) {

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalStateException("Enrollment not found"));

        enrollment.setCompleted(true);
        enrollment.setProgress(100);

        return EnrollmentMapper.toDTO(enrollmentRepository.save(enrollment));
    }
}
