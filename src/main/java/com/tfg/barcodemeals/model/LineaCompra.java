package com.tfg.barcodemeals.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lineaCompra")
public class LineaCompra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column(nullable = false)
	private int cantidad;
    @Column(nullable = false)
    private boolean comprado;
    private double precioLinea;
    @ManyToOne
    private ListaCompra listaCompra;

    @ManyToOne
    private Producto producto;
}