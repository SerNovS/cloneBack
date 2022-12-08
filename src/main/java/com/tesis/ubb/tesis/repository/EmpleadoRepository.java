package com.tesis.ubb.tesis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tesis.ubb.tesis.models.Cargo;
import com.tesis.ubb.tesis.models.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {

    @Query("from Cargo")
    List<Cargo> findAllCargos();
    
}
