package com.tfg.barcodemeals.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plato")
public class Plato implements ValorNutricional{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String nombre;
    private String descripcion;
 
    @ManyToMany
    @JoinTable(
        name = "plato_producto",
        joinColumns = @JoinColumn(name = "plato_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos = new ArrayList<>();
    @Override
    public double getPeso() { return productos.stream().mapToDouble(Producto::getPeso).sum(); }
    @Override
    public double getKcal() { return productos.stream().mapToDouble(Producto::getKcal).sum(); }
    @Override
    public double getGrasa() { return productos.stream().mapToDouble(Producto::getGrasa).sum(); }
    @Override
    public double getSaturada() { return productos.stream().mapToDouble(Producto::getSaturada).sum(); }
    @Override
    public double getNoSaturada() { return productos.stream().mapToDouble(Producto::getNoSaturada).sum(); }
    @Override
    public double getHidratosCarbono() { return productos.stream().mapToDouble(Producto::getHidratosCarbono).sum(); }
    @Override
    public double getAzucar() { return productos.stream().mapToDouble(Producto::getAzucar).sum(); }
    @Override
    public double getProteina() { return productos.stream().mapToDouble(Producto::getProteina).sum(); }
    @Override
    public double getSal() { return productos.stream().mapToDouble(Producto::getSal).sum(); }
    @Override
    public double getFibra() { return productos.stream().mapToDouble(Producto::getFibra).sum(); }

}
