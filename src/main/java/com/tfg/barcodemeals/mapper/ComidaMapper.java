package com.tfg.barcodemeals.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tfg.barcodemeals.dto.request.ComidaRequest;
import com.tfg.barcodemeals.dto.response.ComidaResponse;
import com.tfg.barcodemeals.model.Comida;

public class ComidaMapper {
public ComidaResponse toResponse(Comida comida) {
	if(comida == null) return null;
	
	return new ComidaResponse(
			comida.getId(),
			comida.getTipo().toString(),
			comida.getFecha(),
			Optional.ofNullable(comida.getPlatos())
					.orElse(List.of())
					.stream() 
				    .map(p -> p.getId())
				    .collect(Collectors.toList()),
			Optional.ofNullable(comida.getProductos())
					.orElse(List.of())
					.stream()
					.map(p -> p.getId())
					.collect(Collectors.toList()),
			comida.getPeso(),
			comida.getKcal(),
			comida.getGrasa(),
			comida.getSaturada(),
			comida.getNoSaturada(),
			comida.getHidratosCarbono(),
			comida.getAzucar(),
			comida.getProteina(),
			comida.getSal(),
			comida.getFibra()
			);
			
}
public ComidaRequest toRequest(Comida comida) {
	if(comida==null) return null;
	return new ComidaRequest(
			comida.getTipo().toString(),
			comida.getFecha(),
			Optional.ofNullable(comida.getPlatos())
			.orElse(List.of())
			.stream() 
		    .map(p -> p.getId())
		    .collect(Collectors.toList()),
	Optional.ofNullable(comida.getProductos())
			.orElse(List.of())
			.stream()
			.map(p -> p.getId())
			.collect(Collectors.toList())
			);
			
			
}



}









