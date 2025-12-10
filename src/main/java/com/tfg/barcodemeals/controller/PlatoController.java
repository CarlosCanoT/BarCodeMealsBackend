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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.barcodemeals.dto.request.PlatoRequest;
import com.tfg.barcodemeals.dto.response.PlatoResponse;
import com.tfg.barcodemeals.service.PlatoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/platos")
@RequiredArgsConstructor
public class PlatoController implements CrudController<PlatoResponse, PlatoRequest> {
	
	private final PlatoService platoService;
	
	@Override
	@GetMapping
	public List<PlatoResponse> obtenerTodos() {
		return platoService.obtenerTodos();
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<PlatoResponse> obtenerPorId(@PathVariable Long id) {
		return platoService.obtenerPorId(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/visibles")
	public List<PlatoResponse> obtenerVisiblesParaUsuario(@RequestParam Long usuarioId) {
	    return platoService.obtenerVisiblesParaUsuario(usuarioId);
	}


	@Override
	@PostMapping
	public ResponseEntity<PlatoResponse> crear(@RequestBody PlatoRequest request) {
		return ResponseEntity.ok(platoService.crear(request));
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<PlatoResponse> actualizar(@PathVariable Long id, @RequestBody PlatoRequest request) {
		return platoService.actualizar(id, request)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		return platoService.eliminar(id) ? ResponseEntity.noContent().build()
			     : ResponseEntity.notFound().build();
	}

}
