package com.tesis.ubb.tesis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tesis.ubb.tesis.models.Producto;
import com.tesis.ubb.tesis.models.TipoProducto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("from TipoProducto")
    public List<TipoProducto> findAllTipos();
}
