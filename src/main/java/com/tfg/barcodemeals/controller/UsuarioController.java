package com.tfg.barcodemeals.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.barcodemeals.dto.request.UsuarioRequest;
import com.tfg.barcodemeals.dto.response.UsuarioResponse;
import com.tfg.barcodemeals.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController implements CrudController<UsuarioResponse, UsuarioRequest> {
	
	private final UsuarioService usuarioService;
	
	@Override
	@GetMapping
	public List<UsuarioResponse> obtenerTodos() {
		return usuarioService.obtenerTodos();
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponse> obtenerPorId(@PathVariable Long id) {
		return usuarioService.obtenerPorId(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	@PostMapping
	public ResponseEntity<UsuarioResponse> crear(@RequestBody UsuarioRequest request) {
		return ResponseEntity.ok(usuarioService.crear(request));
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResponse> actualizar(@PathVariable Long id, @RequestBody UsuarioRequest request) {
		return usuarioService.actualizar(id, request)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		return usuarioService.eliminar(id) ? ResponseEntity.noContent().build()
			     : ResponseEntity.notFound().build();
	}
}
