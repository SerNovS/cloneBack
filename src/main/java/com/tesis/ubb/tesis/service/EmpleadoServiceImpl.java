package com.tesis.ubb.tesis.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesis.ubb.tesis.models.Cargo;
import com.tesis.ubb.tesis.models.Empleado;
import com.tesis.ubb.tesis.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll() {
		return (List<Empleado>) empleadoRepository.findAll();
	}

	@Override
	@Transactional
	public Empleado save(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		empleadoRepository.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Empleado findById(Long id) {
		return empleadoRepository.findById(id).orElse(null);
	}

	@Override
	public List<Cargo> findAllCargos() {
		return empleadoRepository.findAllCargos();
	}


}
