package com.tfg.barcodemeals.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfg.barcodemeals.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

	Optional<Producto> findByBarcode(String string);
	
	@Query("SELECT p FROM Producto p WHERE (p.esPublico = true AND p.esFraccion = false) OR p.usuario.id = :usuarioId")
	List<Producto> findVisibleForUser(@Param("usuarioId") Long usuarioId);

	List<Producto> findByUsuarioIdAndEsFraccionTrue(Long usuarioId);

	List<Producto> findByUsuarioIdAndEsFraccionFalse(Long usuarioId);

}
