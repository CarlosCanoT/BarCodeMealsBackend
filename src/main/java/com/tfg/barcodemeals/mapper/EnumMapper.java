package com.tfg.barcodemeals.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tfg.barcodemeals.dto.response.EnumResponse;
import com.tfg.barcodemeals.model.CategoriaProducto;
import com.tfg.barcodemeals.model.Envase;
import com.tfg.barcodemeals.model.Genero;
import com.tfg.barcodemeals.model.TipoComida;
import com.tfg.barcodemeals.model.UnidadMedida;

@Component
public class EnumMapper {

	public List<EnumResponse> mapGeneros(){
		return Arrays.stream(Genero.values())
				.map(g -> new EnumResponse(
						g.name(),
						g.getTexto()
						))
				.collect(Collectors.toList());
	}
	
	public List<EnumResponse> mapEnvases(){
		return Arrays.stream(Envase.values())
				.map(g -> new EnumResponse(
						g.name(),
						g.getTexto()
						))
				.collect(Collectors.toList());
	}
	
	public List<EnumResponse> mapCategoriaProducto(){
		return Arrays.stream(CategoriaProducto.values())
				.map(g -> new EnumResponse(
						g.name(),
						g.getTexto()
						))
				.collect(Collectors.toList());
	}
	
	public List<EnumResponse> mapTipoComida(){
		return Arrays.stream(TipoComida.values())
				.map(g -> new EnumResponse(
						g.name(),
						g.getTexto()
						))
				.collect(Collectors.toList());
	}
	
	public List<EnumResponse> mapUnidadMedida(){
		return Arrays.stream(UnidadMedida.values())
				.map(g -> new EnumResponse(
						g.name(),
						g.getTexto()
						))
				.collect(Collectors.toList());
	}
	
}
