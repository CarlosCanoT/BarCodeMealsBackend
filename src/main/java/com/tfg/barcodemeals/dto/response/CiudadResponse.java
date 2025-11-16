package com.tfg.barcodemeals.dto.response;

import java.util.List;

public record CiudadResponse(
	Long id,
	String nombre,
	String provincia,
	String pais,
	String codigoPostal,
	List<SupermercadoResponse> supermercados

) {}

