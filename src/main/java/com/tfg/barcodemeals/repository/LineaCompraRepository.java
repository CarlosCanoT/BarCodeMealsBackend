package com.tfg.barcodemeals.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.barcodemeals.model.LineaCompra;

public interface LineaCompraRepository extends JpaRepository<LineaCompra, Long>{
	
	List<LineaCompra> findByListaCompraId(Long listaCompraId);
	
}
