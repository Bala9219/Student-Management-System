package com.student.student_management_system.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;
}
