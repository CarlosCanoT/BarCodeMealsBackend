package com.tfg.barcodemeals.dto.request;

public record LineaCompraRequest(
	int cantidad,
	boolean comprado,
	Long listaCompraId,
	Long productoId
) {}
