package com.tfg.barcodemeals.dto.request;

public record ObjetivoDiarioRequest (
		 double kcal,
		 double proteina,
		 double hidratosCarbono,
		 double azucar,
		 double grasa,
		 double sal	
) {}
