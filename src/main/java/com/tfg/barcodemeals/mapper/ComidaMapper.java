package com.tfg.barcodemeals.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tfg.barcodemeals.dto.request.ComidaRequest;
import com.tfg.barcodemeals.dto.response.ComidaResponse;
import com.tfg.barcodemeals.dto.response.EnumResponse;
import com.tfg.barcodemeals.model.Comida;

@Component
public class ComidaMapper {
public ComidaResponse toResponse(Comida comida) {
	if(comida == null) return null;
	
	return new ComidaResponse(
			comida.getId(),
			comida.getNombre(),
            new EnumResponse(comida.getTipo().name(), comida.getTipo().getTexto()),
			comida.getFecha(),
			comida.getRegistroDiario().getId(),
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
			comida.getId(),
			comida.getNombre(),
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









