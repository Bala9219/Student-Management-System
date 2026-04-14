package com.student.student_management_system.dto;

import lombok.*;

import java.util.Set;

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

    private Set<Long> courseIds;
    private Set<String> courseTitles;
}
