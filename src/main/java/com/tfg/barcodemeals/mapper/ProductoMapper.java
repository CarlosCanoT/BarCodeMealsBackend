package com.tfg.barcodemeals.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tfg.barcodemeals.dto.request.ProductoRequest;
import com.tfg.barcodemeals.dto.response.ProductoResponse;
import com.tfg.barcodemeals.model.Producto;

@Component
public class ProductoMapper {
public ProductoResponse toResponse(Producto producto) {
	if(producto == null) return null;
	
	return new ProductoResponse(
			producto.getId(),
			producto.getBarcode(),
			producto.getNombre(),
			producto.getMarca(),
			producto.getCategoria().toString(),
			producto.getPesoEmpaque(),
			producto.getPesoConsumido(),
			producto.getKcal(),
			producto.getGrasa(),
			producto.getSaturada(),
			producto.getNoSaturada(),
			producto.getHidratosCarbono(),
			producto.getAzucar(),
			producto.getProteina(),
			producto.getSal(),
			producto.getFibra(),
			producto.getUnidad().toString(),
			producto.getEnvase().toString(),
			Optional.ofNullable(producto.getReaccionesAdversas())
					.orElse(List.of())
					.stream()
					.map(p -> p.getId())
					.collect(Collectors.toList()),
			Optional.ofNullable(producto.getPrecios())
					.orElse(List.of())
					.stream()
					.map(p -> p.getId())
					.collect(Collectors.toList())
		);
}

public ProductoRequest toRequest(Producto producto) {
	if(producto == null) return null;
	
	return new ProductoRequest(
		producto.getId(),
		producto.getBarcode(),	
		producto.getNombre(),
		producto.getMarca(),	
		producto.getCategoria().toString(),	
		producto.getPesoEmpaque(),	
		producto.getPesoConsumido(),	
		producto.getKcal(),	
		producto.getGrasa(),	
		producto.getSaturada(),	
		producto.getNoSaturada(),	
		producto.getHidratosCarbono(),	
		producto.getAzucar(),	
		producto.getProteina(),	
		producto.getSal(),	
		producto.getFibra(),	
		producto.getUnidad().toString(),	
		producto.getEnvase().toString(),
		Optional.ofNullable(producto.getReaccionesAdversas())
				.orElse(List.of())
				.stream()
				.map(p -> p.getId())
				.collect(Collectors.toList())				
	);
}
}
