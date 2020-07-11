package com.gestor.jonny.red.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gestor.jonny.red.SQLite.Managers.Estilos.EstiloManager;
import com.gestor.jonny.red.SQLite.Managers.Instrumentos.InstrumentoManager;
import com.gestor.jonny.red.SQLite.Managers.Rols.RolManager;

public class DatabaseManager {
    public EstiloManager estiloManager;
    public InstrumentoManager instrumentoManager;
    public RolManager rolManager;

    public DatabaseManager(Context context) {
        LocalDatabase localDatabase = new LocalDatabase(context);
        SQLiteDatabase writableDatabase = localDatabase.getWritableDatabase();
        SQLiteDatabase readableDatabase = localDatabase.getReadableDatabase();
        estiloManager = new EstiloManager(writableDatabase, readableDatabase);
        instrumentoManager = new InstrumentoManager(writableDatabase, readableDatabase);
        rolManager = new RolManager(writableDatabase, readableDatabase);
    }
}
