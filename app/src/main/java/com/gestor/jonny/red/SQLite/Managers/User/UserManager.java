package com.gestor.jonny.red.SQLite.Managers.User;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.gestor.jonny.red.Models.UserModel;
import java.util.ArrayList;
import java.util.Arrays;

public class UserManager {
    private String tableName = "User";
    private String userIdKey = "userId";
    private String nombreKey = "nombre";
    private String apellidosKey = "apellidos";
    private String edadKey = "edad";
    private String fechaKey = "fecha";
    private String correoKey = "correo";
    private String direccionKey = "direccion";
    private String rolKey = "rol";
    private String nombreArtistaKey = "nombreArtista";
    private String instrumentosKey = "instrumentos";
    private String estilosKey = "estilos";
    private String paginaWebKey = "paginaWeb";
    private String precioKey = "precio";

    private SQLiteDatabase writableDatabase;
    private SQLiteDatabase readableDatabase;

    public UserManager(SQLiteDatabase writableDatabase, SQLiteDatabase readableDatabase) {
        this.writableDatabase = writableDatabase;
        this.readableDatabase = readableDatabase;
    }

    public UserModel getUser() {
        Cursor cursor = readableDatabase.rawQuery("select * from " + tableName, null);
        if (cursor.moveToNext()) {
            return parseCursorToUserModel(cursor);
        }

        cursor.close();
        return null;
    }

    public void saveUser(UserModel user) {
        ContentValues content = fillUserDataToDatabaseObject(user);
        long result = writableDatabase.insert(tableName, null, content);
        System.out.println();
    }

    private ContentValues fillUserDataToDatabaseObject(UserModel user) {
        ContentValues cv = new ContentValues();
        cv.put(userIdKey, user.getUserId());
        cv.put(nombreKey, user.getNombre());
        cv.put(apellidosKey, user.getApellidos());
        cv.put(edadKey, user.getEdad());
        cv.put(fechaKey, user.getFecha());
        cv.put(correoKey, user.getCorreo());
        cv.put(direccionKey, user.getDireccion());
        cv.put(rolKey, user.getRol());
        cv.put(nombreArtistaKey, user.getNombreArtista());
        cv.put(instrumentosKey, TextUtils.join(",", user.getInstrumentos()));
        cv.put(estilosKey, TextUtils.join(",", user.getEstilos()));
        cv.put(paginaWebKey, user.getPaginaWeb());
        cv.put(precioKey, user.getPrecio());

        return cv;
    }

    private UserModel parseCursorToUserModel(Cursor cursor) {
        UserModel user = new UserModel();
        user.setUserId(cursor.getLong(cursor.getColumnIndex(userIdKey)));
        user.setNombre(cursor.getString(cursor.getColumnIndex(nombreKey)));
        user.setApellidos(cursor.getString(cursor.getColumnIndex(apellidosKey)));
        user.setEdad(cursor.getInt(cursor.getColumnIndex(edadKey)));
        user.setFecha(cursor.getLong(cursor.getColumnIndex(fechaKey)));
        user.setCorreo(cursor.getString(cursor.getColumnIndex(correoKey)));
        user.setDireccion(cursor.getString(cursor.getColumnIndex(direccionKey)));
        user.setRol(cursor.getLong(cursor.getColumnIndex(rolKey)));
        user.setNombreArtista(cursor.getString(cursor.getColumnIndex(nombreArtistaKey)));
        String instrumentos = cursor.getString(cursor.getColumnIndex(instrumentosKey));
        user.setInstrumentos(getIdsFromString(instrumentos));
        String estilos = cursor.getString(cursor.getColumnIndex(estilosKey));
        user.setEstilos(getIdsFromString(estilos));
        user.setPaginaWeb(cursor.getString(cursor.getColumnIndex(paginaWebKey)));
        user.setPrecio(cursor.getDouble(cursor.getColumnIndex(precioKey)));

        return user;
    }

    private ArrayList getIdsFromString(String idsString) {
        ArrayList result = new ArrayList();
        if (idsString.length() == 0) {
            return new ArrayList();
        }

        ArrayList<String> ids = new ArrayList<>(Arrays.asList(idsString.split(",")));
        for (int i = 0; i < ids.size(); i++) {
            String id = ids.get(i);
            result.add(Long.parseLong(id));
        }

        return result;
    }
}
