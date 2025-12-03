package com.tfg.barcodemeals.mapper;

import org.springframework.stereotype.Component;

import com.tfg.barcodemeals.dto.response.SupermercadoResponse;
import com.tfg.barcodemeals.model.Supermercado;

@Component
public class SupermercadoMapper {
public SupermercadoResponse toResponse(Supermercado supermercado) {
	if(supermercado == null) return null;
	return new SupermercadoResponse(
			supermercado.getId(),
			supermercado.getNombre(),
			supermercado.getDireccion(),
			supermercado.getTelefono(),
			supermercado.getWeb(),
			supermercado.getCiudad().getId(),
			supermercado.getHoraApertura(),
			supermercado.getHoraCierre()
	);
}

}
