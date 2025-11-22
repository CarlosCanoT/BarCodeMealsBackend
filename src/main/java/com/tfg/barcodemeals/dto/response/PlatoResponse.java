package com.tfg.barcodemeals.dto.response;

import java.util.List;

import com.tfg.barcodemeals.model.ReaccionAdversa;

public record PlatoResponse(
	    Long id,
	    String nombre,
	    String descripcion,
	    double peso,
	    double kcal,
	    double grasa,
	    double saturada,
	    double noSaturada,
	    double proteina,
	    double hidratosCarbono,
	    double azucar,
	    double sal,
	    double fibra,
	    List<Long> reaccionesAdversaIds, 
	    List<Long> productoIds  
) {}