package com.tfg.barcodemeals.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.request.UsuarioRequest;
import com.tfg.barcodemeals.dto.response.UsuarioResponse;
import com.tfg.barcodemeals.mapper.UsuarioMapper;
import com.tfg.barcodemeals.model.Genero;
import com.tfg.barcodemeals.model.TipoComida;
import com.tfg.barcodemeals.model.Usuario;
import com.tfg.barcodemeals.repository.CiudadRepository;
import com.tfg.barcodemeals.repository.PrecioRepository;
import com.tfg.barcodemeals.repository.ReaccionAdversaRepository;
import com.tfg.barcodemeals.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService implements CrudService<UsuarioResponse, UsuarioRequest> {

    private final CiudadRepository ciudadRepository;
	private final UsuarioRepository usuarioRepository;
	private final ReaccionAdversaRepository reaccionAdversaRepository;
	private final UsuarioMapper usuarioMapper;



	@Override
	public Optional<UsuarioResponse> obtenerPorId(Long id) {
		return usuarioRepository.findById(id)
				.map(usuarioMapper::toResponse);
	}

	@Override
	public List<UsuarioResponse> obtenerTodos() {
		return usuarioRepository.findAll()
				.stream()
				.map(usuarioMapper::toResponse)
				.toList();
	}

	@Override
	public UsuarioResponse crear(UsuarioRequest request) {
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario(request.nombreUsuario());
		usuario.setContrasena(request.contrasena());
		usuario.setApodo(request.apodo());
		usuario.setEmail(request.email());
		usuario.setTelefono(request.telefono());
		usuario.setFechaNacimiento(request.fechaNacimiento());
		usuario.setGenero(Genero.valueOf(request.genero()));
		usuario.setAltura(request.altura());
		usuario.setCiudad(ciudadRepository.findById(request.ciudadId())
	            .orElseThrow(() -> new RuntimeException("Ciudad no encontrada")));
		if(request.reaccionesAdversasIds() != null && !request.reaccionesAdversasIds().isEmpty()) {
			request.reaccionesAdversasIds().forEach(r -> {
				reaccionAdversaRepository.findById(r).ifPresent(usuario.getReaccionesAdversas()::add);
			});
		}
		return usuarioMapper.toResponse(usuarioRepository.save(usuario));
	}

	@Override
	public Optional<UsuarioResponse> actualizar(Long id, UsuarioRequest request) {
		return usuarioRepository.findById(id)
				.map(usuario -> {
					usuario.setNombreUsuario(request.nombreUsuario());
					usuario.setContrasena(request.contrasena());
					usuario.setApodo(request.apodo());
					usuario.setEmail(request.email());
					usuario.setTelefono(request.telefono());
					usuario.setFechaNacimiento(request.fechaNacimiento());
					usuario.setGenero(Genero.valueOf(request.genero()));
					usuario.setAltura(request.altura());
					ciudadRepository.findById(request.ciudadId())
				    .ifPresent(usuario::setCiudad);
					usuario.setReaccionesAdversas(reaccionAdversaRepository.findAllById(request.reaccionesAdversasIds()));

					return usuarioMapper.toResponse(usuarioRepository.save(usuario));
				});
	}

	@Override
	public boolean eliminar(Long id) {
		return usuarioRepository.findById(id)
				.map(c -> {
					usuarioRepository.delete(c);
					return true;
				}).orElse(false);
	}
	

}
