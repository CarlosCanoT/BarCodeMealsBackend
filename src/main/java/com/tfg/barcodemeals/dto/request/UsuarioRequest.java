package com.tfg.barcodemeals.dto.request;

import java.time.LocalDate;
import java.util.List;

public record UsuarioRequest(
        Long id,                        
        String nombreUsuario,
        String contrasena,               
        String apodo,
        String email,
        String telefono,
        LocalDate fechaNacimiento,
        String genero,                
        double peso,
        double altura,
        Long ciudadId,                  
        List<Long> reaccionesAdversasIds 
) {}
