package com.tfg.barcodemeals.dto.request;

import java.util.List;

public record PlatoRequest(
		Long id,
	    String nombre,
	    String descripcion,
	    List<Long> productosIds  
) {}
