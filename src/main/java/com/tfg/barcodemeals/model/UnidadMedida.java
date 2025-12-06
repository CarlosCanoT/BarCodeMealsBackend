package com.tfg.barcodemeals.model;


public enum UnidadMedida {
    GRAMOS("g"),
    MILILITROS("ml");

    private final String texto;

    UnidadMedida(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}

