package com.student.student_management_system.service;

import com.student.student_management_system.dto.StudentRequestDTO;
import com.student.student_management_system.dto.StudentResponseDTO;
import com.student.student_management_system.exception.CourseNotFoundException;
import com.student.student_management_system.exception.DuplicateMailException;
import com.student.student_management_system.exception.InsufficientResourcesException;
import com.student.student_management_system.exception.StudentNotFoundException;
import com.student.student_management_system.mapper.StudentMapper;
import com.student.student_management_system.model.Course;
import com.student.student_management_system.model.Student;
import com.student.student_management_system.repository.CourseRepository;
import com.student.student_management_system.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
        if(studentRepository.existsByEmail(dto.getEmail())){
            throw new DuplicateMailException(dto.getEmail());
        }

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(dto.getCourseId()));

        Student student = StudentMapper.toEntity(dto);
        student.setCourse(course);
        return StudentMapper.toDTO(studentRepository.save(student));
    }

    @Override
    public Page<StudentResponseDTO> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable)
                .map(StudentMapper::toDTO);
    }

    @Override
    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        return StudentMapper.toDTO(student);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        if(!existing.getEmail().equals(dto.getEmail()) && studentRepository.existsByEmail(dto.getEmail())){
            throw new DuplicateMailException(dto.getEmail());
        }

        Course course = courseRepository.findById(dto.getCourseId())
                        .orElseThrow(() -> new CourseNotFoundException(dto.getCourseId()));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setAge(dto.getAge());
        existing.setCourse(course);

        return StudentMapper.toDTO(studentRepository.save(existing));
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        studentRepository.delete(student);
    }

    @Override
    public Page<StudentResponseDTO> searchStudents(String keyword, Pageable pageable) {
        if(keyword==null || keyword.trim().length()<3){
            throw new InsufficientResourcesException("Search keyword must be at least 3 characters long.");
        }
        Page<Student> students;
        if(keyword.contains("@")){
            students = studentRepository.findByEmailContainingIgnoreCase(keyword, pageable);
        } else {
          students = studentRepository.findByNameContainingIgnoreCase(keyword, pageable);
        }

        return students.map(StudentMapper::toDTO);
    }
}
