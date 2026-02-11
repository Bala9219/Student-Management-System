package com.student.student_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "students")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name field is required")
    @Column(nullable = false)
    private String name;

    @Email(message = "E-Mail should be valid")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @Min(value = 1, message = "Age cannot be less than 1 or negative")
    @NotNull(message = "Age is required")
    @Column(nullable = false)
    private Integer age;

    @NotBlank(message = "Course is required")
    @Column(nullable = false)
    private String course;
}
