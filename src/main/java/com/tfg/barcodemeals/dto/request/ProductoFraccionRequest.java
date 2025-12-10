package com.tfg.barcodemeals.dto.request;

public record ProductoFraccionRequest (
	Long productoOriginalId,
	double pesoConsumido,
	Long usuarioId
	
) {}
