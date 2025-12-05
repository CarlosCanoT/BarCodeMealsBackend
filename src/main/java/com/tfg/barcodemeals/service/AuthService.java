package com.tfg.barcodemeals.service;

import java.util.Date;
import java.security.Key;

import org.springframework.stereotype.Service;

import com.tfg.barcodemeals.dto.request.LoginRequest;
import com.tfg.barcodemeals.dto.response.LoginResponse;
import com.tfg.barcodemeals.model.Usuario;
import com.tfg.barcodemeals.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    private final String SECRET_KEY = "pruebaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(request.nombreUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getContrasena().equals(request.contrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = Jwts.builder()
                .setSubject(usuario.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) 
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

        return new LoginResponse(token, usuario.getId(), usuario.getNombreUsuario());
    }

}
