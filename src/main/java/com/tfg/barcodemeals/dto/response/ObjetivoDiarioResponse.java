package com.tfg.barcodemeals.dto.response;

public record ObjetivoDiarioResponse (
		 Long id,
		 double kcal,
		 double grasa,
		 double hidratosCarbono,
		 double azucar,
		 double proteina,
		 double sal,
		 Long usuarioId
) {}