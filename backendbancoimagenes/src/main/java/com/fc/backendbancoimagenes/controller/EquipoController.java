package com.fc.backendbancoimagenes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fc.backendbancoimagenes.dto.EquipoRequest;
import com.fc.backendbancoimagenes.model.Equipo;
import com.fc.backendbancoimagenes.repository.EquipoRepository;
import com.fc.backendbancoimagenes.service.EquipoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    private final EquipoRepository equipoRepository;
	
	@Autowired
	private EquipoService equipoService;

    EquipoController(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }
	
	@PostMapping("/nuevo")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> crearEquipo(@RequestBody EquipoRequest request) {
		equipoService.crearEquipo(request);
		return ResponseEntity.ok("Equipo creado de manera exitosa");
	}
	
	@GetMapping
	public List<Equipo> listarEquipos() {
	    return equipoRepository.findAll();
	}
	
	
	
}