package com.tfg.barcodemeals.mapper;

import org.springframework.stereotype.Component;

import com.tfg.barcodemeals.dto.request.LineaCompraRequest;
import com.tfg.barcodemeals.dto.response.LineaCompraResponse;
import com.tfg.barcodemeals.model.LineaCompra;

@Component
public class LineaCompraMapper {
public LineaCompraResponse toResponse(LineaCompra lineaCompra) {
	if(lineaCompra==null) return null;
	
	return new LineaCompraResponse(
			lineaCompra.getCantidad(),
			lineaCompra.isComprado(),
			lineaCompra.getPrecioLinea(),
			lineaCompra.getListaCompra().getId(),
			lineaCompra.getProducto().getId()	
			);
	
	}
public LineaCompraRequest toRequest(LineaCompra lineaCompra) {
	if(lineaCompra==null) return null;
	
	return new LineaCompraRequest(
			lineaCompra.getCantidad(),
			lineaCompra.isComprado(),
			lineaCompra.getListaCompra().getId(),
			lineaCompra.getProducto().getId()
			);

	}
}
