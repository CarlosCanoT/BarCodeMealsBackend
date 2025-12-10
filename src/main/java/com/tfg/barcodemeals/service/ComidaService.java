package com.tfg.barcodemeals.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.request.ComidaRequest;
import com.tfg.barcodemeals.dto.response.ComidaResponse;
import com.tfg.barcodemeals.mapper.ComidaMapper;
import com.tfg.barcodemeals.model.Comida;
import com.tfg.barcodemeals.model.Plato;
import com.tfg.barcodemeals.model.Producto;
import com.tfg.barcodemeals.model.RegistroDiario;
import com.tfg.barcodemeals.model.TipoComida;
import com.tfg.barcodemeals.model.Usuario;
import com.tfg.barcodemeals.repository.ComidaRepository;
import com.tfg.barcodemeals.repository.PlatoRepository;
import com.tfg.barcodemeals.repository.ProductoRepository;
import com.tfg.barcodemeals.repository.RegistroDiarioRepository;
import com.tfg.barcodemeals.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComidaService {

	private final ComidaRepository comidaRepository;
    private final ProductoRepository productoRepository;
    private final PlatoRepository platoRepository;
    private final RegistroDiarioRepository registroDiarioRepository;
    private final UsuarioRepository usuarioRepository;
	private final ComidaMapper comidaMapper;

	
	public Optional<ComidaResponse> obtenerPorId(Long id) {
		return comidaRepository.findById(id)
				.map(comidaMapper::toResponse);
	}

	public List<ComidaResponse> obtenerTodos() {
		return comidaRepository.findAll()
				.stream()
				.map(comidaMapper::toResponse)
				.toList();
	}

	@Transactional
	public ComidaResponse crear(ComidaRequest request, Long usuarioId) {
	    Usuario usuario = usuarioRepository.findById(usuarioId)
	            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

	    RegistroDiario registroDiario = registroDiarioRepository
	            .findByUsuarioAndFecha(usuario, request.fecha())
	            .orElseGet(() -> {
	                RegistroDiario nuevo = new RegistroDiario();
	                nuevo.setUsuario(usuario);
	                nuevo.setFecha(request.fecha());
	                nuevo.setComidas(new ArrayList<>());
	                return registroDiarioRepository.save(nuevo);
	            });

	    Comida comida = new Comida();
	    comida.setNombre(request.nombre());
	    comida.setTipo(TipoComida.valueOf(request.tipo()));
	    comida.setFecha(request.fecha());
	    comida.setRegistroDiario(registroDiario);

	    // 🔴 DEPURACIÓN: Ver qué IDs llegan
	    System.out.println("ProductoIds recibidos: " + request.productoIds());
	    System.out.println("PlatoIds recibidos: " + request.platoIds());

	    // Cargar productos
	    if (request.productoIds() != null && !request.productoIds().isEmpty()) {
	        List<Producto> productos = productoRepository.findAllById(request.productoIds());
	        System.out.println("Productos encontrados: " + productos.size());
	        comida.setProductos(productos);
	    } else {
	        comida.setProductos(new ArrayList<>());
	    }

	    // Cargar platos
	    if (request.platoIds() != null && !request.platoIds().isEmpty()) {
	        List<Plato> platos = platoRepository.findAllById(request.platoIds());
	        System.out.println("Platos encontrados: " + platos.size());
	        comida.setPlatos(platos);
	    } else {
	        comida.setPlatos(new ArrayList<>());
	    }

	    // Mostrar en consola ANTES de guardar
	    System.out.println("Productos a añadir: " + comida.getProductos().size());
	    comida.getProductos().forEach(p -> System.out.println("  - " + p.getId() + " " + p.getNombre()));
	    System.out.println("Platos a añadir: " + comida.getPlatos().size());
	    comida.getPlatos().forEach(p -> System.out.println("  - " + p.getId() + " " + p.getNombre()));

	    // Guardar comida
	    Comida comidaGuardada = comidaRepository.save(comida);

	    // Asociar comida al registro diario
	    registroDiario.getComidas().add(comidaGuardada);
	    registroDiario.recalcularTotales();
	    registroDiarioRepository.save(registroDiario);

	    return comidaMapper.toResponse(comidaGuardada);
	}


	
	public Optional<ComidaResponse> actualizar(Long id, ComidaRequest request) {
		return comidaRepository.findById(id)
				.map(comida -> {
					comida.setTipo(TipoComida.valueOf(request.tipo()));
					comida.setFecha(request.fecha());
					comida.setPlatos(platoRepository.findAllById(request.platoIds()));
					comida.setProductos(productoRepository.findAllById(request.productoIds()));
				    return comidaMapper.toResponse(comidaRepository.save(comida));
				});
	}

	public boolean eliminar(Long id) {
		return comidaRepository.findById(id)
				.map(c -> {
					comidaRepository.delete(c);
					return true;
				}).orElse(false);
	}

}
