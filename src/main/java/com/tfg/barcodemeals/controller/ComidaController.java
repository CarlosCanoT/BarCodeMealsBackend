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

import com.tfg.barcodemeals.dto.request.ComidaRequest;
import com.tfg.barcodemeals.dto.response.ComidaResponse;
import com.tfg.barcodemeals.service.ComidaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comidas")
@RequiredArgsConstructor
public class ComidaController implements CrudController<ComidaResponse, ComidaRequest> {

	private final ComidaService comidaService;
	
	@Override
	@GetMapping
	public List<ComidaResponse> obtenerTodos() {
		return comidaService.obtenerTodos();
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ComidaResponse> obtenerPorId(@PathVariable Long id) {
		return comidaService.obtenerPorId(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	@PostMapping
	public ResponseEntity<ComidaResponse> crear(@RequestBody ComidaRequest request) {
		return ResponseEntity.ok(comidaService.crear(request));
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<ComidaResponse> actualizar(@PathVariable Long id, @RequestBody ComidaRequest request) {
		return comidaService.actualizar(id, request)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

    @Override
    @DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		return comidaService.eliminar(id) ? ResponseEntity.noContent().build()
			     : ResponseEntity.notFound().build();
	}

}
