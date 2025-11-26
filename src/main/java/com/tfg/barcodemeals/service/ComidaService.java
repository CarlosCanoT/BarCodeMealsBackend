package com.tfg.barcodemeals.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.request.ComidaRequest;
import com.tfg.barcodemeals.dto.response.ComidaResponse;
import com.tfg.barcodemeals.mapper.ComidaMapper;
import com.tfg.barcodemeals.model.Comida;
import com.tfg.barcodemeals.model.TipoComida;
import com.tfg.barcodemeals.repository.ComidaRepository;
import com.tfg.barcodemeals.repository.PlatoRepository;
import com.tfg.barcodemeals.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComidaService implements CrudService<ComidaResponse, ComidaRequest>{

	private final ComidaRepository comidaRepository;
    private final ProductoRepository productoRepository;
    private final PlatoRepository platoRepository;
	private final ComidaMapper comidaMapper;

	
	@Override
	public Optional<ComidaResponse> obtenerPorId(Long id) {
		return comidaRepository.findById(id)
				.map(comidaMapper::toResponse);
	}

	@Override
	public List<ComidaResponse> obtenerTodos() {
		return comidaRepository.findAll()
				.stream()
				.map(comidaMapper::toResponse)
				.toList();
	}

	@Override
	public ComidaResponse crear(ComidaRequest request) {
		Comida comida = new Comida();
		comida.setTipo(TipoComida.valueOf(request.tipo()));
		comida.setFecha(request.fecha());
		if(request.platoIds() != null && !request.platoIds().isEmpty()) {
			request.platoIds().forEach(p -> {
				platoRepository.findById(p).ifPresent(comida.getPlatos()::add);
			});
		}
		return comidaMapper.toResponse(comidaRepository.save(comida));
	}

	@Override
	public Optional<ComidaResponse> actualizar(Long id, ComidaRequest request) {
		return comidaRepository.findById(id)
				.map(comida -> {
					comida.setTipo(TipoComida.valueOf(request.tipo()));
					comida.setFecha(request.fecha());
					comida.setPlatos(platoRepository.findAllById(request.platoIds()));
					comida.setProductos(productoRepository.findAllById(request.productoIds()));
				    return comidaMapper.toResponse(comidaRepository.save(comida));
				});
	}

	@Override
	public boolean eliminar(Long id) {
		return comidaRepository.findById(id)
				.map(c -> {
					comidaRepository.delete(c);
					return true;
				}).orElse(false);
	}

}
