package com.tfg.barcodemeals.dto.response;

import java.time.LocalDate;
import java.util.List;

public record ComidaResponse(
	    Long id,
	    String tipo,
	    LocalDate fecha,
	    List<PlatoResponse> platos,
	    List<ProductoResponse> productos,
	    double kcalTotal,
	    double grasaTotal,
	    double saturadaTotal,
	    double noSaturadaTotal,
	    double proteinaTotal,
	    double hidratosCarbonoTotal,
	    double azucarTotal,
	    double salTotal,
	    double fibraTotal
) {}