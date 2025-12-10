package com.tfg.barcodemeals.dto.response;

import java.util.List;

import com.tfg.barcodemeals.model.ReaccionAdversa;

public record PlatoResponse(
	    Long id,
	    String nombre,
	    String descripcion,
	    boolean esPublico,
	    double peso,
	    double kcal,
	    double grasa,
	    double saturada,
	    double noSaturada,
	    double hidratosCarbono,
	    double azucar,
	    double proteina,
	    double sal,
	    double fibra,
	    List<Long> productoIds,
	    Long usuarioId
) {}