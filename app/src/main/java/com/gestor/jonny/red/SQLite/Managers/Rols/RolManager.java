package com.gestor.jonny.red.SQLite.Managers.Rols;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gestor.jonny.red.Models.RolModel;

import java.util.ArrayList;

public class RolManager {
    private String tableName = "Rol";
    private String rolIdKey = "rolId";
    private String nombreKey = "nombre";

    private SQLiteDatabase writableDatabase;
    private SQLiteDatabase readableDatabase;

    public RolManager(SQLiteDatabase writableDatabase, SQLiteDatabase readableDatabase) {
        this.writableDatabase = writableDatabase;
        this.readableDatabase = readableDatabase;
    }

    public ArrayList<RolModel> getAllRols() {
        ArrayList<RolModel> rols = new ArrayList<>();
        Cursor cursor = readableDatabase.rawQuery("select * from " + tableName, null);
        while (cursor.moveToNext()) {
            rols.add(parseCursorToRolModel(cursor));
        }

        cursor.close();
        return rols;
    }

    public RolModel getRolForRolId(long rolId) {
        String Query = "Select * from " + tableName + " where " + rolIdKey + " = " + String.valueOf(rolId);
        Cursor cursor = readableDatabase.rawQuery(Query, null);
        if (cursor.moveToNext()) {
            return parseCursorToRolModel(cursor);
        }

        return null;
    }

    public void addRol(RolModel rol) {
        ContentValues content = fillRolDataToDatabaseObject(rol);
        writableDatabase.insert(tableName, null, content);
    }

    private ContentValues fillRolDataToDatabaseObject(RolModel rol) {
        ContentValues cv = new ContentValues();
        cv.put(rolIdKey, rol.getId());
        cv.put(nombreKey, rol.getNombre());

        return cv;
    }

    private RolModel parseCursorToRolModel(Cursor cursor) {
        RolModel rol = new RolModel();
        rol.setId(cursor.getLong(cursor.getColumnIndex(rolIdKey)));
        rol.setNombre(cursor.getString(cursor.getColumnIndex(nombreKey)));

        return rol;
    }
}
