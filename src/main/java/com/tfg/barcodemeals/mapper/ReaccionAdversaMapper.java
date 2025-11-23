package com.tfg.barcodemeals.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tfg.barcodemeals.dto.request.ReaccionAdversaRequest;
import com.tfg.barcodemeals.dto.response.ReaccionAdversaResponse;
import com.tfg.barcodemeals.model.ReaccionAdversa;

public class ReaccionAdversaMapper {
public ReaccionAdversaResponse toResponse(ReaccionAdversa reaccionAdversa) {
	if(reaccionAdversa == null) return null;
	return new ReaccionAdversaResponse(
		reaccionAdversa.getId(),
		reaccionAdversa.getTipo().toString(),
		reaccionAdversa.getNivelRiesgo().toString(),
		reaccionAdversa.getDescripcion(),
		Optional.ofNullable(reaccionAdversa.getUsuarios())
				.orElse(List.of())
				.stream()
				.map(u -> u.getId())
				.collect(Collectors.toList()),
		Optional.ofNullable(reaccionAdversa.getProductos())
				.orElse(List.of())
				.stream()
				.map(p -> p.getId())
				.collect(Collectors.toList())
	);
}
public ReaccionAdversaRequest toRequest(ReaccionAdversa reaccionAdversa) {
	if(reaccionAdversa == null) return null;
	return new ReaccionAdversaRequest(
		reaccionAdversa.getId(),
		reaccionAdversa.getTipo().toString(),
		reaccionAdversa.getNivelRiesgo().toString()
	);
}
}
