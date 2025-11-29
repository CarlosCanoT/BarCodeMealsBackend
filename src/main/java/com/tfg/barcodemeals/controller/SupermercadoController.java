package com.tfg.barcodemeals.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.barcodemeals.dto.response.SupermercadoResponse;
import com.tfg.barcodemeals.service.SupermercadoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/supermercados")
@RequiredArgsConstructor
public class SupermercadoController {
	
	private final SupermercadoService supermercadoService;

	@GetMapping
	public List<SupermercadoResponse> obtenerTodos() {
		return supermercadoService.obtenerTodos();
	}
 
	@GetMapping("/{id}")
	public ResponseEntity<SupermercadoResponse> obtenerPorId(@PathVariable Long id) {
		return supermercadoService.obtenerPorId(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}

