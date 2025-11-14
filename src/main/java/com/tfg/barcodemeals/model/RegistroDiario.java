package com.tfg.barcodemeals.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name = "registro_diario")
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
	
	@ManyToOne
	private Usuario usuario;
	
	  @OneToMany(mappedBy="registroDiario", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Comida> comidas = new ArrayList<>();

}
