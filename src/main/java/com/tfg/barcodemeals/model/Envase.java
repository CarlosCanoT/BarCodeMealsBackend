package com.tfg.barcodemeals.model;

public enum Envase {
	PLÁSTICO, CARTÓN, VIDRIO, ORGÁNICO;
	
	 @Override
	    public String toString() {
	        return name().toLowerCase();
	    }
}
