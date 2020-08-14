package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class WebSocketApp2Application {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketApp2Application.class, args);
		
		
	}

}
