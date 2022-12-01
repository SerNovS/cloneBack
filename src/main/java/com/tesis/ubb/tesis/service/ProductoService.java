package com.tesis.ubb.tesis.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tesis.ubb.tesis.models.Producto;
import com.tesis.ubb.tesis.models.TipoProducto;

public interface ProductoService {

    public List<Producto> findAll();

    public Page<Producto>findAll(Pageable pageable);

    public Producto save(Producto producto);

    public void delete(Long id);

    public Producto findById(Long id);

    public List<TipoProducto> findAllTipos();

    public Producto actualizaStock(Long id,Integer stock);

    public Producto actualizaPrecioVenta(Long id ,Integer stock);
     
    
}
