package com.tfg.barcodemeals.model;

import java.time.LocalDate;
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
@Table(name = "registroDiario")
public class RegistroDiario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double kcalTotal;
	private double grasaTotal;
	private double saturadaTotal;
	private double noSaturadaTotal;
	private double proteinaTotal;
	private double hidratosCarbonoTotal; 
	private double azucarTotal; 
	private double salTotal; 
	private double fibraTotal; 
	private LocalDate fecha;
	@ManyToMany
	@JoinTable(
	    name = "registro_producto",
	    joinColumns = @JoinColumn(name = "registro_id"),
	    inverseJoinColumns = @JoinColumn(name = "producto_id")
	)
	private List<Producto> productos = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

}
