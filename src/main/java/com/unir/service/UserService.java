package com.unir.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unir.model.User;
import com.unir.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(String name, String email, String password, String type) {
        if(userRepository.findByEmail(email).isPresent()){
            throw new RuntimeException("Email ya registrado");
        }
        User user = User.builder()
                .name(name)
                .email(email)
                .passwordHash(passwordEncoder.encode(password))
                .type(type != null ? type : "user")
                .createdAt(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email no registrado"));

        if(!passwordEncoder.matches(password, user.getPasswordHash())){
            throw new RuntimeException("Contraseña incorrecta");
        }
        return user;
    }

    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}