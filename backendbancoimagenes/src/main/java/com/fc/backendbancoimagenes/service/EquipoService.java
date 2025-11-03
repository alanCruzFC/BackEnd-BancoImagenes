package com.fc.backendbancoimagenes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fc.backendbancoimagenes.dto.EquipoRequest;
import com.fc.backendbancoimagenes.model.Equipo;
import com.fc.backendbancoimagenes.model.Usuario;
import com.fc.backendbancoimagenes.repository.EquipoRepository;
import com.fc.backendbancoimagenes.repository.UsuarioRepository;

@Service
public class EquipoService {
	
	@Autowired
	private EquipoRepository equipoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void crearEquipo(EquipoRequest request) {
		Usuario jefe = usuarioRepository.findById(request.getJefeId())
				.orElseThrow(() -> new RuntimeException("Usuario jefe no encontrado"));
		
		Equipo equipo = new Equipo();
		equipo.setNombre(request.getNombre());
		equipo.setJefe(jefe);
		
		equipoRepository.save(equipo);
	}

}
