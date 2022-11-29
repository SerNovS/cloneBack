package com.tesis.ubb.tesis.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Empleado implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 3, max = 12)
    @Column(nullable = false)
    private String nombre;

    @NotEmpty
    @Column(nullable = false)
    private String apellido;
    
    @NotEmpty
    @Column(nullable = false)
    private String telefono;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cargo cargo;


    public Empleado() {
    }

    public Empleado(Long id, String nombre, String apellido, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Empleado id(Long id) {
        setId(id);
        return this;
    }

    public Empleado nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Empleado apellido(String apellido) {
        setApellido(apellido);
        return this;
    }

    public Empleado telefono(String telefono) {
        setTelefono(telefono);
        return this;
    }


    public Cargo getCargo() {
        return this.cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    
}
