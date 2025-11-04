package com.fc.backendbancoimagenes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fc.backendbancoimagenes.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}