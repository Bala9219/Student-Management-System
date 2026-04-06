package com.student.student_management_system.controller;

import com.student.student_management_system.dto.CourseRequestDTO;
import com.student.student_management_system.dto.CourseResponseDTO;
import com.student.student_management_system.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseRequestDTO request){
        CourseResponseDTO saved = courseService.createCourse(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCourses(@PageableDefault(page = 0, size = 10, sort = "duration")Pageable pageable){
        Page<CourseResponseDTO> page = courseService.getAllCourses(pageable);

        Map<String, Object> response = Map.of(
                "courses", page.getContent(),
                "currentPage", page.getNumber(),
                "totalItems", page.getTotalElements(),
                "totalPages", page.getTotalPages(),
                "pageSize", page.getSize()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> findCourseById(@PathVariable Long id){
        CourseResponseDTO getCourse = courseService.findCourseById(id);
        return ResponseEntity.ok(getCourse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequestDTO request){
        CourseResponseDTO updatedCourse = courseService.updateCourse(id, request);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchCourses(@RequestParam String keyword,
                                                             @PageableDefault(size = 10, sort = "title")Pageable pageable){
        Page<CourseResponseDTO> page = courseService.searchAllCourses(keyword, pageable);

        Map<String, Object> response = Map.of(
                "courses", page.getContent(),
                "currentPage", page.getNumber(),
                "totalItems", page.getTotalElements(),
                "totalPages", page.getTotalPages(),
                "pageSize", page.getSize()
        );
        return ResponseEntity.ok(response);
    }
}
