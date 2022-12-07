package com.tesis.ubb.tesis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesis.ubb.tesis.models.PrecioVenta;
import com.tesis.ubb.tesis.repository.PrecioVentaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrecioVentaServiceImpl implements PrecioVentaService{

    @Autowired
    private PrecioVentaRepository precioVentaRepository;

    @Override
    @Transactional
    public PrecioVenta save(PrecioVenta precioVenta) {
        return precioVentaRepository.save(precioVenta);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        precioVentaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrecioVenta> findAll() {
        return (List<PrecioVenta>)precioVentaRepository.findAll();
    }   

    @Override
    @Transactional(readOnly = true)
    public PrecioVenta findById(Long id) {
        return precioVentaRepository.findById(id).orElse(null);
    }
    
}
