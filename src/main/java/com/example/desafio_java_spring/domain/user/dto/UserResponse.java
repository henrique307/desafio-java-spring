package com.example.desafio_java_spring.domain.user.dto;

import com.example.desafio_java_spring.domain.user.Role;
import com.example.desafio_java_spring.domain.user.User;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        Role role
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
