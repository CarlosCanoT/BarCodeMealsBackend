package com.tfg.barcodemeals.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.response.SupermercadoResponse;
import com.tfg.barcodemeals.mapper.SupermercadoMapper;
import com.tfg.barcodemeals.repository.SupermercadoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupermercadoService {
	
	private final SupermercadoRepository supermercadoRepository;
	private final SupermercadoMapper supermercadoMapper;
	
	public Optional<SupermercadoResponse> obtenerPorId(Long id) {
		return supermercadoRepository.findById(id)
				.map(supermercadoMapper::toResponse);
	}

	public List<SupermercadoResponse> obtenerTodos() {
		return supermercadoRepository.findAll()
				.stream()
				.map(supermercadoMapper::toResponse)
				.toList();
	}

}
