package com.tfg.barcodemeals.dto.response;

public record ObjetivoDiarioResponse (
		 double kcal,
		 double proteina,
		 double hidratosCarbono,
		 double azucar,
		 double grasa,
		 double sal	
) {}