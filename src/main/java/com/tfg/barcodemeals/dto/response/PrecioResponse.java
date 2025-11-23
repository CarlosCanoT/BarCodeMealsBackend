package com.tfg.barcodemeals.dto.response;

import java.time.LocalDate;

public record PrecioResponse(
	    Long id,
	    double valor,
	    LocalDate fecha,
	    boolean oferta,
	    double descuento,
	    Long productoId,
	    Long supermercadoId
) {}