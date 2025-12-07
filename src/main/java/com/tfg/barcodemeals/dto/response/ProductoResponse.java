package com.tfg.barcodemeals.dto.response;

import java.util.List;

public record ProductoResponse(
	    Long id,
	    String barcode,
	    String nombre,
	    String marca,
	    EnumResponse categoria,
	    double pesoEmpaque,
	    double pesoConsumido,
	    Double kcal,
	    double grasa,
	    Double saturada,
	    Double noSaturada,
	    double hidratosCarbono,
	    Double azucar,
	    double proteina,
	    Double sal,
	    Double fibra,
	    EnumResponse unidad,  
	    EnumResponse envase, 
	    List<Long> reaccionesAdversaIds,
	    List<Long> precioIds
) {}

	