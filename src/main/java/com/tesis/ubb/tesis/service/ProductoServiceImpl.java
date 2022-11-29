package com.tesis.ubb.tesis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tesis.ubb.tesis.models.Producto;
import com.tesis.ubb.tesis.models.TipoProducto;
import com.tesis.ubb.tesis.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productoRepository.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
	@Transactional
	public Page<Producto> findAll(Pageable pageable) {
		return productoRepository.findAll(pageable);
	}

    @Override
    public List<TipoProducto> findAllTipos() {
        return productoRepository.findAllTipos();
    }

    @Override
	@Transactional
    public Producto actualizaStock(Long id ,Integer stock){
        Producto p= productoRepository.findById(id).orElse(null);
        Integer valor=p.getStock() + stock;
        p.setStock(valor);
        return p;
    }
	

}
