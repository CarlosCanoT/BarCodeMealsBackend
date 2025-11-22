package com.tfg.barcodemeals.dto.request;

import java.util.List;

public record RegistroDiarioRequest(
	Long id,
	boolean objetivoCumplido,
	List<Long> comidasIds
){}
