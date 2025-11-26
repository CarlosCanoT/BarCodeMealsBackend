package com.tfg.barcodemeals.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.request.RegistroDiarioRequest;
import com.tfg.barcodemeals.dto.response.RegistroDiarioResponse;
import com.tfg.barcodemeals.mapper.RegistroDiarioMapper;
import com.tfg.barcodemeals.model.Comida;
import com.tfg.barcodemeals.model.RegistroDiario;
import com.tfg.barcodemeals.repository.ComidaRepository;
import com.tfg.barcodemeals.repository.RegistroDiarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistroDiarioService implements CrudService<RegistroDiarioResponse, RegistroDiarioRequest>{

	private final RegistroDiarioRepository registroDiarioRepository;
	private final ComidaRepository comidaRepository;
	private final RegistroDiarioMapper registroDiarioMapper;



 
	@Override
	public Optional<RegistroDiarioResponse> obtenerPorId(Long id) {
		return registroDiarioRepository.findById(id)
				.map(registroDiarioMapper::toResponse);
	}

	@Override
	public List<RegistroDiarioResponse> obtenerTodos() {
		return registroDiarioRepository.findAll()
				.stream()
				.map(registroDiarioMapper::toResponse)
				.toList();
	}

	@Override
	public RegistroDiarioResponse crear(RegistroDiarioRequest request) {
		RegistroDiario registroDiario = new RegistroDiario();
		registroDiario.setObjetivoCumplido(false);
		if(request.comidasIds() != null && !request.comidasIds().isEmpty()) {
			List<Comida> comidas = comidaRepository.findAllById(request.comidasIds());
			registroDiario.setComidas(comidas);
		}
		return registroDiarioMapper.toResponse(registroDiarioRepository.save(registroDiario));
	}

	@Override
	public Optional<RegistroDiarioResponse> actualizar(Long id, RegistroDiarioRequest request) {
		return registroDiarioRepository.findById(id)
				.map(registroDiario -> {
					registroDiario.setObjetivoCumplido(request.objetivoCumplido());
					registroDiario.setComidas(comidaRepository.findAllById(request.comidasIds()));
					return registroDiarioMapper.toResponse(registroDiarioRepository.save(registroDiario));
				});
				
	}

	@Override
	public boolean eliminar(Long id) {
		return registroDiarioRepository.findById(id)
				.map(r -> {
					registroDiarioRepository.delete(r);
					return true;
				}).orElse(false);
		
	}
	

}
