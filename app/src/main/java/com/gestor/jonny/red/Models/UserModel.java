package com.gestor.jonny.red.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class UserModel implements Serializable {
    private long userId;
    private String nombre = "";
    private String apellidos = "";
    private int edad = 0;
    private long fecha = 0;
    private String correo = "";
    private String direccion = "";
    private long rol;
    private String nombreArtista = "";
    private ArrayList<Long> instrumentos = new ArrayList<>();
    private ArrayList<Long> estilos = new ArrayList<>();
    private String paginaWeb = "";
    private double precio = 0.0;
    //private ArrayList<RecorridoModel> recorrido = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public long getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /*public ArrayList<RecorridoModel> getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(ArrayList<RecorridoModel> recorrido) {
        this.recorrido = recorrido;
    }*/

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public ArrayList<Long> getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(ArrayList<Long> instrumentos) {
        this.instrumentos = instrumentos;
    }

    public ArrayList<Long> getEstilos() {
        return estilos;
    }

    public void setEstilos(ArrayList<Long> estilos) {
        this.estilos = estilos;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public long getRol() {
        return rol;
    }

    public void setRol(long rol) {
        this.rol = rol;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
