package com.tesis.ubb.tesis.service;

import java.util.List;

import com.tesis.ubb.tesis.models.PrecioVenta;

public interface PrecioVentaService {

    public List<PrecioVenta> findAll();  
	
	public PrecioVenta save(PrecioVenta precioVenta);
	
	public void delete(Long id);
	
	public PrecioVenta findById(Long id);
    
}
