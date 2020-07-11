package com.gestor.jonny.red.SQLite.Managers.Estilos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gestor.jonny.red.Models.EstiloModel;
import com.gestor.jonny.red.Models.RolModel;

import java.util.ArrayList;

public class EstiloManager {
    private String tableName = "Estilo";
    private String estiloIdKey = "estiloId";
    private String nombreKey = "nombre";

    private SQLiteDatabase writableDatabase;
    private SQLiteDatabase readableDatabase;

    public EstiloManager(SQLiteDatabase writableDatabase, SQLiteDatabase readableDatabase) {
        this.writableDatabase = writableDatabase;
        this.readableDatabase = readableDatabase;
    }

    public ArrayList<EstiloModel> getAllEstilos() {
        ArrayList<EstiloModel> estilos = new ArrayList<>();
        Cursor cursor = readableDatabase.rawQuery("select * from " + tableName, null);
        while (cursor.moveToNext()) {
            estilos.add(parseCursorToEstiloModel(cursor));
        }

        cursor.close();
        return estilos;
    }

    public EstiloModel getEstiloForEstiloId(long estiloId) {
        String Query = "Select * from " + tableName + " where " + estiloIdKey + " = " + String.valueOf(estiloId);
        Cursor cursor = readableDatabase.rawQuery(Query, null);
        if (cursor.moveToNext()) {
            return parseCursorToEstiloModel(cursor);
        }

        return null;
    }

    public void addEstilo(EstiloModel estilo) {
        ContentValues content = fillEstiloDataToDatabaseObject(estilo);
        writableDatabase.insert(tableName, null, content);
    }

    private ContentValues fillEstiloDataToDatabaseObject(EstiloModel estilo) {
        ContentValues cv = new ContentValues();
        cv.put(estiloIdKey, estilo.getId());
        cv.put(nombreKey, estilo.getNombre());

        return cv;
    }

    private EstiloModel parseCursorToEstiloModel(Cursor cursor) {
        EstiloModel estilo = new EstiloModel();
        estilo.setId(cursor.getLong(cursor.getColumnIndex(estiloIdKey)));
        estilo.setNombre(cursor.getString(cursor.getColumnIndex(nombreKey)));

        return estilo;
    }

}
