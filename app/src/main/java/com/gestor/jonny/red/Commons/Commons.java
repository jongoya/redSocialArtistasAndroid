package com.gestor.jonny.red.Commons;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import com.gestor.jonny.red.Models.EstiloModel;
import com.gestor.jonny.red.Models.InstrumentoModel;
import com.gestor.jonny.red.Models.RolModel;
import com.gestor.jonny.red.SharedActivities.Models.ListSelectorModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by jonny on 21/8/16.
 */
public class Commons extends Application {
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

    public static void showGenericAlertMessage(Context contexto, String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(contexto);
        builder1.setMessage(message);
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public static String convertTimestampDateString(long timestamp) {
        SimpleDateFormat sf = new SimpleDateFormat("dd/MMMM/yyyy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp * 1000);
        return sf.format(calendar.getTime());
    }
}
