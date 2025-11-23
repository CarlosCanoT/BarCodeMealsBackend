//package com.tfg.barcodemeals.mapper;
//
//import com.tfg.barcodemeals.dto.request.LoginRequest;
//import com.tfg.barcodemeals.dto.response.LoginResponse;
//import com.tfg.barcodemeals.model.Usuario;
//
//public class LoginMapper {
//	public LoginResponse login(LoginRequest request) {
//	    Usuario usuario = usuarioService.authenticate(request.nombreUsuario(), request.contrasena());
//	    String jwt = jwtService.generateToken(usuario);
//
//	    return new LoginResponse(
//	        jwt,
//	        usuario.getId(),
//	        usuario.getNombreUsuario(),
//	        usuario.getApodo()
//	    );
//	}
//
//}
