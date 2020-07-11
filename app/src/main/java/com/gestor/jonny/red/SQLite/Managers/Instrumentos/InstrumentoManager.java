package com.gestor.jonny.red.SQLite.Managers.Instrumentos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gestor.jonny.red.Models.InstrumentoModel;

import java.util.ArrayList;

public class InstrumentoManager {
    private String tableName = "Instrumento";
    private String instrumentoIdKey = "instrumentoId";
    private String nombreKey = "nombre";

    private SQLiteDatabase writableDatabase;
    private SQLiteDatabase readableDatabase;

    public InstrumentoManager(SQLiteDatabase writableDatabase, SQLiteDatabase readableDatabase) {
        this.writableDatabase = writableDatabase;
        this.readableDatabase = readableDatabase;
    }

    public ArrayList<InstrumentoModel> getAllInstrumentos() {
        ArrayList<InstrumentoModel> instrumentos = new ArrayList<>();
        Cursor cursor = readableDatabase.rawQuery("select * from " + tableName, null);
        while (cursor.moveToNext()) {
            instrumentos.add(parseCursorToInstrumentoModel(cursor));
        }

        cursor.close();
        return instrumentos;
    }

    public InstrumentoModel getInstrumentoForInstrumentoId(long instrumentoId) {
        String Query = "Select * from " + tableName + " where " + instrumentoIdKey + " = " + String.valueOf(instrumentoId);
        Cursor cursor = readableDatabase.rawQuery(Query, null);
        if (cursor.moveToNext()) {
            return parseCursorToInstrumentoModel(cursor);
        }

        return null;
    }

    public void addInstrumento(InstrumentoModel instrumento) {
        ContentValues content = fillInstrumentoDataToDatabaseObject(instrumento);
        writableDatabase.insert(tableName, null, content);
    }

    private ContentValues fillInstrumentoDataToDatabaseObject(InstrumentoModel instrumento) {
        ContentValues cv = new ContentValues();
        cv.put(instrumentoIdKey, instrumento.getId());
        cv.put(nombreKey, instrumento.getNombre());

        return cv;
    }

    private InstrumentoModel parseCursorToInstrumentoModel(Cursor cursor) {
        InstrumentoModel instrumento = new InstrumentoModel();
        instrumento.setId(cursor.getLong(cursor.getColumnIndex(instrumentoIdKey)));
        instrumento.setNombre(cursor.getString(cursor.getColumnIndex(nombreKey)));

        return instrumento;
    }
}
