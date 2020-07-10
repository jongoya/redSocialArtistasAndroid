package com.gestor.jonny.red.Models;

import java.io.Serializable;

public class InstrumentoModel implements Serializable {

    private String nombre = "";
    private long id = 0;

    public InstrumentoModel(String nombre, long id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
