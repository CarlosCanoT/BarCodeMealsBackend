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

import com.tfg.barcodemeals.dto.request.ListaCompraRequest;
import com.tfg.barcodemeals.dto.response.ListaCompraResponse;
import com.tfg.barcodemeals.service.ListaCompraService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/listasCompra")
@RequiredArgsConstructor
public class ListaCompraController implements CrudController<ListaCompraResponse, ListaCompraRequest>{
	
	private final ListaCompraService listaCompraService;
	
	@Override
	@GetMapping
	public List<ListaCompraResponse> obtenerTodos() {
		return listaCompraService.obtenerTodos();
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ListaCompraResponse> obtenerPorId(@PathVariable Long id) {
		return listaCompraService.obtenerPorId(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	@PostMapping
	public ResponseEntity<ListaCompraResponse> crear(@RequestBody ListaCompraRequest request) {
		return ResponseEntity.ok(listaCompraService.crear(request));
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<ListaCompraResponse> actualizar(@PathVariable Long id, @RequestBody ListaCompraRequest request) {
		return listaCompraService.actualizar(id, request)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		return listaCompraService.eliminar(id) ? ResponseEntity.noContent().build()
			     : ResponseEntity.notFound().build();
	}

	
	
}
