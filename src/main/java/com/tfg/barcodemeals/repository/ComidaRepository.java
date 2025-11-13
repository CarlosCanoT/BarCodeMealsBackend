package com.tfg.barcodemeals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.barcodemeals.model.Comida;

public interface ComidaRepository extends JpaRepository<Comida, Long>{
}
