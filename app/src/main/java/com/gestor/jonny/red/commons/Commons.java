package com.gestor.jonny.red.commons;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by jonny on 21/8/16.
 */
public class Commons extends Application {
    final static ArrayList<String> opcionesRol = new ArrayList<>();
    public static String YOUTUBE_API_KEY = "AIzaSyCdakw7-rIuATQ4yjwwg8EAVao8obJ5KrM";
    public static boolean conexion(Context contexto){
        boolean res = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(connectivityManager.TYPE_MOBILE);
        NetworkInfo wifiInfo2 = connectivityManager.getNetworkInfo(connectivityManager.TYPE_WIFI);
        if(wifiInfo.isConnected() || wifiInfo2.isConnected()){
            res= true;
        }
        return res;
    }
    public static int dpToPx(int dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static void removePreferencias(Context contexto){
        SharedPreferences prefs = contexto.getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if(prefs.getString("usuario", "") != ""){
            editor.remove("usuario").apply();
            editor.remove("contra").apply();
        }
    }

    public static String generateRandomValue(){
        char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();

    }

    public static  Bitmap redimensionarImagen(Bitmap bitmap, int tamaño){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > height) {
            // landscape
            float ratio = (float) width / tamaño;
            width = tamaño;
            height = (int)(height / ratio);
        } else if (height > width) {
            // portrait
            float ratio = (float) height / tamaño;
            height = tamaño;
            width = (int)(width / ratio);
        } else {
            // square
            height = tamaño;
            width = tamaño;
        }
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        return bitmap;
    }

    public static String removeLocalHost(String url){
        StringBuffer link = new StringBuffer();
        int contador = 0;
        for (int i = 0; i < url.length(); i++) {
            if(contador >= 4){
                link.append(url.charAt(i));
            }
            if(url.charAt(i) == '/' && contador < 4){
                contador++;
            }
        }
        return link+"";
    }

    public static int getScreenWidth(Context contexto, Activity actividad){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        actividad.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels - Commons.dpToPx(20, contexto);

    }

    public static String getRol(int rol){
        inicializarOpcionesRol();
        switch (rol){
            case 0:
                return opcionesRol.get(0);
            case 1:
                return opcionesRol.get(1);
            case 2:
                return opcionesRol.get(2);
            case 3:
                return opcionesRol.get(3);
            case 4:
                return opcionesRol.get(4);
            case 5:
                return opcionesRol.get(5);
            case 6:
                return opcionesRol.get(6);
            case 7:
                return opcionesRol.get(7);
            case 8:
                return opcionesRol.get(8);
            case 9:
                return opcionesRol.get(9);
            case 10:
                return opcionesRol.get(10);
            case 11:
                return opcionesRol.get(11);
            case 12:
                return opcionesRol.get(12);
            case 13:
                return opcionesRol.get(13);
            case 14:
                return opcionesRol.get(14);
            case 15:
                return opcionesRol.get(15);
            case 16:
                return opcionesRol.get(16);
            case 17:
                return opcionesRol.get(17);
            case 18:
                return opcionesRol.get(18);
            case 19:
                return opcionesRol.get(19);
            case 20:
                return opcionesRol.get(20);
            case 21:
                return opcionesRol.get(21);
            default:
                return opcionesRol.get(21);
        }
    }

    public static void inicializarOpcionesRol(){
        opcionesRol.removeAll(opcionesRol);
        opcionesRol.add("");
        opcionesRol.add("Actor/Actriz");
        opcionesRol.add("Animaciones");
        opcionesRol.add("Cantante");
        opcionesRol.add("Dibujante de comics");
        opcionesRol.add("Diseñador 3D");
        opcionesRol.add("Diseñador de moda");
        opcionesRol.add("Diseñador web");
        opcionesRol.add("Diseñador App");
        opcionesRol.add("Director de cine");
        opcionesRol.add("Dj");
        opcionesRol.add("Escritor");
        opcionesRol.add("Fotografo");
        opcionesRol.add("Grafitero");
        opcionesRol.add("Joyeria");
        opcionesRol.add("Modelaje");
        opcionesRol.add("Musico");
        opcionesRol.add("Pintura");
        opcionesRol.add("Productor de cine");
        opcionesRol.add("Productor de musica");
        opcionesRol.add("Programación");
        opcionesRol.add("Publicidad");
        opcionesRol.add("Tatuador");
    }

    public static boolean isOnline(Context contexto) {
        ConnectivityManager cm =
                (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static String fechaActual(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String formatearFecha(String fecha){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(fecha);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date != null){
            StringBuffer buffer = new StringBuffer();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int hour = cal.get(Calendar.HOUR);
            int min = cal.get(Calendar.MINUTE);
            buffer.append(String.valueOf(day) + "/");
            buffer.append(getMes(month) + "/");
            buffer.append(String.valueOf(year) + "  ");
            buffer.append(String.valueOf(hour) + ":");
            buffer.append(String.valueOf(min));
            return buffer+"";
        }
        return "";
    }

    private static String getMes(int mes){
        switch (mes){
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
            default:
                return "";
        }
    }

    public static String getImagenMini(String url){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < url.length()-4; i++) {
            buffer.append(url.charAt(i));
        }
        buffer.append("Mini.jpg");
        return buffer+"";
    }

    public static String eliminarRuta(String url){
        StringBuffer buffer = new StringBuffer();
        boolean control = false;
        for (int i = 0; i < url.length(); i++) {
            if(control){
                buffer.append(url.charAt(i));
            }
            if(url.charAt(i) == '/'){
                control=true;
            }
        }
        return buffer+"";
    }
}
