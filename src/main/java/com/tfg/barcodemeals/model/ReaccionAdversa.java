package com.tfg.barcodemeals.model;

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
@Table(name = "reaccion_adversa")
public class ReaccionAdversa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	 private String descripcion;
	 @ManyToMany(mappedBy = "reaccionesAdversas")
	 private List<Usuario> usuarios;
	 
	 @ManyToMany(mappedBy = "reaccionesAdversas")
	 private List<Producto> productos;
}