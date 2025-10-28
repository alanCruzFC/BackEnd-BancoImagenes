package com.fc.backendbancoimagenes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fc.backendbancoimagenes.model.Usuario;
import com.fc.backendbancoimagenes.repository.UserRepository;

@SpringBootApplication
public class BackendbancoimagenesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendbancoimagenesApplication.class, args);
		
	}
	
}
