package com.student.student_management_system.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponseDTO {

    private Long id;
    private String title;
    private String duration;
    private Long fee;

}
