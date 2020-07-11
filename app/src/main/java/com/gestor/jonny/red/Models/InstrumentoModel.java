package com.gestor.jonny.red.Models;

import java.io.Serializable;

public class InstrumentoModel implements Serializable {

    private String nombre = "";
    private long instrumentoId = 0;

    public InstrumentoModel(String nombre, long instrumentoId) {
        this.nombre = nombre;
        this.instrumentoId = instrumentoId;
    }

    public InstrumentoModel() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return instrumentoId;
    }

    public void setId(long instrumentoId) {
        this.instrumentoId = instrumentoId;
    }
}
