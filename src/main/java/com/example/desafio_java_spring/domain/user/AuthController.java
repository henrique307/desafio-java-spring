package com.example.desafio_java_spring.domain.user;

import com.example.desafio_java_spring.domain.user.dto.AuthResponse;
import com.example.desafio_java_spring.domain.user.dto.LoginRequest;
import com.example.desafio_java_spring.domain.user.dto.RegisterRequest;
import com.example.desafio_java_spring.domain.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/resgister")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody @Valid RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }
}
