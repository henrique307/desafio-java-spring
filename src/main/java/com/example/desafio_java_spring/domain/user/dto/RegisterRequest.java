package com.example.desafio_java_spring.domain.user.dto;

import com.example.desafio_java_spring.domain.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank
    String name,

    @NotBlank @Email
    String email,

    @NotBlank @Size(min = 6)
    String password,

    @NotNull
    Role role
) {}
