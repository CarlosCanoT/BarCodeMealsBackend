package com.tfg.barcodemeals.mapper;

import com.tfg.barcodemeals.dto.request.UsuarioRequest;
import com.tfg.barcodemeals.dto.response.ReaccionAdversaResponse;
import com.tfg.barcodemeals.dto.response.UsuarioResponse;
import com.tfg.barcodemeals.model.Usuario;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioResponse toResponse(Usuario usuario) {
        if (usuario == null) return null;
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombreUsuario(),
                usuario.getApodo(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getFechaNacimiento(),
                usuario.getEdad(),
                usuario.getGenero().toString(),
                usuario.getPeso(),
                usuario.getAltura(),
                usuario.getCiudad().getId(),
                Optional.ofNullable(usuario.getReaccionesAdversas())
                		.orElse(List.of())
                		.stream()
                		.map(r -> r.getId())
                		.collect(Collectors.toList())
        );
    }
    public UsuarioRequest toRequest(Usuario usuario) {
    	  if (usuario == null) return null;
    	  return new UsuarioRequest(
    			  usuario.getId(),
    			  usuario.getNombreUsuario(),
    			  usuario.getContrasena(),
    			  usuario.getApodo(),
    			  usuario.getEmail(),
    			  usuario.getTelefono(),
    			  usuario.getFechaNacimiento(),
    			  usuario.getGenero().toString(),
    			  usuario.getPeso(),
    			  usuario.getAltura(),
    			  usuario.getCiudad().getId(),
    			  Optional.ofNullable(usuario.getReaccionesAdversas())
          				  .orElse(List.of())
          				  .stream()
          				  .map(r -> r.getId())
          				  .collect(Collectors.toList())
    			  );
    }

    
}
