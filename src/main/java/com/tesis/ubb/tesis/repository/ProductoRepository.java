package com.tesis.ubb.tesis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tesis.ubb.tesis.models.Producto;
import com.tesis.ubb.tesis.models.TipoProducto;
import com.tesis.ubb.tesis.models.UnidadMedida;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("from TipoProducto")
    public List<TipoProducto> findAllTipos();

    @Query("from UnidadMedida")
    public List<UnidadMedida> findAllUnidades();

    @Query(value = "SELECT DISTINCT * from producto where tipo_id = ?1", nativeQuery = true)
    public List<Producto> findAllTipoById(Long id);

}
