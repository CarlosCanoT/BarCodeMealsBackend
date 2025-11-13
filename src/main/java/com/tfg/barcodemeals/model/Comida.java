package com.tfg.barcodemeals.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "comida")
public class Comida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Enumerated(EnumType.STRING)
	 private TipoComida tipo;
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
    private RegistroDiario registroDiario;
    
    @ManyToMany
    @JoinTable(
        name = "comida_plato",
        joinColumns = @JoinColumn(name = "comida_id"),
        inverseJoinColumns = @JoinColumn(name = "plato_id")
    )
    private List<Plato> platos = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
        name = "comida_producto",
        joinColumns = @JoinColumn(name = "comida_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos = new ArrayList<>();

}
