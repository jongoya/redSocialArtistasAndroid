package com.gestor.jonny.red.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalDatabase extends SQLiteOpenHelper {
    private static final String ESTILOS_TABLE_CREATE = "CREATE TABLE Estilo (estiloId INTEGER, nombre TEXT)";
    private static final String INSTRUMENTOS_TABLE_CREATE = "CREATE TABLE Instrumento (instrumentoId INTEGER, nombre TEXT)";
    private static final String ROLS_TABLE_CREATE = "CREATE TABLE Rol (rolId INTEGER, nombre TEXT)";

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
