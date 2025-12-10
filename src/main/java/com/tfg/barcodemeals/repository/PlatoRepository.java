package com.tfg.barcodemeals.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfg.barcodemeals.model.Plato;

public interface PlatoRepository extends JpaRepository<Plato, Long>{

	Optional<Plato> findByNombre(String nombre);
	
	@Query("SELECT p FROM Plato p WHERE p.esPublico = true OR p.usuario.id = :usuarioId")
	List<Plato> findVisibleForUser(@Param("usuarioId") Long usuarioId);

}
