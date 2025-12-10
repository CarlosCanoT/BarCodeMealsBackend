package com.tfg.barcodemeals.controller;

import java.time.LocalDate;
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

import com.tfg.barcodemeals.dto.request.RegistroDiarioRequest;
import com.tfg.barcodemeals.dto.response.RegistroDiarioResponse;
import com.tfg.barcodemeals.service.RegistroDiarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/registrosDiarios")
@RequiredArgsConstructor
public class RegistroDiarioController implements CrudController<RegistroDiarioResponse, RegistroDiarioRequest> {
	
	private final RegistroDiarioService registroDiarioService;
	
	@Override
	@GetMapping
	public List<RegistroDiarioResponse> obtenerTodos() {
		return registroDiarioService.obtenerTodos();
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<RegistroDiarioResponse> obtenerPorId(@PathVariable Long id) {
		return registroDiarioService.obtenerPorId(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/usuario/{nombreUsuario}/fecha/{fecha}")
    public ResponseEntity<RegistroDiarioResponse> obtenerPorNombreUsuarioYFecha(@PathVariable Long usuarioId, @PathVariable LocalDate fecha) {
        return registroDiarioService
                .obtenerPorUsuarioIdyFecha(usuarioId, fecha)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
	
	@Override
	@PostMapping
	public ResponseEntity<RegistroDiarioResponse> crear(@RequestBody RegistroDiarioRequest request) {
		return ResponseEntity.ok(registroDiarioService.crear(request));
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<RegistroDiarioResponse> actualizar(@PathVariable Long id, @RequestBody RegistroDiarioRequest request) {
		return registroDiarioService.actualizar(id, request)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		return registroDiarioService.eliminar(id) ? ResponseEntity.noContent().build()
			     : ResponseEntity.notFound().build();
	}
}