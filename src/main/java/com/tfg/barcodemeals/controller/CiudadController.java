package com.tfg.barcodemeals.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.barcodemeals.dto.response.CiudadResponse;
import com.tfg.barcodemeals.service.CiudadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ciudades")
@RequiredArgsConstructor
public class CiudadController {
	
	private final CiudadService ciudadService;

	@GetMapping
	public List<CiudadResponse> obtenerTodos() {
		return ciudadService.obtenerTodos();
	}
 
	@GetMapping("/{id}")
	public ResponseEntity<CiudadResponse> obtenerPorId(@PathVariable Long id) {
		return ciudadService.obtenerPorId(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
