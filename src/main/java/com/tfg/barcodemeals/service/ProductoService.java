package com.tfg.barcodemeals.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.request.ProductoRequest;
import com.tfg.barcodemeals.dto.response.ProductoResponse;
import com.tfg.barcodemeals.mapper.ProductoMapper;
import com.tfg.barcodemeals.model.CategoriaProducto;
import com.tfg.barcodemeals.model.Envase;
import com.tfg.barcodemeals.model.Producto;
import com.tfg.barcodemeals.model.UnidadMedida;
import com.tfg.barcodemeals.repository.ProductoRepository;
import com.tfg.barcodemeals.repository.ReaccionAdversaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService implements CrudService<ProductoResponse,ProductoRequest>{

	private final ProductoRepository productoRepository;
	private final ReaccionAdversaRepository reaccionAdversaRepository;
	private final ProductoMapper productoMapper;

	@Override
	public Optional<ProductoResponse> obtenerPorId(Long id){
		return productoRepository.findById(id)
				.map(productoMapper::toResponse);
	}
	@Override
	public List<ProductoResponse> obtenerTodos(){
		return productoRepository.findAll()
				.stream()
				.map(productoMapper::toResponse)
				.toList();
	}
	
	@Override
	public ProductoResponse crear(ProductoRequest request){
		Producto producto = new Producto();
		producto.setBarcode(request.barcode());
		producto.setNombre(request.nombre());
		producto.setMarca(request.marca());
		producto.setCategoria(CategoriaProducto.valueOf(request.categoria()));
		producto.setPesoEmpaque(request.pesoEmpaque());
		producto.setPesoConsumido(request.pesoConsumido());
		producto.setKcal(request.kcal());
		producto.setGrasa(request.grasa());
		producto.setSaturada(request.saturada());
		producto.setNoSaturada(request.noSaturada());
		producto.setHidratosCarbono(request.hidratosCarbono());
		producto.setAzucar(request.azucar());
		producto.setProteina(request.proteina());
		producto.setSal(request.sal());
		producto.setFibra(request.fibra());
		producto.setUnidad(UnidadMedida.valueOf(request.unidad()));
		producto.setEnvase(Envase.valueOf(request.envase()));
		
		if(request.reaccionesAdversasIds() != null && !request.reaccionesAdversasIds().isEmpty()) {
	       request.reaccionesAdversasIds().forEach(id -> {
	    	   reaccionAdversaRepository.findById(id).ifPresent(producto.getReaccionesAdversas()::add);
	       });
	    }
		return productoMapper.toResponse(productoRepository.save(producto));
	}
	
	
	@Override
	public Optional<ProductoResponse> actualizar(Long id, ProductoRequest request) {
		return productoRepository.findById(id)
				.map(producto -> {
					producto.setBarcode(request.barcode());
					producto.setNombre(request.nombre());
					producto.setMarca(request.marca());
					producto.setCategoria(CategoriaProducto.valueOf(request.categoria()));
					producto.setPesoEmpaque(request.pesoEmpaque());
					producto.setPesoConsumido(request.pesoConsumido());
					producto.setKcal(request.kcal());
					producto.setGrasa(request.grasa());
					producto.setSaturada(request.saturada());
					producto.setNoSaturada(request.noSaturada());
					producto.setHidratosCarbono(request.hidratosCarbono());
					producto.setAzucar(request.azucar());
					producto.setProteina(request.proteina());
					producto.setSal(request.sal());
					producto.setFibra(request.fibra());
					producto.setUnidad(UnidadMedida.valueOf(request.unidad()));
					producto.setEnvase(Envase.valueOf(request.envase()));
					producto.setReaccionesAdversas(reaccionAdversaRepository.findAllById(request.reaccionesAdversasIds()));
		            return productoMapper.toResponse(productoRepository.save(producto));
				});
	}
	@Override
	public boolean eliminar(Long id){
		return productoRepository.findById(id)
				.map(p -> {
				productoRepository.delete(p);
				return true;
				}).orElse(false);
	}
	
}
