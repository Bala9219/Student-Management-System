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

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public StudentResponseDTO createStudent(@Valid @RequestBody StudentRequestDTO dto){
        return studentService.createStudent(dto);

    }

    @GetMapping
    public Page<StudentResponseDTO> getAllStudents(@PageableDefault(page = 0, size = 5, sort = "age")Pageable pageable){
        return studentService.getAllStudents(pageable);
    }

    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentResponseDTO updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequestDTO dto){
        return studentService.updateStudent(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public Page<StudentResponseDTO> searchStudents(@RequestParam String keyword,
                                                   @PageableDefault(size = 5, sort = "name")Pageable pageable){
        return studentService.searchStudents(keyword, pageable);
    }
}
