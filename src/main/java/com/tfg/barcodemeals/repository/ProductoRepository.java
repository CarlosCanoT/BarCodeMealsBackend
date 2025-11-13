package com.tfg.barcodemeals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.barcodemeals.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
