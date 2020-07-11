package com.gestor.jonny.red.Models;

import java.io.Serializable;

public class EstiloModel implements Serializable {

    private String nombre = "";
    private long estiloId = 0;

    public EstiloModel(String nombre, long estiloId) {
        this.nombre = nombre;
        this.estiloId = estiloId;
    }

    public EstiloModel() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return estiloId;
    }

    public void setId(long id) {
        this.estiloId = id;
    }
}
