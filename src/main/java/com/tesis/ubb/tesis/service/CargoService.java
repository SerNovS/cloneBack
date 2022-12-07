package com.tesis.ubb.tesis.service;

import java.util.List;

import com.tesis.ubb.tesis.models.Cargo;

public interface CargoService {

    public List<Cargo> findAll();

    public Cargo findById(Long id);

    public boolean existsById(Long id);

}
