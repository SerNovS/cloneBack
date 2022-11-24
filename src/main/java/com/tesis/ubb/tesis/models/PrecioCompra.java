package com.tesis.ubb.tesis.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


@Entity
@Table(name = "precio_compra")
public class PrecioCompra implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "El precio no puede ser vacia.")
    @Min(1)
    @Column(nullable = false)
    private Integer precio;

    @NotNull(message = "La cantidad no puede ser vacia.")
    @Min(1)
    @Column(nullable = false)
    private Integer cantidad;

    //@Basic
    @NotNull(message = "Debe ingresar la fecha.")
    @Column(name="fecha",nullable = false)
    //@Temporal(TemporalType.TIMESTAMP)
    //java.util.Calendar
    //java.util.Date
   // private java.sql.Date FechaCompra;
   @JsonFormat(pattern = "YYYY-MM-DD", shape=Shape.STRING)
   private java.sql.Date fechaCompra;
   

    // @NotNull(message = "Indique que tipo de producto ha comprado.")
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "producto_id")
    @NotNull(message = "Ingrese el producto")
    @Min(0)
    @Column(name = "producto", insertable = true, updatable = false)
    private Long producto;

    public PrecioCompra() {
    }

    public PrecioCompra(Long id, @NotNull(message = "El precio no puede ser vacia.") @Min(1) Integer precio,
            @NotNull(message = "La cantidad no puede ser vacia.") @Min(1) Integer cantidad, Date fechaCompra,
            @NotNull(message = "Ingrese el producto") @Min(1) Long producto) {
        this.id = id;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
        this.producto = producto;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public java.sql.Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(java.sql.Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Long getProducto() {
        return producto;
    }

    public void setProducto(Long producto) {
        this.producto = producto;
    }    

    
}
