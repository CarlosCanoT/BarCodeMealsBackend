package com.tfg.barcodemeals.model;

public enum TipoComida {
	DESAYUNO("Desayuno"),
	COMIDA("Comida"),
	MERIENDA("Merienda"),
	CENA("Cena"),
	SNACK("Snack");
	
	private final String texto;
	
	TipoComida(String texto){
		this.texto = texto;
	}
	
	public String getTexto() {
        return texto;
    }
}
