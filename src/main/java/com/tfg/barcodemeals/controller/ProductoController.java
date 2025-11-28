package com.tfg.barcodemeals.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.barcodemeals.dto.request.ProductoRequest;
import com.tfg.barcodemeals.dto.response.ProductoResponse;
import com.tfg.barcodemeals.service.ProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController implements CrudController<ProductoResponse, ProductoRequest> {

    private final ProductoService productoService;

	@Override
	@GetMapping
	public List<ProductoResponse> obtenerTodos() {
		return productoService.obtenerTodos();
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ProductoResponse> obtenerPorId(@PathVariable Long id) {
		return productoService.obtenerPorId(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	@PostMapping
	public ResponseEntity<ProductoResponse> crear(@RequestBody ProductoRequest request) {
		ProductoResponse creado = productoService.crear(request);
		return ResponseEntity.ok(creado);
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<ProductoResponse> actualizar(@PathVariable Long id,@RequestBody ProductoRequest request) {
		return productoService.actualizar(id, request)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build()); 
	}

	@Override
	public ResponseEntity<Void> eliminar(Long id) {
		boolean eliminado = productoService.eliminar(id);
		return eliminado ? ResponseEntity.noContent().build()
					     : ResponseEntity.notFound().build();
	}

}
