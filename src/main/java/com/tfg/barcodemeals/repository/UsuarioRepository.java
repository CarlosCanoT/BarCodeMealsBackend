package com.tfg.barcodemeals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.barcodemeals.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
}
