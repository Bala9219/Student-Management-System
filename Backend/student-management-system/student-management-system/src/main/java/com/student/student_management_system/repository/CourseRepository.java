package com.student.student_management_system.repository;

import com.student.student_management_system.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByTitle(String title);

    Page<Course> findByTitleContainingIgnoringCase(String title, Pageable pageable);

    Page<Course> findByDurationContainingIgnoringCase(String duration, Pageable pageable);

}
