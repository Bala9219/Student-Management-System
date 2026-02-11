package com.student.student_management_system.service;

import com.student.student_management_system.exception.DuplicateMailException;
import com.student.student_management_system.exception.StudentNotFoundException;
import com.student.student_management_system.model.Student;
import com.student.student_management_system.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        if(studentRepository.existsByEmail(student.getEmail())){
            throw new DuplicateMailException(student.getEmail());
        }
        return studentRepository.save(student);
    }

    @Override
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existing = getStudentById(id);

        existing.setName(student.getName());
        //existing.setEmail(student.getEmail());
        if(!existing.getEmail().equals(student.getEmail()) && studentRepository.existsByEmail(student.getEmail())){
            throw new DuplicateMailException(student.getEmail());
        }
        existing.setEmail(student.getEmail());
        existing.setAge(student.getAge());
        existing.setCourse(student.getCourse());

        return studentRepository.save(existing);
    }

    @Override
    public void deleteStudent(Long id) {
        Student existing = getStudentById(id);
        studentRepository.delete(existing);
    }
}
