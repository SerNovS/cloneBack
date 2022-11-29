package com.tesis.ubb.tesis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tesis.ubb.tesis.models.Cargo;
import com.tesis.ubb.tesis.models.Empleado;

@Service
public interface EmpleadoService {
    
	public List<Empleado> findAll();
	
	public Empleado save(Empleado cliente);
	
	public void delete(Long id);
	
	public Empleado findById(Long id);

	public List<Cargo> findAllCargos();
}
