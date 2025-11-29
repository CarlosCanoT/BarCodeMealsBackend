package com.tfg.barcodemeals.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.response.CiudadResponse;
import com.tfg.barcodemeals.mapper.CiudadMapper;
import com.tfg.barcodemeals.repository.CiudadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CiudadService {

    private final CiudadRepository ciudadRepository;
    private final CiudadMapper ciudadMapper;

    public Optional<CiudadResponse> obtenerPorId(Long id) {
        return ciudadRepository.findById(id)
                .map(ciudadMapper::toResponse);
    }
    
    public List<CiudadResponse> obtenerTodos() {
        return ciudadRepository.findAll()
                .stream()
                .map(ciudadMapper::toResponse)
                .toList();
    }

  
}

