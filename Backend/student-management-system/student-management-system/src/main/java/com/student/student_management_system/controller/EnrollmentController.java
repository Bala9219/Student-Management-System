package com.student.student_management_system.controller;

import com.student.student_management_system.dto.EnrollmentRequestDTO;
import com.student.student_management_system.dto.EnrollmentResponseDTO;
import com.student.student_management_system.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> enroll(@Valid @RequestBody EnrollmentRequestDTO request){
        return ResponseEntity.ok(enrollmentService.enroll(request));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getByStudent(@PathVariable Long studentId){
        return ResponseEntity.ok(enrollmentService.getByStudent(studentId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getByCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(enrollmentService.getByCourse(courseId));
    }

    @PutMapping("/{id}/progress")
    public ResponseEntity<EnrollmentResponseDTO> updateProgress(@PathVariable Long id,
                                                                @RequestParam Integer progress){
        return ResponseEntity.ok(enrollmentService.updateProgress(id, progress));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<EnrollmentResponseDTO> markCompleted(@PathVariable Long id){
        return ResponseEntity.ok(enrollmentService.markCompleted(id));
    }
}
