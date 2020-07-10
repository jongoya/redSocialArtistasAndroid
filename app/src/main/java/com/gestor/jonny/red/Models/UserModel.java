package com.gestor.jonny.red.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class UserModel implements Serializable {
    private String nombre = "";
    private String apellidos = "";
    private int edad = 0;
    private long fecha = 0;
    private String correo = "";
    private String pais = "";
    private String direccion = "";
    private RolModel rol;
    private String nombreArtista = "";
    private ArrayList<InstrumentoModel> instrumentos = new ArrayList<>();
    private ArrayList<EstiloModel> estilos = new ArrayList<>();
    private String paginaWeb = "";
    private double precio = 0.0;
    private String usuario = "";
    private String password = "";
    private String clave = "";
    private ArrayList<RecorridoModel> recorrido = new ArrayList<>();

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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public ArrayList<RecorridoModel> getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(ArrayList<RecorridoModel> recorrido) {
        this.recorrido = recorrido;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public ArrayList<InstrumentoModel> getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(ArrayList<InstrumentoModel> instrumentos) {
        this.instrumentos = instrumentos;
    }

    public ArrayList<EstiloModel> getEstilos() {
        return estilos;
    }

    public void setEstilos(ArrayList<EstiloModel> estilos) {
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

    public RolModel getRol() {
        return rol;
    }

    public void setRol(RolModel rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
