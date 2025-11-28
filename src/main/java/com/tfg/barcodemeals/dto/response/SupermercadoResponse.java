package com.tfg.barcodemeals.dto.response;

import java.time.LocalTime;

public record SupermercadoResponse(
	    Long id,
	    String nombre,
	    String direccion,
	    String telefono,
	    String web,
	    Long ciudad,
	    LocalTime horaApertura,
	    LocalTime horaCierre
) {}