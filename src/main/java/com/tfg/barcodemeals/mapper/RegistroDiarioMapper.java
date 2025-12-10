package com.tfg.barcodemeals.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import com.tfg.barcodemeals.controller.RegistroDiarioController;
import com.tfg.barcodemeals.dto.request.RegistroDiarioRequest;
import com.tfg.barcodemeals.dto.response.RegistroDiarioResponse;
import com.tfg.barcodemeals.model.RegistroDiario;

@Component
public class RegistroDiarioMapper {

public RegistroDiarioResponse toResponse(RegistroDiario registroDiario) {
	if(registroDiario == null) return null;
	return new RegistroDiarioResponse(
			registroDiario.getId(),
			registroDiario.getKcalTotal(),
			registroDiario.getGrasaTotal(),
			registroDiario.getSaturadaTotal(),
			registroDiario.getNoSaturadaTotal(),
			registroDiario.getHidratosCarbonoTotal(),
			registroDiario.getAzucarTotal(),
			registroDiario.getProteinaTotal(),
			registroDiario.getSalTotal(),
			registroDiario.getFibraTotal(),
			registroDiario.getFecha(),
			registroDiario.isObjetivoCumplido(),
			registroDiario.getUsuario().getId(),
			Optional.ofNullable(registroDiario.getComidas())
					.orElse(List.of())
					.stream()
					.map(c -> c.getId())
					.collect(Collectors.toList())
		);
}
public RegistroDiarioRequest toRequest (RegistroDiario registroDiario) {
	if(registroDiario == null) return null;
	return new RegistroDiarioRequest(
			registroDiario.getId(),
			registroDiario.isObjetivoCumplido(),
			Optional.ofNullable(registroDiario.getComidas())
			.orElse(List.of())
			.stream()
			.map(c -> c.getId())
			.collect(Collectors.toList()),
			registroDiario.getFecha(),
			registroDiario.getUsuario().getId()
	);
}
}
