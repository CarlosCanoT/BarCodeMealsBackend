package com.tfg.barcodemeals.dto.request;

import java.time.LocalDate;
import java.util.List;

public record UsuarioRequest(
	    String nombreUsuario,
	    String contrasena,
	    String apodo,
	    String email,
	    String telefono,
	    LocalDate fechaNacimiento,
	    String genero,
	    Double peso,
	    Double altura,
	    Integer edad,
	    Long ciudadId,
	    List<Long> reaccionesAdversasIds  
) {}
