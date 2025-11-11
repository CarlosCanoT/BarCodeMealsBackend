package com.tfg.barcodemeals.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reaccionAdversa")
public class ReaccionAdversa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	 @Enumerated(EnumType.STRING)
	 private NivelRiesgo nivelRiesgo;
	 @Enumerated(EnumType.STRING)
	 private TipoReaccion tipo;
	 private String descripcion;
	
	 @ManyToMany(mappedBy = "reaccionesAdversas")
	 private List<Usuario> usuarios = new ArrayList<>();
	 
	 @ManyToMany(mappedBy = "reacciones")
	 private List<Producto> productos = new ArrayList<>();
}