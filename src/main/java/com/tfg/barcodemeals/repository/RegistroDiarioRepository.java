package com.tfg.barcodemeals.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.barcodemeals.model.RegistroDiario;
import com.tfg.barcodemeals.model.Usuario;

public interface RegistroDiarioRepository extends JpaRepository<RegistroDiario, Long>{
	
	Optional<RegistroDiario> findByUsuarioAndFecha(Usuario usuario, LocalDate fecha);
	
}
