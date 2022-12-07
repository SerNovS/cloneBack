package com.tesis.ubb.tesis.models;


import java.io.Serializable;
import java.util.Objects;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreProducto;
    private String imagen;
    private Integer stock;
    private String unidadMedida;
    private Integer ultimoPrecioCompra;
    private Integer ultimoPrecioVenta;
    private boolean visibilidad;


    @NotNull(message = "el tipo de producto no puede ser vacio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private TipoProducto tipoProducto;


    public Producto() {
    }
    
    public Producto(Long id, String nombreProducto, String imagen, Integer stock, String unidadMedida, Integer ultimoPrecioCompra, Integer ultimoPrecioVenta, boolean visibilidad) {

        this.id = id;
        this.nombreProducto = nombreProducto;
        this.imagen = imagen;
        this.stock = stock;
        this.unidadMedida = unidadMedida;
        this.ultimoPrecioCompra = ultimoPrecioCompra;
        this.ultimoPrecioVenta = ultimoPrecioVenta;
        this.visibilidad = visibilidad;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return this.nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getStock() {
        return this.stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getUnidadMedida() {
        return this.unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Integer getUltimoPrecioCompra() {
        return this.ultimoPrecioCompra;
    }

    public void setUltimoPrecioCompra(Integer ultimoPrecioCompra) {
        this.ultimoPrecioCompra = ultimoPrecioCompra;
    }

    public Integer getUltimoPrecioVenta() {
        return this.ultimoPrecioVenta;
    }

    public void setUltimoPrecioVenta(Integer ultimoPrecioVenta) {
        this.ultimoPrecioVenta = ultimoPrecioVenta;
    }

    public boolean isVisibilidad() {
        return this.visibilidad;
    }

    public boolean getVisibilidad() {
        return this.visibilidad;
    }

    public void setVisibilidad(boolean visibilidad) {
        this.visibilidad = visibilidad;
    }

    public Producto id(Long id) {
        setId(id);
        return this;
    }

    public Producto nombreProducto(String nombreProducto) {
        setNombreProducto(nombreProducto);
        return this;
    }

    public Producto imagen(String imagen) {
        setImagen(imagen);
        return this;
    }

    public Producto stock(Integer stock) {
        setStock(stock);
        return this;
    }

    public Producto unidadMedida(String unidadMedida) {
        setUnidadMedida(unidadMedida);
        return this;
    }

    public Producto ultimoPrecioCompra(Integer ultimoPrecioCompra) {
        setUltimoPrecioCompra(ultimoPrecioCompra);
        return this;
    }

    public Producto ultimoPrecioVenta(Integer ultimoPrecioVenta) {
        setUltimoPrecioVenta(ultimoPrecioVenta);
        return this;
    }

    public Producto visibilidad(boolean visibilidad) {
        setVisibilidad(visibilidad);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Producto)) {
            return false;
        }
        Producto producto = (Producto) o;
     

        return Objects.equals(id, producto.id) && Objects.equals(nombreProducto, producto.nombreProducto) && Objects.equals(imagen, producto.imagen) && Objects.equals(stock, producto.stock) && Objects.equals(unidadMedida, producto.unidadMedida) 
        && Objects.equals(ultimoPrecioCompra, producto.ultimoPrecioCompra) && Objects.equals(ultimoPrecioVenta, producto.ultimoPrecioVenta) && visibilidad == producto.visibilidad;

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nombreProducto, imagen, stock, unidadMedida, ultimoPrecioCompra, ultimoPrecioVenta,
                visibilidad);

    }

    @Override
    public String toString() {
        return "{" +

                " id='" + getId() + "'" +
                ", nombreProducto='" + getNombreProducto() + "'" +
                ", imagen='" + getImagen() + "'" +
                ", stock='" + getStock() + "'" +
                ", unidadMedida='" + getUnidadMedida() + "'" +
                ", ultimoPrecioCompra='" + getUltimoPrecioCompra() + "'" +
                ", ultimoPrecioVenta='" + getUltimoPrecioVenta() + "'" +
                ", visibilidad='" + isVisibilidad() + "'" +
                "}";
    }

    public TipoProducto getTipoProducto() {
        return this.tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }


}
