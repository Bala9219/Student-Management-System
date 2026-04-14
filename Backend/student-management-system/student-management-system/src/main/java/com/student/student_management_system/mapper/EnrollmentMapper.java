package com.student.student_management_system.mapper;

import com.student.student_management_system.dto.EnrollmentResponseDTO;
import com.student.student_management_system.model.Enrollment;

public class EnrollmentMapper {

    public static EnrollmentResponseDTO toDTO(Enrollment enrollment){
        return EnrollmentResponseDTO.builder()
                .id(enrollment.getId())
                .studentId(enrollment.getStudent().getId())
                .studentName(enrollment.getStudent().getName())
                .courseId(enrollment.getCourse().getId())
                .courseTitle(enrollment.getCourse().getTitle())
                .progress(enrollment.getProgress())
                .completed(enrollment.getCompleted())
                .enrolledAt(enrollment.getEnrolledAt())
                .build();
    }
}
