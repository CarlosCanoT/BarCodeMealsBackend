package com.tfg.barcodemeals.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<Rs, Rq> {
	
	Optional<Rs> obtenerPorId(Long id);
	List<Rs> obtenerTodos();
	Rs crear(Rq request);
	Optional<Rs> actualizar(Long id, Rq request);
	boolean eliminar(Long id);
}
