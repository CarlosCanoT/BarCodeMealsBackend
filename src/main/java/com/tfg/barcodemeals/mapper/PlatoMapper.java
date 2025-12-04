package com.tfg.barcodemeals.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tfg.barcodemeals.dto.request.PlatoRequest;
import com.tfg.barcodemeals.dto.response.PlatoResponse;
import com.tfg.barcodemeals.model.Plato;

@Component
public class PlatoMapper {
	public PlatoResponse toResponse(Plato plato) {
		if(plato == null) return null;
		
		return new PlatoResponse(
				plato.getId(),
				plato.getNombre(),
				plato.getDescripcion(),
				plato.getPeso(),
				plato.getKcal(),
				plato.getGrasa(),
				plato.getSaturada(),
				plato.getNoSaturada(),
				plato.getHidratosCarbono(),
				plato.getAzucar(),
				plato.getProteina(),
				plato.getSal(),
				plato.getFibra(),
				Optional.ofNullable(plato.getProductos())
						.orElse(List.of())
						.stream()
						.map(p -> p.getId())
						.collect(Collectors.toList())
		);	
	}
	
	public PlatoRequest toRequest(Plato plato) {
		if(plato == null) return null;
	
		return new PlatoRequest(
				plato.getId(),
				plato.getNombre(),
				plato.getDescripcion(),
				Optional.ofNullable(plato.getProductos())
				.orElse(List.of())
				.stream()
				.map(p -> p.getId())
				.collect(Collectors.toList())
			);
	}	
}
