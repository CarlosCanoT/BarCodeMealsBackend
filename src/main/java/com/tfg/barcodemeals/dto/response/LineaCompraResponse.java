package com.tfg.barcodemeals.dto.response;

public record LineaCompraResponse (
		int cantidad,
		boolean comprado,
	    double precioLinea,
		Long listaCompraId,
		Long productoId
){}
