package com.tesis.ubb.tesis.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cargos")
public class Cargo implements Serializable {
    
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	public Long getIdTipoProducto() {
		return this.id;
	}

	public void setIdTipoProducto(Long idTipoProducto) {
		this.id = idTipoProducto;
	}

	public String getNombreTipo() {
		return this.nombre;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombre = nombreTipo;
	}
}
