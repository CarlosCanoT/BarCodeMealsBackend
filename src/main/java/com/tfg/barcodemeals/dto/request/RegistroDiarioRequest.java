package com.tfg.barcodemeals.dto.request;

import java.time.LocalDate;
import java.util.List;

public record RegistroDiarioRequest(
	Long id,
	boolean objetivoCumplido,
	List<Long> comidasIds,
	LocalDate fecha,
	Long usuarioId      
){}
