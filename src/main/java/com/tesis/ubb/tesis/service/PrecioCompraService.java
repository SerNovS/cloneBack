package com.tesis.ubb.tesis.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tesis.ubb.tesis.models.PrecioCompra;

public interface PrecioCompraService {

    public List<PrecioCompra> findAll();

    public Page<PrecioCompra> findAll(Pageable pageable);
	
	public PrecioCompra save(PrecioCompra precioCompra);
	
	public void delete(Long id);
	
	public PrecioCompra findById(Long id);
    
}
