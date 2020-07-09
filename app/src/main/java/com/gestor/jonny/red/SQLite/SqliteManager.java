package com.gestor.jonny.red.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.gestor.jonny.red.commons.Commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SqliteManager extends SQLiteOpenHelper {
    private static String imageDir = "imageDir";
    private static String APP_FILEDIR = "/data/data/com.gestor.jonny.red/app_imageDir";
    String sqlCreate = "CREATE TABLE imagenes (url TEXT, titulo TEXT, portada TEXT, artista TEXT)";

    public SqliteManager(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL(sqlCreate);
    }

    public static ArrayList recojerImagenes(SQLiteDatabase db){
        ArrayList<ImagenObject> imagenes = new ArrayList<>();
        Cursor c = db.rawQuery(" SELECT * FROM imagenes", null);
        while (c.moveToNext()){
            ImagenObject imagen = new ImagenObject();
            imagen.setUrl(c.getString(0));
            imagen.setTitulo(c.getString(1));
            imagen.setPortada(c.getString(2));
            imagen.setArtista(c.getString(3));
            imagenes.add(imagen);
        }
        return imagenes;
    }

    public static boolean anadirImagen(ImagenObject imagen, SQLiteDatabase db, Context contexto, Bitmap bitmap){
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("url", imagen.getUrl());
        nuevoRegistro.put("titulo",imagen.getTitulo());
        nuevoRegistro.put("portada",imagen.getPortada());
        nuevoRegistro.put("artista",imagen.getArtista());
        long res = db.insert("imagenes", null, nuevoRegistro);
        if(res == -1){//error al guardar la imagen en db
            return false;
        }else{
            String resultado = saveImage(contexto,imagen.getUrl(), bitmap);
            System.out.println(resultado);
        }
        return true;
    }
    public static boolean eliminarImagen(String url, SQLiteDatabase db){
        int res = db.delete("imagenes", "url=\"" + url + "\"", null);
        if(res < 0){
            return false;
        }
        if(!removeImage(url)){
            return false;
        }
        return true;
    }
    public static boolean editarTitulo(String url, String titulo, SQLiteDatabase db){
        ContentValues valores = new ContentValues();
        valores.put("titulo",titulo);
        int res = db.update("imagenes", valores, "url=\"" + url + "\"", null);
        if(res>0){
            return true;
        }
        return false;
    }

    public static boolean setArtista(String urlImagen, String urlArtista, SQLiteDatabase db){
        ContentValues valores = new ContentValues();
        valores.put("artista","false");
        int res = 0;
        if(urlArtista !=""){
            res = db.update("imagenes", valores, "url=\"" + urlArtista + "\"", null);
        }else{
            res = 1;
        }
        if(res>0){
            ContentValues valores2 = new ContentValues();
            valores2.put("artista","true");
            int res2 = db.update("imagenes", valores2, "url=\"" + urlImagen + "\"", null);
            if(res2>0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public static boolean setPortada(String urlImagen, String urlPortada, SQLiteDatabase db){
        ContentValues valores = new ContentValues();
        valores.put("portada","false");
        int res = 0;
        if(urlPortada !=""){
            res = db.update("imagenes", valores, "url=\"" + urlPortada + "\"", null);
        }else{
            res = 1;
        }
        if(res>0){
            ContentValues valores2 = new ContentValues();
            valores2.put("portada","true");
            int res2 = db.update("imagenes", valores2, "url=\"" + urlImagen + "\"", null);
            if(res2>0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public static String saveImage(Context contexto, String url, Bitmap bitmap){
        ContextWrapper cw = new ContextWrapper(contexto.getApplicationContext());
        File directory = cw.getDir(imageDir, Context.MODE_PRIVATE);
        File mypath=new File(directory,url);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            saveImageMini(contexto,url,bitmap);
        } catch (Exception e) {
            return e.toString();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                return e.toString();
            }
        }
        return directory.getAbsolutePath();
    }

    public static String saveImageMini(Context contexto, String url, Bitmap bitmap){
        ContextWrapper cw = new ContextWrapper(contexto.getApplicationContext());
        File directory = cw.getDir(imageDir, Context.MODE_PRIVATE);
        File mypath=new File(directory, Commons.getImagenMini(url));
        FileOutputStream fos = null;
        Bitmap bit = Commons.redimensionarImagen(bitmap, 400);
        try {
            fos = new FileOutputStream(mypath);
            bit.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            return e.toString();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                return e.toString();
            }
        }
        return directory.getAbsolutePath();
    }


    public static String saveUploadImage(Context contexto, String url, Bitmap bitmap){
        ContextWrapper cw = new ContextWrapper(contexto.getApplicationContext());
        File directory = cw.getDir(imageDir, Context.MODE_PRIVATE);
        File mypath=new File(directory,url);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            return e.toString();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                return e.toString();
            }
        }
        return directory.getAbsolutePath();
    }

    private static boolean removeImage(String url){
        File file = new File(APP_FILEDIR, url);
        File fileMini = new File(APP_FILEDIR, Commons.getImagenMini(url));
        boolean first = file.delete();
        boolean second = fileMini.delete();
        if(first && second){
            return true;
        }else{
            return  false;
        }
    }

    public static Bitmap loadImageFromStorage(String url) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        final Bitmap b = BitmapFactory.decodeFile(APP_FILEDIR+"/"+url, options);
        return b;
    }
}
