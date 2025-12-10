package com.tfg.barcodemeals.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.request.ProductoFraccionRequest;
import com.tfg.barcodemeals.dto.request.ProductoRequest;
import com.tfg.barcodemeals.dto.response.ProductoResponse;
import com.tfg.barcodemeals.mapper.ProductoMapper;
import com.tfg.barcodemeals.model.CategoriaProducto;
import com.tfg.barcodemeals.model.Envase;
import com.tfg.barcodemeals.model.Producto;
import com.tfg.barcodemeals.model.UnidadMedida;
import com.tfg.barcodemeals.repository.ProductoRepository;
import com.tfg.barcodemeals.repository.ReaccionAdversaRepository;
import com.tfg.barcodemeals.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService implements CrudService<ProductoResponse, ProductoRequest> {

	private final ProductoRepository productoRepository;
	private final ReaccionAdversaRepository reaccionAdversaRepository;
	private final UsuarioRepository usuarioRepository;
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
	
	public List<ProductoResponse> obtenerProductosVisibles(Long usuarioId) {
        return productoRepository.findVisibleForUser(usuarioId)
                .stream()
                .map(productoMapper::toResponse)
                .toList();
    }
	
	public List<ProductoResponse> obtenerMisFracciones(Long usuarioId) {
        return productoRepository.findByUsuarioIdAndEsFraccionTrue(usuarioId)
                .stream()
                .map(productoMapper::toResponse)
                .toList();
    }
	
	 public List<ProductoResponse> obtenerMisProductos(Long usuarioId) {
	        return productoRepository.findByUsuarioIdAndEsFraccionFalse(usuarioId)
	                .stream()
	                .map(productoMapper::toResponse)
	                .toList();
	    }
	 
	 public Optional<ProductoResponse> obtenerPorBarcode(String barcode) {
		 return productoRepository.findByBarcode(barcode)
				 .map(productoMapper::toResponse);
	 }
	
	@Override
	public ProductoResponse crear(ProductoRequest request){
		Producto producto = new Producto();
		String barcode = request.barcode();
		if (barcode != null && barcode.trim().isEmpty()) {
		    barcode = null;
		}
		
		producto.setBarcode(barcode);
		producto.setNombre(request.nombre());
		producto.setMarca(request.marca());
		producto.setEsFraccion(false);
		producto.setEsPublico(request.esPublico());
		producto.setCategoria(CategoriaProducto.valueOf(request.categoria()));
		producto.setPesoEmpaque(request.pesoEmpaque());
		producto.setPesoConsumido(100);
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
		
	    producto.setUsuario(request.usuarioId() != null ? usuarioRepository.findById(request.usuarioId()).orElse(null) : null);

			
		return productoMapper.toResponse(productoRepository.save(producto));
	}
	
	public ProductoResponse crearProductoFraccion(ProductoFraccionRequest productoFraccionRequest) {
		Producto original = productoRepository.findById(productoFraccionRequest.productoOriginalId())
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));
		
		Producto fraccion = new Producto();
	    fraccion.setBarcode(null);
	    fraccion.setNombre(original.getNombre() + " [ " + productoFraccionRequest.pesoConsumido() + " " + original.getUnidad().getTexto() + " ]");
	    fraccion.setMarca(original.getMarca());
	    fraccion.setEsFraccion(true);
	    fraccion.setEsPublico(false);
	    fraccion.setCategoria(original.getCategoria());
	    fraccion.setPesoEmpaque(original.getPesoEmpaque());
	    fraccion.setPesoConsumido(productoFraccionRequest.pesoConsumido());
	    fraccion.setKcal(original.getKcal());
	    fraccion.setGrasa(original.getGrasa());
	    fraccion.setSaturada(original.getSaturada());
	    fraccion.setNoSaturada(original.getNoSaturada());
	    fraccion.setHidratosCarbono(original.getHidratosCarbono());
	    fraccion.setAzucar(original.getAzucar());
	    fraccion.setProteina(original.getProteina());
	    fraccion.setSal(original.getSal());
	    fraccion.setFibra(original.getFibra());
	    fraccion.setUnidad(original.getUnidad());
	    fraccion.setEnvase(original.getEnvase());
	    fraccion.setUsuario(usuarioRepository.findById(productoFraccionRequest.usuarioId())
	    	    .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
	    if (original.getReaccionesAdversas() != null) {
	        fraccion.setReaccionesAdversas(new ArrayList<>(original.getReaccionesAdversas()));
	    }	    
	    return productoMapper.toResponse(productoRepository.save(fraccion));
	}
	
	@Override
	public Optional<ProductoResponse> actualizar(Long id, ProductoRequest request) {
		return productoRepository.findById(id)
				.filter(p -> p.getUsuario() != null && p.getUsuario().getId().equals(request.usuarioId()))
				.map(producto -> {
					producto.setBarcode(request.barcode());
					producto.setNombre(request.nombre());
					producto.setMarca(request.marca());
					producto.setEsPublico(request.esPublico());
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
