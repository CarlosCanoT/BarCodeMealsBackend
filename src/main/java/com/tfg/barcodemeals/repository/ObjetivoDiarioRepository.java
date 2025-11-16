package com.tfg.barcodemeals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.barcodemeals.model.Ciudad;

public interface ObjetivoDiarioRepository extends JpaRepository<Ciudad, Long>{
}
