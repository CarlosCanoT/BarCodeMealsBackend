package com.tfg.barcodemeals.dto.response;

import java.time.LocalDate;
import java.util.List;

public record ComidaResponse(
	    Long id,
	    String nombre,
	    EnumResponse tipo,
	    LocalDate fecha,
	    Long registroDiarioId,
	    List<Long> platoIds,
	    List<Long> productoIds,
	    double peso,
	    double kcal,
	    double grasa,
	    double saturada,
	    double noSaturada,
	    double hidratosCarbono,
	    double azucar,
	    double proteina,
	    double sal,
	    double fibra
) {}