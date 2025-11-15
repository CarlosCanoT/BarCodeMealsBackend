package com.tfg.barcodemeals.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "objetivo_diario")
public class ObjetivoDiario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double kcal;
	private double proteina;
	private double hidratosCarbono;
	private double grasa;
	
	
    @OneToOne(mappedBy = "objetivoDiario") 
    private Usuario usuario;
}
