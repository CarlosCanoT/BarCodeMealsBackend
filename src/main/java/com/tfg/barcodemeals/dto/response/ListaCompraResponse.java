package com.tfg.barcodemeals.dto.response;

import java.time.LocalDate;
import java.util.List;

public record ListaCompraResponse(
	    Long id,
	    String nombre,
	    LocalDate fechaCreacion,
	    double precioTotal,
	    List<LineaCompraResponse> lineas
) {}