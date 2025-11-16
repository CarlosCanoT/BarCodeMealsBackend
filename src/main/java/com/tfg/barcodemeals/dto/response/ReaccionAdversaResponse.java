package com.tfg.barcodemeals.dto.response;

public record ReaccionAdversaResponse (
	Long id,
	String nombre,         
	String tipo,          
	String nivelRiesgo,    
	String descripcion
){}
