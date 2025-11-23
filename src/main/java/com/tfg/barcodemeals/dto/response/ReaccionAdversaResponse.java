package com.tfg.barcodemeals.dto.response;

import java.util.List;

public record ReaccionAdversaResponse (
	Long id,
	String tipo,          
	String nivelRiesgo,    
	String descripcion,
	List<Long> usuariosIds,
	List<Long> productosIds
){}
