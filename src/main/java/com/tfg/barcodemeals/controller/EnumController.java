package com.tfg.barcodemeals.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.barcodemeals.dto.response.EnumResponse;
import com.tfg.barcodemeals.service.EnumService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/enums")
@RequiredArgsConstructor
public class EnumController {

    private final EnumService enumService;

    @GetMapping("/generos")
    public List<EnumResponse> getGeneros() {
        return enumService.getGeneros();
    }

    @GetMapping("/envases")
    public List<EnumResponse> getEnvases() {
        return enumService.getEnvases();
    }

    @GetMapping("/categorias-producto")
    public List<EnumResponse> getCategoriasProducto() {
        return enumService.getCategoriasProducto();
    }

    @GetMapping("/tipo-comida")
    public List<EnumResponse> getTipoComida() {
        return enumService.getTipoComida();
    }

    @GetMapping("/unidad-medida")
    public List<EnumResponse> getUnidadMedida() {
        return enumService.getUnidadMedida();
    }
}
