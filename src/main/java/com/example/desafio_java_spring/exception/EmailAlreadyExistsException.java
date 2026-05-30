package com.example.desafio_java_spring.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super ("Email já cadastrado: " + email);
    }
}
