package com.student.student_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequestDTO {

    @NotBlank(message = "Title field is required")
    private String title;

    @NotBlank(message = "Duration of the course is required")
    private String duration;

    @NotNull(message = "fee value is required and cannot be null")
    private Long fee;
}
