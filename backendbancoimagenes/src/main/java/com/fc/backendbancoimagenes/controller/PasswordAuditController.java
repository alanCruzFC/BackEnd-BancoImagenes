package com.fc.backendbancoimagenes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.fc.backendbancoimagenes.service.PasswordAuditService;

public class PasswordAuditController {
	
	private PasswordAuditService service;
	
	public ResponseEntity<String> verContra(@PathVariable Long id) throws Exception {
		return ResponseEntity.ok(service.ObtenerConDesencriptada(id));
	}
	

}
