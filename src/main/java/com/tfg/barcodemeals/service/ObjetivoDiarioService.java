package com.tfg.barcodemeals.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.request.ObjetivoDiarioRequest;
import com.tfg.barcodemeals.dto.response.ObjetivoDiarioResponse;
import com.tfg.barcodemeals.mapper.ObjetivoDiarioMapper;
import com.tfg.barcodemeals.model.ObjetivoDiario;
import com.tfg.barcodemeals.repository.ObjetivoDiarioRepository;
import com.tfg.barcodemeals.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObjetivoDiarioService implements CrudService<ObjetivoDiarioResponse, ObjetivoDiarioRequest>{


	private final ObjetivoDiarioRepository objetivoDiarioRepository;
	private final UsuarioRepository usuarioRepository;
	private final ObjetivoDiarioMapper objetivoDiarioMapper;


	@Override
	public Optional<ObjetivoDiarioResponse> obtenerPorId(Long id) {
		return objetivoDiarioRepository.findById(id)
				.map(objetivoDiarioMapper::toResponse);
	}

	@Override
	public List<ObjetivoDiarioResponse> obtenerTodos() {
		return objetivoDiarioRepository.findAll()
				.stream()
				.map(objetivoDiarioMapper::toResponse)
				.toList();
	}

	@Override
	public ObjetivoDiarioResponse crear(ObjetivoDiarioRequest request) {
	ObjetivoDiario objetivoDiario = new ObjetivoDiario();
	objetivoDiario.setKcal(request.kcal());
	objetivoDiario.setGrasa(request.grasa());
	objetivoDiario.setHidratosCarbono(request.hidratosCarbono());
	objetivoDiario.setAzucar(request.azucar());
	objetivoDiario.setProteina(request.proteina());
	objetivoDiario.setSal(request.sal());
	objetivoDiario.setUsuario(usuarioRepository.findById(request.usuarioId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
	return objetivoDiarioMapper.toResponse(objetivoDiarioRepository.save(objetivoDiario));
	}

	@Override
	public Optional<ObjetivoDiarioResponse> actualizar(Long id, ObjetivoDiarioRequest request) {
		return objetivoDiarioRepository.findById(id)
				.map(objetivoDiario -> {
					objetivoDiario.setGrasa(request.grasa());
					objetivoDiario.setHidratosCarbono(request.hidratosCarbono());
					objetivoDiario.setAzucar(request.azucar());
					objetivoDiario.setProteina(request.proteina());
					objetivoDiario.setSal(request.sal());
					usuarioRepository.findById(request.usuarioId())
					.ifPresent(objetivoDiario::setUsuario);
			         return objetivoDiarioMapper.toResponse(objetivoDiarioRepository.save(objetivoDiario));
				});
	}

	@Override
	public boolean eliminar(Long id) {
		return objetivoDiarioRepository.findById(id)
				.map(o -> {
					objetivoDiarioRepository.delete(o);
					return true;
				}).orElse(false);
	}

}
