package com.gestor.jonny.red.Models;

import java.io.Serializable;

public class UserAndLoginModel implements Serializable {
    private UserModel user;
    private LoginModel login;

    public UserAndLoginModel(UserModel user, LoginModel login) {
        this.user = user;
        this.login = login;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public LoginModel getLogin() {
        return login;
    }

    public void setLogin(LoginModel login) {
        this.login = login;
    }
}
