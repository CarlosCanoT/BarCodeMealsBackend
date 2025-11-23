package com.tfg.barcodemeals.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tfg.barcodemeals.dto.request.ListaCompraRequest;
import com.tfg.barcodemeals.dto.response.ListaCompraResponse;
import com.tfg.barcodemeals.model.ListaCompra;

public class ListaCompraMapper {
public ListaCompraResponse toResponse(ListaCompra listaCompra) {
	if(listaCompra == null) return null;
	return new ListaCompraResponse(
			listaCompra.getId(),
			listaCompra.getNombre(),
			listaCompra.getFechaCreacion(),
			listaCompra.getPrecioTotal(),
			Optional.ofNullable(listaCompra.getLineas())
					.orElse(List.of())
					.stream()
					.map(l -> l.getId())
					.collect(Collectors.toList())
		);
}
public ListaCompraRequest toRequest(ListaCompra listaCompra) {
	if(listaCompra == null) return null;
	return new ListaCompraRequest(
			listaCompra.getId(),
			listaCompra.getNombre()
		);
			
}
}
