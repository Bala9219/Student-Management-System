package com.student.student_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "courses")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title field is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Duration of the course is required")
    @Column(nullable = false)
    private String duration;

    @NotNull(message = "fee value is required and cannot be null")
    @Column(nullable = false)
    private Long fee;

}
