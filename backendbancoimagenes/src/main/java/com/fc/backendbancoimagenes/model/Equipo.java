package com.fc.backendbancoimagenes.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Equipo {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  private String nombre;
	  
	  @ManyToOne
	  @JoinColumn(name = "jefe_id")
	  private Usuario jefe;

	  @OneToMany(mappedBy = "equipo")
	  @JsonIgnore
	  private List<Usuario> usuarios;

	  public Long getId() {
		  return id;
	  }

	  public void setId(Long id) {
		  this.id = id;
	  }

	  public String getNombre() {
		  return nombre;
	  }

	  public void setNombre(String nombre) {
		  this.nombre = nombre;
	  }

	  public List<Usuario> getUsuarios() {
		  return usuarios;
	  }

	  public void setUsuarios(List<Usuario> usuarios) {
		  this.usuarios = usuarios;
	  }

	  public Usuario getJefe() {
		  return jefe;
	  }

	  public void setJefe(Usuario jefe) {
		  this.jefe = jefe;
	  }
	  
	  
	  

}
