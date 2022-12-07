package com.tesis.ubb.tesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tesis.ubb.tesis.models.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

    public boolean existsById(Long id);
}
