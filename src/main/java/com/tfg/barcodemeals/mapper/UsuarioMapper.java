package com.tfg.barcodemeals.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tfg.barcodemeals.dto.request.UsuarioRequest;
import com.tfg.barcodemeals.dto.response.EnumResponse;
import com.tfg.barcodemeals.dto.response.UsuarioResponse;
import com.tfg.barcodemeals.model.Usuario;

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
                usuario.calcularEdad(),
                new EnumResponse(usuario.getGenero().name(), usuario.getGenero().getTexto()),
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
