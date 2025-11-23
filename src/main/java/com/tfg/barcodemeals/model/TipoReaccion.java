package com.tfg.barcodemeals.model;

public enum TipoReaccion {
    // Intolerancias
    INTOLERANCIA_LACTOSA,
    INTOLERANCIA_FRUCTOSA,
    INTOLERANCIA_GLUCOSA,
    INTOLERANCIA_HUEVO,
    INTOLERANCIA_SOJA,
    INTOLERANCIA_TRIGO,
    
    // Alergias comunes
    ALERGIA_PESCADO,
    ALERGIA_MARISCOS,
    ALERGIA_FRUTOS_SECOS,
    ALERGIA_MANDARINA,
    ALERGIA_HUEVO,
    ALERGIA_SOJA,
    ALERGIA_LECHE,
    ALERGIA_TRIGO,
    ALERGIA_SULFITOS,
    ALERGIA_MOSTAZA,
    ALERGIA_SESAMO,
    ALERGIA_APIO,
    ALERGIA_PESCADO_AHUMADO,
    
    // Alergias raras / otros
    ALERGIA_CHOCOLATE,
    ALERGIA_CAFE,
    ALERGIA_FRESA,
    ALERGIA_MANZANA,
    ALERGIA_TOMATE,
    ALERGIA_CACAO
    ;
    
    @Override
    public String toString() {
        // Convierte ENUM a algo más “legible”
        return name().replace('_', ' ').toLowerCase();
    }
}
