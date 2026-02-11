package com.student.student_management_system.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponseDTO {

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String course;

}
