package com.fc.backendbancoimagenes.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.fc.backendbancoimagenes.dto.EquipoRequest;
import com.fc.backendbancoimagenes.service.EquipoService;

public class EquipoController {
	
	private EquipoService equipoService;
	
	public ResponseEntity<String> crearEquipo(@RequestBody EquipoRequest request) {
		equipoService.crearEquipo(request);
		return ResponseEntity.ok("Equipo creado de manera exitosa");
	}
	
}
