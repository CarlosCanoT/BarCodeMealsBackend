package com.tfg.barcodemeals.dto.response;

public record LoginResponse(
    String token,         
    UsuarioResponse usuario
) {}
