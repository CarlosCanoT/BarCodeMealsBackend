package com.tfg.barcodemeals.model;

public enum Envase {
	PLÁSTICO, CARTÓN, VIDRIO, ORGÁNICO, NINGUNO, OTRO;
	
	 @Override
	    public String toString() {
	        return name().toLowerCase();
	    }
}
