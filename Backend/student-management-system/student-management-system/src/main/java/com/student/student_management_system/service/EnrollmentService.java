package com.student.student_management_system.service;

import com.student.student_management_system.dto.EnrollmentRequestDTO;
import com.student.student_management_system.dto.EnrollmentResponseDTO;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponseDTO enroll(EnrollmentRequestDTO request);

    List<EnrollmentResponseDTO> getByStudent(Long studentId);

    List<EnrollmentResponseDTO> getByCourse(Long courseId);

    EnrollmentResponseDTO updateProgress(Long enrollmentId, Integer progress);

    EnrollmentResponseDTO markCompleted(Long enrollmentId);
}
