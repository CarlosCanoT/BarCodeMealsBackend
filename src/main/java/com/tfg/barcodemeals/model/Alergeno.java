package com.tfg.barcodemeals.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alergeno")
public class Alergeno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	   @ManyToMany(mappedBy = "alergenos")
	    private List<Usuario> usuarios = new ArrayList<>();

	    @ManyToMany(mappedBy = "alergenos")
	    private List<Producto> productos = new ArrayList<>();
}