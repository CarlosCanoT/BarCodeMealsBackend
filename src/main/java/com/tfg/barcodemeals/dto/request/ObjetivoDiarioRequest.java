package com.tfg.barcodemeals.dto.request;

public record ObjetivoDiarioRequest (
		 Long id,
		 double kcal,
		 double grasa,
		 double hidratosCarbono,
		 double azucar,
		 double proteina,
		 double sal,
		 Long usuarioId	
) {}
