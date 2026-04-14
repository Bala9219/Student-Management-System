package com.student.student_management_system.controller;

import com.student.student_management_system.dto.LoginRequestDTO;
import com.student.student_management_system.dto.LoginResponseDTO;
import com.student.student_management_system.dto.UserRequestDTO;
import com.student.student_management_system.model.User;
import com.student.student_management_system.security.JwtUtil;
import com.student.student_management_system.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequestDTO.getUsername(),
                    loginRequestDTO.getPassword()
            ));

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.getUsername());

            String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDTO request){
        User user = userDetailsService.registerUser(
                request.getUsername(),
                request.getPassword(),
                request.getRole()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
