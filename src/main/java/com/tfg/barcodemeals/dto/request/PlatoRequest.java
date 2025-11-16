package com.tfg.barcodemeals.dto.request;

import java.util.List;

public record PlatoRequest(
	    String nombre,
	    String descripcion,
	    List<Long> productosIds  
) {}
