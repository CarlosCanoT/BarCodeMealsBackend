package com.tfg.barcodemeals.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.request.RegistroDiarioRequest;
import com.tfg.barcodemeals.dto.response.RegistroDiarioResponse;
import com.tfg.barcodemeals.mapper.RegistroDiarioMapper;
import com.tfg.barcodemeals.model.Comida;
import com.tfg.barcodemeals.model.RegistroDiario;
import com.tfg.barcodemeals.model.Usuario;
import com.tfg.barcodemeals.repository.ComidaRepository;
import com.tfg.barcodemeals.repository.RegistroDiarioRepository;
import com.tfg.barcodemeals.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistroDiarioService implements CrudService<RegistroDiarioResponse, RegistroDiarioRequest>{

	private final RegistroDiarioRepository registroDiarioRepository;
	private final ComidaRepository comidaRepository;
	private final UsuarioRepository usuarioRepository;	
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

	public Optional<RegistroDiarioResponse> obtenerPorUsuarioIdyFecha(Long usuarioId, LocalDate fecha) {
		   Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
		   return usuarioOpt.flatMap(usuario ->
           registroDiarioRepository.findByUsuarioAndFecha(usuario, fecha)
                   .map(registroDiarioMapper::toResponse)
   );
	}
	
	@Override
	public RegistroDiarioResponse crear(RegistroDiarioRequest request) {
	    Usuario usuario = usuarioRepository.findById(request.usuarioId())
	        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	    LocalDate fecha = request.fecha() != null ? request.fecha() : LocalDate.now();
	    
	    RegistroDiario registroDiario = registroDiarioRepository.findByUsuarioAndFecha(usuario, fecha)
	        .orElseGet(() -> {
	            RegistroDiario nuevo = new RegistroDiario();
	            nuevo.setUsuario(usuario);
	            nuevo.setFecha(fecha);
	            nuevo.setPesoTotal(0);
	            nuevo.setKcalTotal(0);
	            nuevo.setGrasaTotal(0);
	            nuevo.setSaturadaTotal(0);
	            nuevo.setNoSaturadaTotal(0);
	            nuevo.setProteinaTotal(0);
	            nuevo.setHidratosCarbonoTotal(0);
	            nuevo.setAzucarTotal(0);
	            nuevo.setSalTotal(0);
	            nuevo.setFibraTotal(0);
	            nuevo.setObjetivoCumplido(false);
	            nuevo.setComidas(new ArrayList<>());
	            return registroDiarioRepository.save(nuevo);
	        });

	    if (request.comidasIds() != null && !request.comidasIds().isEmpty()) {
	        List<Comida> comidas = comidaRepository.findAllById(request.comidasIds());
	        registroDiario.getComidas().addAll(comidas);
	        registroDiario.recalcularTotales();
	        registroDiarioRepository.save(registroDiario);
	    }

	    return registroDiarioMapper.toResponse(registroDiario);
	}


	@Override
	public Optional<RegistroDiarioResponse> actualizar(Long id, RegistroDiarioRequest request) {
	    return registroDiarioRepository.findById(id)
	            .map(registroDiario -> {
	                registroDiario.setObjetivoCumplido(request.objetivoCumplido());

	                if (request.comidasIds() != null && !request.comidasIds().isEmpty()) {
	                    registroDiario.setComidas(comidaRepository.findAllById(request.comidasIds()));
	                }

	                if (request.fecha() != null) {
	                    registroDiario.setFecha(request.fecha());
	                }

	                registroDiario.recalcularTotales(); 
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
