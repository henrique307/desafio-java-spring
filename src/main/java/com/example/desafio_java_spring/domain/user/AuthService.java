package com.example.desafio_java_spring.domain.user;

import com.example.desafio_java_spring.domain.user.dto.AuthResponse;
import com.example.desafio_java_spring.domain.user.dto.LoginRequest;
import com.example.desafio_java_spring.exception.InvalidCredentialsException;
import com.example.desafio_java_spring.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(InvalidCredentialsException::new);

        boolean passWordDontMatch = !passwordEncoder
                .matches(request.password(), user.getPassword());

        if (passWordDontMatch) {
            throw new InvalidCredentialsException();
        }

        return new AuthResponse(
                jwtService.generateAccessToken(user),
                jwtService.generateRefreshToken(user)
        );
    }
}
