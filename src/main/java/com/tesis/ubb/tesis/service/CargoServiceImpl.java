package com.tesis.ubb.tesis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesis.ubb.tesis.models.Cargo;
import com.tesis.ubb.tesis.repository.CargoRepository;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Override
    public List<Cargo> findAll() {
        return (List<Cargo>) cargoRepository.findAll();
    }

    @Override
    public Cargo findById(Long id) {
        return cargoRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return cargoRepository.existsById(id);
    }

}
