package com.tfg.barcodemeals.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.barcodemeals.model.ListaCompra;

public interface ListaCompraRepository extends JpaRepository<ListaCompra, Long>{
	
	List<ListaCompra> findByUsuarioId(Long usuarioId);
	
}
