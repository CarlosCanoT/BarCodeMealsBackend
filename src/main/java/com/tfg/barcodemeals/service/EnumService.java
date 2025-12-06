package com.tfg.barcodemeals.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.response.EnumResponse;
import com.tfg.barcodemeals.mapper.EnumMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class EnumService {

	private final EnumMapper enumMapper;
	
	  public List<EnumResponse> getGeneros() {
	        return enumMapper.mapGeneros();
	    }

	    public List<EnumResponse> getEnvases() {
	        return enumMapper.mapEnvases();
	    }

	    public List<EnumResponse> getCategoriasProducto() {
	        return enumMapper.mapCategoriaProducto();
	    }

	    public List<EnumResponse> getTipoComida() {
	        return enumMapper.mapTipoComida();
	    }

	    public List<EnumResponse> getUnidadMedida() {
	        return enumMapper.mapUnidadMedida();
	    }
    
}
