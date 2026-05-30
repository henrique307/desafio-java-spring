package com.example.desafio_java_spring;

import org.springframework.boot.SpringApplication;

public class TestDesafioJavaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.from(DesafioJavaSpringApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
