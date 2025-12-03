package com.tfg.barcodemeals.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tfg.barcodemeals.dto.response.CiudadResponse;
import com.tfg.barcodemeals.model.Ciudad;

@Component
public class CiudadMapper {
public CiudadResponse toResponse(Ciudad ciudad) {
	if(ciudad == null) return null;
	
	return new CiudadResponse(
			ciudad.getId(),
			ciudad.getNombre(),
			ciudad.getProvincia(),
			ciudad.getPais(),
			ciudad.getCodigoPostal(),
			Optional.ofNullable(ciudad.getSupermercados())
					.orElse(List.of())
					.stream()
					.map(s -> s.getId())
					.collect(Collectors.toList())
			);
}

}
