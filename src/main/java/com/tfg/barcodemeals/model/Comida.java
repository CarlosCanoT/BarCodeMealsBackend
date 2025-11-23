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
public class Comida implements ValorNutricional{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Enumerated(EnumType.STRING)
    private TipoComida tipo; 
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
    
    @Override
    public double getPeso() {
        double pesoPlatos = platos.stream().mapToDouble(ValorNutricional::getPeso).sum();
        double pesoProductos = productos.stream().mapToDouble(ValorNutricional::getPeso).sum();
        return pesoPlatos + pesoProductos;
    }
    @Override
    public double getKcal() {
        return platos.stream().mapToDouble(ValorNutricional::getKcal).sum() +
               productos.stream().mapToDouble(ValorNutricional::getKcal).sum();
    }
    @Override
    public double getGrasa() {
        return platos.stream().mapToDouble(ValorNutricional::getGrasa).sum() +
               productos.stream().mapToDouble(ValorNutricional::getGrasa).sum();
    }
    @Override
    public double getSaturada() {
        return platos.stream().mapToDouble(ValorNutricional::getSaturada).sum() +
               productos.stream().mapToDouble(ValorNutricional::getSaturada).sum();
    }
    @Override
    public double getNoSaturada() {
        return platos.stream().mapToDouble(ValorNutricional::getNoSaturada).sum() +
               productos.stream().mapToDouble(ValorNutricional::getNoSaturada).sum();
    }
    @Override
    public double getHidratosCarbono() {
        return platos.stream().mapToDouble(ValorNutricional::getHidratosCarbono).sum() +
               productos.stream().mapToDouble(ValorNutricional::getHidratosCarbono).sum();
    }
    @Override
    public double getAzucar() {
        return platos.stream().mapToDouble(ValorNutricional::getAzucar).sum() +
               productos.stream().mapToDouble(ValorNutricional::getAzucar).sum();
    }
    @Override
    public double getProteina() {
    	return platos.stream().mapToDouble(ValorNutricional::getProteina).sum() +
    			productos.stream().mapToDouble(ValorNutricional::getProteina).sum();
    }
    @Override
    public double getSal() {
        return platos.stream().mapToDouble(ValorNutricional::getSal).sum() +
               productos.stream().mapToDouble(ValorNutricional::getSal).sum();
    }
    @Override
    public double getFibra() {
        return platos.stream().mapToDouble(ValorNutricional::getFibra).sum() +
               productos.stream().mapToDouble(ValorNutricional::getFibra).sum();
    }
}
