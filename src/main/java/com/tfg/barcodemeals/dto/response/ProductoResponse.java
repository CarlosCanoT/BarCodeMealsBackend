package com.tfg.barcodemeals.dto.response;

import java.util.List;

public record ProductoResponse(
	    Long id,
	    String barcode,
	    String nombre,
	    String marca,
	    String categoria,
	    double peso,
	    Double kcal,
	    double grasa,
	    Double saturada,
	    Double noSaturada,
	    double proteina,
	    double hidratosCarbono,
	    Double azucar,
	    Double sal,
	    Double fibra,
	    String unidad,  
	    String envase, 
	    List<String> reaccionesAdversas,
	    List<Double> precios
) {}