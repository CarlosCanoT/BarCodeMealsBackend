package com.tfg.barcodemeals.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.response.ReaccionAdversaResponse;
import com.tfg.barcodemeals.mapper.ReaccionAdversaMapper;
import com.tfg.barcodemeals.repository.ReaccionAdversaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReaccionAdversaService {
	private final ReaccionAdversaRepository reaccionAdversaRepository;
	private final ReaccionAdversaMapper reaccionAdversaMapper;
	
	public Optional<ReaccionAdversaResponse> obtenerPorId(Long id) {
		return reaccionAdversaRepository.findById(id)
				.map(reaccionAdversaMapper::toResponse);
	}

	
	public List<ReaccionAdversaResponse> obtenerTodos() {
		return reaccionAdversaRepository.findAll()
				.stream()
				.map(reaccionAdversaMapper::toResponse)
				.toList();
	}
}
