package com.tfg.barcodemeals.model;


public enum UnidadMedida {
    GRAMOS("g"),
    MILILITROS("ml");

    private final String simbolo;

    UnidadMedida(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }
}

