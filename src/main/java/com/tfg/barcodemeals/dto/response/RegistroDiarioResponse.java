package com.tfg.barcodemeals.dto.response;

import java.time.LocalDate;
import java.util.List;

public record RegistroDiarioResponse (
	   Long id,
       double kcalTotal,
       double grasaTotal,
       double saturadaTotal,
       double noSaturadaTotal,
       double proteinaTotal,
       double hidratosCarbonoTotal,
       double azucarTotal,
       double salTotal,
       double fibraTotal,
       LocalDate fecha,
       boolean objetivoCumplido,
       Long usuarioId,
       List<ComidaResponse> comidas
) {}
