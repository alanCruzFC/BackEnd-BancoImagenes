package com.fc.backendbancoimagenes.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import com.fc.backendbancoimagenes.model.Usuario;
import com.fc.backendbancoimagenes.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}

}
