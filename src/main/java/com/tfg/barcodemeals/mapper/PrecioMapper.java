package com.tfg.barcodemeals.mapper;

import com.tfg.barcodemeals.dto.response.PrecioResponse;
import com.tfg.barcodemeals.model.Precio;

public class PrecioMapper {
public PrecioResponse toResponse(Precio precio) {
	if(precio == null) return null;
	
	return new PrecioResponse(
			precio.getId(),
			precio.getValor(),
			precio.getFecha(),
			precio.isOferta(),
			precio.getDescuento(),
			precio.getProducto().getId(),
			precio.getSupermercado().getId()
	);
}

}
