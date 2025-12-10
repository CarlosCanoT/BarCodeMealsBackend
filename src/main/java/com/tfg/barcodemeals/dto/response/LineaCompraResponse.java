package com.tfg.barcodemeals.dto.response;

public record LineaCompraResponse (
		Long id,
		int cantidad,
		boolean comprado,
		Long listaCompraId,
		Long productoId
){}
