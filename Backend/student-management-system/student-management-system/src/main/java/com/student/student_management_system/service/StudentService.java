package com.student.student_management_system.service;


import com.student.student_management_system.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    Student createStudent(Student student);

    Page<Student> getAllStudents(Pageable pageable);

    Student getStudentById(Long id);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);
}
