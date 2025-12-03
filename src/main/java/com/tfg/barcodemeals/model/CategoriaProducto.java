package com.tfg.barcodemeals.model;

public enum CategoriaProducto {
    // Básicos y Proteínas
    LACTEOS, 
    CARNE, 
    PESCADO,
    HUEVOS,
    LEGUMBRES, 
    FRUTOS_SECOS_Y_SEMILLAS,

    // Vegetales y Frutas
    VERDURAS_Y_HORTALIZAS,
    FRUTAS,
    
    // Cereales y Derivados
    CEREALES_Y_GRANOS,
    PAN_Y_BOLLERIA,
    PASTA,
    
    // Grasas y Aceites
    ACEITES_Y_GRASAS,
    
    // Preparados y Bebidas
    PLATOS_PREPARADOS,
    CONSERVAS,
    BEBIDAS_REFRESCANTES,
    AGUA,
    ALCOHOLICAS,
    
    // Varios
    DULCES_Y_AZUCARES,
    ESPECIAS_Y_CONDIMENTOS,
    OTROS;
	
	 @Override
	    public String toString() {
	        return name().replace('_', ' ').toLowerCase();
	    }
}
