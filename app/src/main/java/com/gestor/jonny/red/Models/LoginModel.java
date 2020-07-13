package com.gestor.jonny.red.Models;

import java.io.Serializable;

public class LoginModel implements Serializable {
    private long loginId = 0;
    private String usuario = "";
    private String password = "";
    private String clave = "";
    private String token = "";
    private long userId = 0;

    public LoginModel(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public LoginModel() {
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
