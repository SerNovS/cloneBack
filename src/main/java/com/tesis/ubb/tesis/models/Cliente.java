package com.tesis.ubb.tesis.models;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Cliente implements Serializable {

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

    @Email
    @NotEmpty
    //@Column(nullable = false, unique = true)
    private String email;

    @NotEmpty
    //@Column(nullable = false, unique = true)
    private String direccion;
    
    private String nombreEmpresa;

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String apellido, String telefono, String email, String direccion,
            String nombreEmpresa) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.nombreEmpresa = nombreEmpresa;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreEmpresa() {
        return this.nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Cliente id(Long id) {
        setId(id);
        return this;
    }

    public Cliente nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Cliente apellido(String apellido) {
        setApellido(apellido);
        return this;
    }

    public Cliente telefono(String telefono) {
        setTelefono(telefono);
        return this;
    }

    public Cliente email(String email) {
        setEmail(email);
        return this;
    }

    public Cliente direccion(String direccion) {
        setDireccion(direccion);
        return this;
    }

    public Cliente nombreEmpresa(String nombreEmpresa) {
        setNombreEmpresa(nombreEmpresa);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cliente)) {
            return false;
        }
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(nombre, cliente.nombre)
                && Objects.equals(apellido, cliente.apellido) && Objects.equals(telefono, cliente.telefono)
                && Objects.equals(email, cliente.email) && Objects.equals(direccion, cliente.direccion)
                && Objects.equals(nombreEmpresa, cliente.nombreEmpresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, telefono, email, direccion, nombreEmpresa);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", nombre='" + getNombre() + "'" +
                ", apellido='" + getApellido() + "'" +
                ", telefono='" + getTelefono() + "'" +
                ", email='" + getEmail() + "'" +
                ", direccion='" + getDireccion() + "'" +
                ", nombreEmpresa='" + getNombreEmpresa() + "'" +
                "}";
    }

}
