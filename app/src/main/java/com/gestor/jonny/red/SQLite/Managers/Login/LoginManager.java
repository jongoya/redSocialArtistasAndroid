package com.gestor.jonny.red.SQLite.Managers.Login;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gestor.jonny.red.Models.LoginModel;

public class LoginManager {
    private String tableName = "Login";
    private String loginIdKey = "loginId";
    private String usuarioKey = "usuario";
    private String passwordKey = "password";
    private String claveKey = "clave";
    private String tokenKey = "token";
    private String userIdKey = "userId";

    private SQLiteDatabase writableDatabase;
    private SQLiteDatabase readableDatabase;

    public LoginManager(SQLiteDatabase writableDatabase, SQLiteDatabase readableDatabase) {
        this.writableDatabase = writableDatabase;
        this.readableDatabase = readableDatabase;
    }

    public LoginModel getLogin() {
        Cursor cursor = readableDatabase.rawQuery("select * from " + tableName, null);
        if (cursor.moveToNext()) {
            return parseCursorToLoginModel(cursor);
        }

        cursor.close();
        return null;
    }

    public void saveLogin(LoginModel login) {
        ContentValues content = fillLoginDataToDatabaseObject(login);
        long results = writableDatabase.insert(tableName, null, content);
        System.out.println();
    }

    private ContentValues fillLoginDataToDatabaseObject(LoginModel login) {
        ContentValues cv = new ContentValues();
        cv.put(loginIdKey, login.getLoginId());
        cv.put(usuarioKey, login.getUsuario());
        cv.put(passwordKey, login.getPassword());
        cv.put(claveKey, login.getClave());
        cv.put(tokenKey, login.getToken());
        cv.put(userIdKey, login.getUserId());

        return cv;
    }

    private LoginModel parseCursorToLoginModel(Cursor cursor) {
        LoginModel login = new LoginModel();
        login.setLoginId(cursor.getLong(cursor.getColumnIndex(loginIdKey)));
        login.setUsuario(cursor.getString(cursor.getColumnIndex(usuarioKey)));
        login.setPassword(cursor.getString(cursor.getColumnIndex(passwordKey)));
        login.setClave(cursor.getString(cursor.getColumnIndex(claveKey)));
        login.setToken(cursor.getString(cursor.getColumnIndex(tokenKey)));
        login.setUserId(cursor.getLong(cursor.getColumnIndex(userIdKey)));

        return login;
    }
}
