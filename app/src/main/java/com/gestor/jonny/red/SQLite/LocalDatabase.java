package com.gestor.jonny.red.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalDatabase extends SQLiteOpenHelper {
    private static final String ESTILOS_TABLE_CREATE = "CREATE TABLE Estilo (estiloId INTEGER, nombre TEXT)";
    private static final String INSTRUMENTOS_TABLE_CREATE = "CREATE TABLE Instrumento (instrumentoId INTEGER, nombre TEXT)";
    private static final String ROLS_TABLE_CREATE = "CREATE TABLE Rol (rolId INTEGER, nombre TEXT)";
    private static final String LOGIN_TABLE_CREATE = "CREATE TABLE Login (loginId INTEGER, usuario TEXT, password TEXT, clave TEXT, token TEXT, userId INTEGER)";
    private static final String USER_TABLE_CREATE = "CREATE TABLE User (userId INTEGER, nombre TEXT, apellidos TEXT, edad INTEGER, fecha INTEGER, correo TEXT, direccion TEXT" +
            ", rol INTEGER, nombreArtista TEXT, instrumentos TEXT, estilos TEXT, paginaWeb TEXT, precio REAL)";

    private static final String DB_NAME = "artistas";
    private static final int DB_VERSION = 1;

    public LocalDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ESTILOS_TABLE_CREATE);
        sqLiteDatabase.execSQL(INSTRUMENTOS_TABLE_CREATE);
        sqLiteDatabase.execSQL(ROLS_TABLE_CREATE);
        sqLiteDatabase.execSQL(LOGIN_TABLE_CREATE);
        sqLiteDatabase.execSQL(USER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
