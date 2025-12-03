package com.tfg.barcodemeals.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.barcodemeals.model.ReaccionAdversa;
import com.tfg.barcodemeals.model.TipoReaccion;

public interface ReaccionAdversaRepository extends JpaRepository<ReaccionAdversa, Long>{
	Optional<ReaccionAdversa> findByTipo(TipoReaccion tipo);
}
