package com.tfg.barcodemeals.model;

public enum CategoriaProducto {
    // Básicos y Proteínas
    LACTEOS("Lácteos"), 
    CARNE("Carne"), 
    PESCADO("Pescado"),
    HUEVOS("Huevos"),
    LEGUMBRES("Legumbres"), 
    FRUTOS_SECOS_Y_SEMILLAS("Futros secos y semillas"),

    // Vegetales y Frutas
    VERDURAS_Y_HORTALIZAS("Verduras y hortalizas"),
    FRUTAS("Frutas"),
    
    // Cereales y Derivados
    CEREALES_Y_GRANOS("Cereales y granos"),
    PAN("Pan"),
    BOLLERIA("Bollería"),
    PASTA("Pasta"),
    
    // Grasas y Aceites
    ACEITES_Y_GRASAS("Aceites y grasas"),
    
    // Preparados y Bebidas
    PRECOCINADOS("Precocinados"),
    CONSERVAS("Conservas"),
    REFRESCOS("Refrescos"),
    AGUA("Agua"),
    ALCOHOLICAS("Alcoholicas"),
    
    // Varios
    DULCES_Y_AZUCARES("Dulces y azúcares"),
    ESPECIAS_Y_CONDIMENTOS("Especias y condimentos"),
    OTROS("Otros");
	
	private final String texto;
	
	CategoriaProducto(String texto) {
		this.texto = texto;
	}
	
	public String getTexto() {
        return texto;
    }
}
