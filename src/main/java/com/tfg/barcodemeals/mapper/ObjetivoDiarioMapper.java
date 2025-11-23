package com.tfg.barcodemeals.mapper;

import com.tfg.barcodemeals.dto.request.ObjetivoDiarioRequest;
import com.tfg.barcodemeals.dto.response.ObjetivoDiarioResponse;
import com.tfg.barcodemeals.model.ObjetivoDiario;

public class ObjetivoDiarioMapper {
public ObjetivoDiarioResponse toResponse(ObjetivoDiario objetivoDiario) {
	if(objetivoDiario == null) return null;
	
	return new ObjetivoDiarioResponse(
			objetivoDiario.getId(),
			objetivoDiario.getKcal(),
			objetivoDiario.getGrasa(),
			objetivoDiario.getHidratosCarbono(),
			objetivoDiario.getAzucar(),			
			objetivoDiario.getProteina(),
			objetivoDiario.getSal(),
			objetivoDiario.getUsuario().getId()
		);
}
public ObjetivoDiarioRequest toRequest(ObjetivoDiario objetivoDiario) {
	if(objetivoDiario == null) return null;
	
	return new ObjetivoDiarioRequest(
			objetivoDiario.getId(),
			objetivoDiario.getKcal(),
			objetivoDiario.getGrasa(),
			objetivoDiario.getHidratosCarbono(),
			objetivoDiario.getAzucar(),			
			objetivoDiario.getProteina(),
			objetivoDiario.getSal(),
			objetivoDiario.getUsuario().getId()
		);
}
}
