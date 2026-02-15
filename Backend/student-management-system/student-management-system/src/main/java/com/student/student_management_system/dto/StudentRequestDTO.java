package com.student.student_management_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequestDTO {

    @NotBlank(message = "Name field is required")
    private String name;

    @Email(message = "E-Mail should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Min(value = 1, message = "Age cannot be less than 1 or negative")
    @NotNull(message = "Age is required")
    private Integer age;

    @NotNull(message = "Course Id cannot be null")
    private Long courseId;
}
