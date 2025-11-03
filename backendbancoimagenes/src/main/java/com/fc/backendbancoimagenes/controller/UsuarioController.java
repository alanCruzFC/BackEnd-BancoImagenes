package com.fc.backendbancoimagenes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fc.backendbancoimagenes.dto.RegisterRequest;
import com.fc.backendbancoimagenes.model.PasswordAudit;
import com.fc.backendbancoimagenes.model.Usuario;
import com.fc.backendbancoimagenes.repository.PasswordAuditRepository;
import com.fc.backendbancoimagenes.service.UsuarioService;
import com.fc.backendbancoimagenes.util.EncriptacionUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PasswordAuditRepository passwordAuditRepository;

    UsuarioController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> crear(@RequestBody RegisterRequest request) throws Exception {
        usuarioService.guardar(request);
        return ResponseEntity.ok("Usuario creado exitosamente");
    }
	
	@GetMapping
	public List<Usuario> listar() {
		return usuarioService.listar();
	}
	
	@GetMapping("/{id}/contrasena")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> obtenerContrasena(@PathVariable Long id) {
		PasswordAudit passwordAudit = passwordAuditRepository.findByUserId(id)
            .orElseThrow(() -> new RuntimeException("No se encontró la contraseña"));
        
        try {
        	String desencriptada = EncriptacionUtil.Desencriptacion(passwordAudit.getEncrypPassword());
        	return ResponseEntity.ok(desencriptada);
        }catch (Exception e){
        	throw new RuntimeException("Error al desencriptar la contraseña", e);
        }
        
        
    }

}
