package com.student.student_management_system.controller;

import com.student.student_management_system.dto.StudentRequestDTO;
import com.student.student_management_system.dto.StudentResponseDTO;
import com.student.student_management_system.model.Student;
import com.student.student_management_system.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO dto){
        StudentResponseDTO created = studentService.createStudent(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStudents(@PageableDefault(page = 0, size = 10, sort = "age")Pageable pageable){
        Page<StudentResponseDTO> page = studentService.getAllStudents(pageable);

        Map<String, Object> response = Map.of(
                "students", page.getContent(),
                "currentPage", page.getNumber(),
                "totalItems", page.getTotalElements(),
                "totalPages", page.getTotalPages(),
                "pageSize", page.getSize()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id){
        StudentResponseDTO getStudent =  studentService.getStudentById(id);
        return ResponseEntity.ok(getStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequestDTO dto){
        StudentResponseDTO updated = studentService.updateStudent(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchStudents(@RequestParam String keyword,
                                                   @PageableDefault(size = 10, sort = "name")Pageable pageable){
        Page<StudentResponseDTO> page = studentService.searchStudents(keyword, pageable);

        Map<String, Object> response = Map.of(
                "students", page.getContent(),
                "currentPage", page.getNumber(),
                "totalItems", page.getTotalElements(),
                "totalPages", page.getTotalPages(),
                "pageSize", page.getSize()
        );
        return ResponseEntity.ok(response);
    }
}
