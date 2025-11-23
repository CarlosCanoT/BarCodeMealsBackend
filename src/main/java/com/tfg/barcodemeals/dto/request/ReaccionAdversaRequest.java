package com.tfg.barcodemeals.dto.request;

public record ReaccionAdversaRequest (
	Long id,
	String tipo,
	String nivelRiesgo
) {}
