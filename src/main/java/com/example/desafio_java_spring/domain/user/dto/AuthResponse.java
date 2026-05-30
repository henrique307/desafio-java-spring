package com.example.desafio_java_spring.domain.user.dto;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {}
