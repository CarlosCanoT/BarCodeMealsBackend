package com.tfg.barcodemeals.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.request.LineaCompraRequest;
import com.tfg.barcodemeals.dto.response.LineaCompraResponse;
import com.tfg.barcodemeals.mapper.LineaCompraMapper;
import com.tfg.barcodemeals.model.LineaCompra;
import com.tfg.barcodemeals.model.ListaCompra;
import com.tfg.barcodemeals.model.Producto;
import com.tfg.barcodemeals.repository.LineaCompraRepository;
import com.tfg.barcodemeals.repository.ListaCompraRepository;
import com.tfg.barcodemeals.repository.ProductoRepository;
import com.tfg.barcodemeals.repository.RegistroDiarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LineaCompraService implements CrudService<LineaCompraResponse, LineaCompraRequest>{

	private final LineaCompraRepository lineaCompraRepository;
	private final ListaCompraRepository listaCompraRepository;
	private final ProductoRepository productoRepository;
	private final LineaCompraMapper lineaCompraMapper;
	
	@Override
	public Optional<LineaCompraResponse> obtenerPorId(Long id) {
		return lineaCompraRepository.findById(id)
				.map(lineaCompraMapper::toResponse);
	}

	@Override
	public List<LineaCompraResponse> obtenerTodos() {
		return lineaCompraRepository.findAll()
				.stream()
				.map(lineaCompraMapper::toResponse)
				.toList();
	}

	@Override
	public LineaCompraResponse crear(LineaCompraRequest request) {
		LineaCompra lineaCompra = new LineaCompra();
		lineaCompra.setCantidad(request.cantidad());
		lineaCompra.setComprado(false);
		lineaCompra.setListaCompra(listaCompraRepository.findById(request.listaCompraId())
	            .orElseThrow(() -> new RuntimeException("Lista no encontrada")));
		lineaCompra.setProducto(productoRepository.findById(request.productoId())
	            .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
		return lineaCompraMapper.toResponse(lineaCompraRepository.save(lineaCompra));
	}

	@Override
	public Optional<LineaCompraResponse> actualizar(Long id, LineaCompraRequest request) {
		return lineaCompraRepository.findById(id)
				.map(lineaCompra -> {
				lineaCompra.setCantidad(request.cantidad());
				lineaCompra.setComprado(request.comprado());
				listaCompraRepository.findById(request.listaCompraId())
			    .ifPresent(lineaCompra::setListaCompra);
				productoRepository.findById(request.productoId())
			    .ifPresent(lineaCompra::setProducto);
	            return lineaCompraMapper.toResponse(lineaCompraRepository.save(lineaCompra));
			});
	}

	@Override
	public boolean eliminar(Long id) {
		return lineaCompraRepository.findById(id)
				.map(l -> {
				lineaCompraRepository.delete(l);
				return true;
				}).orElse(false);
	}

}
