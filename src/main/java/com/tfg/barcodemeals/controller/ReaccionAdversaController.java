package com.tfg.barcodemeals.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.barcodemeals.dto.response.ReaccionAdversaResponse;
import com.tfg.barcodemeals.service.ReaccionAdversaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reaccionesAdversas")
@RequiredArgsConstructor
public class ReaccionAdversaController {
	
	private final ReaccionAdversaService reaccionAdversaService;

	@GetMapping
	public List<ReaccionAdversaResponse> obtenerTodos() {
		return reaccionAdversaService.obtenerTodos();
	}
 
	@GetMapping("/{id}")
	public ResponseEntity<ReaccionAdversaResponse> obtenerPorId(@PathVariable Long id) {
		return reaccionAdversaService.obtenerPorId(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
