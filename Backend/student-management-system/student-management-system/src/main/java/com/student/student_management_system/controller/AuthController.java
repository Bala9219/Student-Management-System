package com.student.student_management_system.controller;

import com.student.student_management_system.dto.LoginRequestDTO;
import com.student.student_management_system.dto.LoginResponseDTO;
import com.student.student_management_system.security.JwtUtil;
import com.student.student_management_system.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getUsername(),
                loginRequestDTO.getPassword()
        ));
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.getUsername());

        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
