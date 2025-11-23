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
public class Producto implements ValorNutricional{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(nullable = true, unique = false)
private String barcode;
@Column(nullable = false)
private String nombre;
private String marca;
@Enumerated(EnumType.STRING)
private CategoriaProducto categoria;
private double pesoEmpaque;
@Column(nullable = false)
private double pesoConsumido;
@Column(nullable = false)
private double kcal;
@Column(nullable = false)
private double grasa;
private double saturada;
private double noSaturada;
@Column(nullable = false)
private double hidratosCarbono; 
private double azucar; 
@Column(nullable = false)
private double proteina;
private double sal; 
private double fibra; 

@Enumerated(EnumType.STRING)
private UnidadMedida unidad;

@Enumerated(EnumType.STRING)
private Envase envase;

@ManyToMany
@JoinTable(
    name = "producto_reaccion_adversa",
    joinColumns = @JoinColumn(name = "producto_id"),
    inverseJoinColumns = @JoinColumn(name = "reaccion_adversa_id")
)

private List<ReaccionAdversa> reaccionesAdversas = new ArrayList<>();

@ManyToMany(mappedBy="productos")
private List<Plato> platos = new ArrayList<>();

@ManyToMany(mappedBy="productos")
private List<Comida> comidas = new ArrayList<>();

@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
private List<Precio> precios = new ArrayList<>();


private double indice() {
    return pesoConsumido / 100.0; // los valores son por cada 100g
}
@Override
public double getPeso() { return pesoConsumido; }
@Override
public double getKcal() { return kcal * indice(); }
@Override
public double getGrasa() { return grasa * indice();}
@Override
public double getSaturada() { return saturada * indice(); }
@Override
public double getNoSaturada() { return noSaturada * indice(); }
@Override
public double getHidratosCarbono() { return hidratosCarbono * indice();}
@Override
public double getAzucar() { return azucar * indice();}
@Override
public double getProteina() { return proteina * indice();}
@Override
public double getSal() { return sal * indice(); }
@Override
public double getFibra() { return fibra * indice();}
}
