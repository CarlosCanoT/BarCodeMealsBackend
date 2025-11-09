package com.tfg.barcodemeals.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "listaCompra")
public class ListaCompra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@Column(nullable = false)
	private LocalDate fechaCreacion;
	
	@ManyToOne
	private Usuario usuario;

	@OneToMany(mappedBy = "listaCompra", cascade = CascadeType.ALL)
	private List<LineaCompra> lineas = new ArrayList<>();
}