package com.tfg.barcodemeals.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.barcodemeals.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

	Optional<Producto> findByBarcode(String string);

}
