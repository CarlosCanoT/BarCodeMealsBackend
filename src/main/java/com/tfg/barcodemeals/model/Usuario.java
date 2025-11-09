package com.tfg.barcodemeals.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
    private String nombreUsuario; 
    @Column(nullable = false)
    private String contrasena;
    private String apodo;
    private String email;
    private int edad;
    private int telefono;
    private double peso;
    private double altura;
    
    @ManyToOne
    private Ciudad ciudad;
    
    @ManyToMany
    @JoinTable(
        name = "usuario_alergeno",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "alergeno_id")
    )
    private List<Alergeno> alergenos = new ArrayList<>();



}