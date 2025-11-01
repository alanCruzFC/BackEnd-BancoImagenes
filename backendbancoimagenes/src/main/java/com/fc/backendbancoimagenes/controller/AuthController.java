package com.fc.backendbancoimagenes.controller;

import com.fc.backendbancoimagenes.dto.AuthRequest;
import com.fc.backendbancoimagenes.dto.RegisterRequest;
import com.fc.backendbancoimagenes.model.Equipo;
import com.fc.backendbancoimagenes.model.LoginResponse;
import com.fc.backendbancoimagenes.model.Usuario;
import com.fc.backendbancoimagenes.repository.EquipoRepository;
import com.fc.backendbancoimagenes.repository.UserRepository;
import com.fc.backendbancoimagenes.security.JwtService;
import com.fc.backendbancoimagenes.service.PasswordAuditService;
import com.fc.backendbancoimagenes.util.EncriptacionUtil;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/**")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final PasswordAuditService passwordAuditService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    AuthController(PasswordAuditService passwordAuditService) {
        this.passwordAuditService = passwordAuditService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthRequest request) {
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        
        Map<String , Object> claims = new HashMap<>();
        claims.put("role", role);
        
        String token = jwtService.generateToken(claims, userDetails);
        return ResponseEntity.ok(new LoginResponse(token));
    }
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EquipoRepository equipoRepository;

    @PostMapping("/nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) throws Exception {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        }

        Usuario user = new Usuario();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRol(request.getRol());
        user.setActivo(true);
        
        Equipo equipo = equipoRepository.findById(request.getEquipoId())
        		.orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        user.setEquipo(equipo);
        
        userRepository.save(user);
        
        String encrypted = EncriptacionUtil.Encriptacion(request.getPassword());
        passwordAuditService.guardar(user.getId(), encrypted);

        return ResponseEntity.ok("Usuario creado exitosamente");
    }

}

