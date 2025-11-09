package com.tfg.barcodemeals.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String barcode;
@Column(nullable = false)
private double peso;
private double kcal;
@Column(nullable = false)
private double grasa;
private double saturada;
private double noSaturada;
@Column(nullable = false)
private double proteina;
@Column(nullable = false)
private double hidratosCarbono; 
private double azucar; 
private double sal; 
private double fibra; 

@Enumerated(EnumType.STRING)
private UnidadMedida unidad;

@ManyToMany
@JoinTable(
    name = "producto_alergeno",
    joinColumns = @JoinColumn(name = "producto_id"),
    inverseJoinColumns = @JoinColumn(name = "alergeno_id")
)
private List<Alergeno> alergenos = new ArrayList<>();


@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
private List<Precio> precios = new ArrayList<>();

}
