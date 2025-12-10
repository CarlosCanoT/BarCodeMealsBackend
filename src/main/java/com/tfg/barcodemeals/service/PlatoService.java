package com.tfg.barcodemeals.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.request.PlatoRequest;
import com.tfg.barcodemeals.dto.response.PlatoResponse;
import com.tfg.barcodemeals.mapper.PlatoMapper;
import com.tfg.barcodemeals.model.Plato;
import com.tfg.barcodemeals.model.Producto;
import com.tfg.barcodemeals.repository.PlatoRepository;
import com.tfg.barcodemeals.repository.ProductoRepository;
import com.tfg.barcodemeals.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlatoService implements CrudService<PlatoResponse, PlatoRequest> {
	private final PlatoRepository platoRepository;
	private final ProductoRepository productoRepository;	
	private final UsuarioRepository usuarioRepository;
	private final PlatoMapper platoMapper;
	
	@Override
	public Optional<PlatoResponse> obtenerPorId(Long id) {
		return platoRepository.findById(id)
				.map(platoMapper::toResponse);
	}

	@Override
	public List<PlatoResponse> obtenerTodos() {
		return platoRepository.findAll()
				.stream()
				.map(platoMapper::toResponse)
				.toList();
	}

	public List<PlatoResponse> obtenerVisiblesParaUsuario(Long usuarioId) {
	    return platoRepository.findVisibleForUser(usuarioId)
	            .stream()
	            .map(platoMapper::toResponse)
	            .toList();
	}

	@Override
	public PlatoResponse crear(PlatoRequest request) {
		Plato plato = new Plato();
		plato.setNombre(request.nombre());
		plato.setDescripcion(request.descripcion());
		plato.setEsPublico(request.esPublico());
		  if (request.productosIds() != null && !request.productosIds().isEmpty()) {
		        List<Producto> productos = productoRepository.findAllById(request.productosIds());
		        plato.setProductos(productos);
		    }
		  plato.setUsuario(request.usuarioId() != null ? usuarioRepository.findById(request.usuarioId()).orElse(null) : null);

		return platoMapper.toResponse(platoRepository.save(plato));
	}

	@Override
	public Optional<PlatoResponse> actualizar(Long id, PlatoRequest request) {
		 return platoRepository.findById(id)
			        .map(plato -> {
			            plato.setNombre(request.nombre());
			            plato.setDescripcion(request.descripcion());
			            plato.setProductos(productoRepository.findAllById(request.productosIds()));
			            plato.setEsPublico(request.esPublico());
			            return platoMapper.toResponse(platoRepository.save(plato));
			        });
		 
	}

	@Override
	public boolean eliminar(Long id) {
		return platoRepository.findById(id)
				.map(p -> {
				platoRepository.delete(p);
				return true;
				}).orElse(false);
	}

}
