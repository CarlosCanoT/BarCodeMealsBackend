package com.tfg.barcodemeals.dto.request;

public record LineaCompraRequest(
	Long id,
	int cantidad,
	boolean comprado,
	Long listaCompraId,
	Long productoId
) {}
