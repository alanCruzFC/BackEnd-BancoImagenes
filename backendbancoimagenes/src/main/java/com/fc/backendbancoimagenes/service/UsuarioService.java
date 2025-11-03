package com.fc.backendbancoimagenes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fc.backendbancoimagenes.dto.RegisterRequest;
import com.fc.backendbancoimagenes.model.Equipo;
import com.fc.backendbancoimagenes.model.Usuario;
import com.fc.backendbancoimagenes.repository.EquipoRepository;
import com.fc.backendbancoimagenes.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	private EquipoRepository equipoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}

	public void guardar(RegisterRequest request) {
	    Usuario usuario = new Usuario();
	    usuario.setUsername(request.getUsername());
	    usuario.setPassword(passwordEncoder.encode(request.getPassword()));
	    usuario.setEmail(request.getEmail());
	    usuario.setRol(request.getRol());
	    usuario.setActivo(true);
	    
	    Equipo equipo = equipoRepository.findById(request.getEquipoId())
	        .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
	    usuario.setEquipo(equipo);
		usuarioRepository.save(usuario);
		
	}
	

}
