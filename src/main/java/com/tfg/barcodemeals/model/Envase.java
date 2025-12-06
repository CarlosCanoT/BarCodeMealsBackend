package com.tfg.barcodemeals.model;

public enum Envase {
	
	PLÁSTICO("Plástico"),
	CARTÓN("Cartón"),
	VIDRIO("Vidrio"),
	ORGÁNICO("Orgánico"),
	NINGUNO("Ninguno"),
	OTRO("Otro");
	
	private final String texto;
	
	Envase(String texto){
		this.texto = texto;
		
	}
	
	public String getTexto() {
        return texto;
	}
}
