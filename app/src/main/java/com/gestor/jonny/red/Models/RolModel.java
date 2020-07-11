package com.gestor.jonny.red.Models;

import java.io.Serializable;

public class RolModel implements Serializable {
    private String nombre = "";
    private long rolId = 0;

    public RolModel(String nombre, long rolId) {
        this.nombre = nombre;
        this.rolId = rolId;
    }

    public RolModel() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return rolId;
    }

    public void setId(long id) {
        this.rolId = id;
    }
}
