package com.tfg.barcodemeals.dto.response;

import java.util.List;

import com.tfg.barcodemeals.model.ReaccionAdversa;

public record ProductoResponse(
	    Long id,
	    String barcode,
	    String nombre,
	    String marca,
	    String categoria,
	    double pesoEmpaque,
	    double pesoConsumido,
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
	    List<Long> reaccionesAdversaIds,
	    List<Long> precioIds
) {}