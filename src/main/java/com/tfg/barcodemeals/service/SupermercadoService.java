package com.tfg.barcodemeals.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.request.SupermercadoRequest;
import com.tfg.barcodemeals.dto.response.SupermercadoResponse;
import com.tfg.barcodemeals.mapper.SupermercadoMapper;
import com.tfg.barcodemeals.model.Supermercado;
import com.tfg.barcodemeals.repository.SupermercadoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupermercadoService  implements CrudService<SupermercadoResponse, SupermercadoRequest>{
	
	private final SupermercadoRepository supermercadoRepository;
	private final SupermercadoMapper supermercadoMapper;
	
	@Override
	public Optional<SupermercadoResponse> obtenerPorId(Long id) {
		return supermercadoRepository.findById(id)
				.map(supermercadoMapper::toResponse);
	}

	@Override
	public List<SupermercadoResponse> obtenerTodos() {
		return supermercadoRepository.findAll()
				.stream()
				.map(supermercadoMapper::toResponse)
				.toList();
	}

	@Override
	public SupermercadoResponse crear(SupermercadoRequest request) {
		Supermercado supermercado = new Supermercado();
		supermercado.setRating(request.rating());
	}

	@Override
	public Optional<SupermercadoResponse> actualizar(Long id, SupermercadoRequest request) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean eliminar(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
