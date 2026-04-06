package com.student.student_management_system.service;

import com.student.student_management_system.model.User;
import com.student.student_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
        return new CustomUserDetails(user);
    }

    public User registerUser(String username, String password, String role){
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(role.toUpperCase())
                .build();

        return userRepository.save(user);
    }
}
