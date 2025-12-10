package com.tfg.barcodemeals.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.barcodemeals.dto.request.LineaCompraRequest;
import com.tfg.barcodemeals.dto.response.LineaCompraResponse;
import com.tfg.barcodemeals.service.LineaCompraService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lineasCompra")
@RequiredArgsConstructor
public class LineaCompraController implements CrudController<LineaCompraResponse, LineaCompraRequest>{
	
	private final LineaCompraService lineaCompraService;

   
	
	@Override
	@GetMapping
	public List<LineaCompraResponse> obtenerTodos() {
		return lineaCompraService.obtenerTodos();
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<LineaCompraResponse> obtenerPorId(@PathVariable Long id) {
		return lineaCompraService.obtenerPorId(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/listaCompra/{listaCompraId}")
	public ResponseEntity<List<LineaCompraResponse>> obtenerPorListaCompra(@PathVariable Long listaCompraId){
		return Optional.ofNullable(lineaCompraService.obtenerPorListaCompra(listaCompraId))
						.filter(list -> !list.isEmpty())
						.map(ResponseEntity::ok)
						.orElseGet(() -> ResponseEntity.noContent().build());
	}
	
	@Override
	@PostMapping
	public ResponseEntity<LineaCompraResponse> crear(@RequestBody LineaCompraRequest request) {
		return ResponseEntity.ok(lineaCompraService.crear(request));
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<LineaCompraResponse> actualizar(@PathVariable Long id, @RequestBody LineaCompraRequest request) {
		return lineaCompraService.actualizar(id, request)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		return lineaCompraService.eliminar(id) ? ResponseEntity.noContent().build()
			     : ResponseEntity.notFound().build();
	}

}
