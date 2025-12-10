package com.tfg.barcodemeals.dto.request;

import java.time.LocalDate;
import java.util.List;

public record ComidaRequest(
		Long id,
		String nombre,
	    String tipo,
	    LocalDate fecha,
	    List<Long> platoIds,
	    List<Long> productoIds
) { }

