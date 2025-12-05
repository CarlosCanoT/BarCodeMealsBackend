package com.tfg.barcodemeals.dto.response;


public record LoginResponse(
    String token,         
    Long usuarioId,       
    String nombreUsuario
) {}
