package com.fc.backendbancoimagenes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fc.backendbancoimagenes.model.Usuario;
import com.fc.backendbancoimagenes.service.UsuarioService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioService usuarioService;

    UsuarioController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
	@GetMapping
	public List<Usuario> listar() {
		return usuarioService.listar();
	}
	
	public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuarioService.guardar(usuario);
		return ResponseEntity.ok("Usuario creado exitosamente");
	}
}
