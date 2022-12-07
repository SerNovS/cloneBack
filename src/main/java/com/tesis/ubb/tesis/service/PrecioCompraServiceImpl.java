package com.tesis.ubb.tesis.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tesis.ubb.tesis.models.PrecioCompra;
import com.tesis.ubb.tesis.repository.PrecioCompraRepository;


@Service
public class PrecioCompraServiceImpl implements PrecioCompraService{

    @Autowired
    private PrecioCompraRepository precioCompraRepository;

    @Override
	@Transactional
    public PrecioCompra save(PrecioCompra precioCompra) {
        return precioCompraRepository.save(precioCompra);
    }

	@Override
	@Transactional
	public void delete(Long id) {
		precioCompraRepository.deleteById(id);
	}

	@Override
    @Transactional(readOnly = true)
	public PrecioCompra findById(Long id) {
		return precioCompraRepository.findById(id).orElse(null);
	}


    @Override
    @Transactional(readOnly = true)
    public List<PrecioCompra> findAll() {
        return (List<PrecioCompra>)precioCompraRepository.findAll();
    }

    @Override
    @Transactional
    public Page<PrecioCompra> findAll(Pageable pageable) {
        return precioCompraRepository.findAll(pageable);
    } 
}
