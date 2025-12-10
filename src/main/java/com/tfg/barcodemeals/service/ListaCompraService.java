package com.tfg.barcodemeals.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.request.ListaCompraRequest;
import com.tfg.barcodemeals.dto.response.ListaCompraResponse;
import com.tfg.barcodemeals.mapper.ListaCompraMapper;
import com.tfg.barcodemeals.model.ListaCompra;
import com.tfg.barcodemeals.repository.ListaCompraRepository;
import com.tfg.barcodemeals.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListaCompraService implements CrudService<ListaCompraResponse, ListaCompraRequest>{
	private final ListaCompraRepository listaCompraRepository;
	private final ListaCompraMapper listaCompraMapper;
	private final UsuarioRepository usuarioRepository;
	
	@Override
	public Optional<ListaCompraResponse> obtenerPorId(Long id) {
		return listaCompraRepository.findById(id)
				.map(listaCompraMapper::toResponse);
	}
	
	@Override
	public List<ListaCompraResponse> obtenerTodos() {
		return listaCompraRepository.findAll()
				.stream()
				.map(listaCompraMapper::toResponse)
				.toList();		
	}
	
	public List<ListaCompraResponse> obtenerPorUsuario(Long usuarioId){
		return listaCompraRepository.findByUsuarioId(usuarioId)
				.stream()
				.map(listaCompraMapper::toResponse)
				.toList();
	}
	
	@Override
	public ListaCompraResponse crear(ListaCompraRequest request) {
		ListaCompra listaCompra = new ListaCompra();
		listaCompra.setNombre(request.nombre());
		listaCompra.setFechaCreacion(LocalDate.now());
		listaCompra.setLineas(List.of());
		listaCompra.setUsuario(usuarioRepository.findById(request.usuarioId())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
		return listaCompraMapper.toResponse(listaCompraRepository.save(listaCompra));
	}
	
	@Override
	public Optional<ListaCompraResponse> actualizar(Long id, ListaCompraRequest request) {
		return listaCompraRepository.findById(id)
		        .map(listaCompra -> {
		        listaCompra.setNombre(request.nombre());
		        return listaCompraMapper.toResponse(listaCompraRepository.save(listaCompra));
		        });
		
	}
	
	@Override
	public boolean eliminar(Long id) {
		return listaCompraRepository.findById(id)
				.map(l -> {
				listaCompraRepository.delete(l);
				return true;
				}).orElse(false);
	}
}
