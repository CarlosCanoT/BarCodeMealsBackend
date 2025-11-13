package com.tfg.barcodemeals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.barcodemeals.model.Precio;

public interface PrecioRepository extends JpaRepository<Precio, Long>{
}
