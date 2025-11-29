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

import com.tfg.barcodemeals.dto.request.ObjetivoDiarioRequest;
import com.tfg.barcodemeals.dto.response.ObjetivoDiarioResponse;
import com.tfg.barcodemeals.service.ObjetivoDiarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ObjetivosDiario")
@RequiredArgsConstructor
public class ObjetivoDiarioController implements CrudController<ObjetivoDiarioResponse, ObjetivoDiarioRequest> {

	private final ObjetivoDiarioService objetivoDiarioService;
	
	@Override
	@GetMapping
	public List<ObjetivoDiarioResponse> obtenerTodos() {
		return objetivoDiarioService.obtenerTodos();
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ObjetivoDiarioResponse> obtenerPorId(@PathVariable Long id) {
		return objetivoDiarioService.obtenerPorId(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	@PostMapping
	public ResponseEntity<ObjetivoDiarioResponse> crear(@RequestBody ObjetivoDiarioRequest request) {
		return ResponseEntity.ok(objetivoDiarioService.crear(request));
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<ObjetivoDiarioResponse> actualizar(@PathVariable Long id, @RequestBody ObjetivoDiarioRequest request) {
		return objetivoDiarioService.actualizar(id, request)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		return objetivoDiarioService.eliminar(id) ? ResponseEntity.noContent().build()
			     : ResponseEntity.notFound().build();
	}

}
