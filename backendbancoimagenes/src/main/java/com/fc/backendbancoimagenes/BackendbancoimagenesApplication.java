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
	@Bean
	public CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
	    return args -> {
	        if (userRepository.findByUsername("admin").isEmpty()) {
	            Usuario user = new Usuario();
	            user.setUsername("admin");
	            user.setPassword(passwordEncoder.encode("123456"));
	            userRepository.save(user);
	            System.out.println("Usuario de prueba creado: admin / 123456");
	        }
	    };
	}
}
