package com.fc.backendbancoimagenes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fc.backendbancoimagenes.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
