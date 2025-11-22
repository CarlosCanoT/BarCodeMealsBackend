package com.tfg.barcodemeals.dto.response;

import java.time.LocalDate;
import java.util.List;

public record ComidaResponse(
	    Long id,
	    String tipo,
	    LocalDate fecha,
	    List<Long> platoIds,
	    List<Long> productoIds,
	    double peso,
	    double kcal,
	    double grasa,
	    double saturada,
	    double noSaturada,
	    double proteina,
	    double hidratosCarbono,
	    double azucar,
	    double sal,
	    double fibra
) {}