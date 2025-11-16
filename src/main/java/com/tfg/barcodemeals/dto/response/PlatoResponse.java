package com.tfg.barcodemeals.dto.response;

import java.util.List;

public record PlatoResponse(
	    Long id,
	    String nombre,
	    String descripcion,
	    double kcal,
	    double grasa,
	    double saturada,
	    double noSaturada,
	    double proteina,
	    double hidratosCarbono,
	    double azucar,
	    double sal,
	    double fibra,
	    List<String> reaccionesAdversas, 
	    List<ProductoResponse> productos  
) {}