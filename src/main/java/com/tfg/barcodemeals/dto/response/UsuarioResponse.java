package com.tfg.barcodemeals.dto.response;

import java.time.LocalDate;
import java.util.List;

public record UsuarioResponse(
	    Long id,
	    String nombreUsuario,
	    String apodo,
	    String email,
	    String telefono,
	    LocalDate fechaNacimiento,
	    int edad,
	    String genero,
	    double peso,
	    double altura,
	    String ciudadNombre,
	    List<String> reaccionesAdversas 
) {}

