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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


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

    @Basic
    @NotNull(message = "Debe ingresar la fecha.")
    @Column(nullable = false)
    private java.sql.Date FechaCompra;


    // @NotEmpty
    // @Column(nullable = false)
    // private java.sql.Date comienzoValidesFechaCompra;

    
    // @Column(nullable = false)
    // private java.sql.Date terminoValidesFechaCompra;

    @NotNull(message = "Indique que tipo de producto ha comprado.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public PrecioCompra() {
    }

   



    public PrecioCompra(Long id, @NotNull(message = "El precio no puede ser vacia.") @Min(1) Integer precio,
            @NotNull(message = "La cantidad no puede ser vacia.") @Min(1) Integer cantidad, Date fechaCompra,
            @NotNull(message = "Indique que tipo de producto ha comprado.") Producto producto) {
        this.id = id;
        this.precio = precio;
        this.cantidad = cantidad;
        FechaCompra = fechaCompra;
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

   

    public java.sql.Date getFechaCompra() {
        return FechaCompra;
    }

    public void setFechaCompra(java.sql.Date fechaCompra) {
        FechaCompra = fechaCompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }



    public Integer getCantidad() {
        return cantidad;
    }



    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }





    public Integer getPrecio() {
        return precio;
    }





    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    


}
