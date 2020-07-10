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

    /*public static String getRol(int rol){
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
    }*/

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

    public static ArrayList<RolModel> getOpcionesRol() {
        ArrayList<RolModel> rols = new ArrayList<>();
        rols.add(new RolModel("Cantante", 1));
        rols.add(new RolModel("Dj", 2));
        rols.add(new RolModel("Músico", 3));

        return rols;
    }

    public static ArrayList<InstrumentoModel> getInstrumentos() {
        ArrayList<InstrumentoModel> instrumentos = new ArrayList<>();
        instrumentos.add(new InstrumentoModel("Guitarra", 1));
        instrumentos.add(new InstrumentoModel("Piano", 2));
        instrumentos.add(new InstrumentoModel("Flauta", 3));
        instrumentos.add(new InstrumentoModel("Tambor", 4));
        instrumentos.add(new InstrumentoModel("Bongó", 5));
        instrumentos.add(new InstrumentoModel("Violín", 6));
        instrumentos.add(new InstrumentoModel("Trompeta", 7));
        instrumentos.add(new InstrumentoModel("Armónica", 8));
        instrumentos.add(new InstrumentoModel("Bombo", 9));
        instrumentos.add(new InstrumentoModel("Trombón", 10));
        instrumentos.add(new InstrumentoModel("Tuba", 11));
        instrumentos.add(new InstrumentoModel("Clarinete", 12));
        instrumentos.add(new InstrumentoModel("Fagot", 13));
        instrumentos.add(new InstrumentoModel("Saxofón", 14));
        instrumentos.add(new InstrumentoModel("Oboe", 15));
        instrumentos.add(new InstrumentoModel("Handpan", 16));
        instrumentos.add(new InstrumentoModel("Xilófono", 17));
        instrumentos.add(new InstrumentoModel("Triangulo", 18));
        instrumentos.add(new InstrumentoModel("Metalófono", 19));
        instrumentos.add(new InstrumentoModel("Cajón", 20));
        instrumentos.add(new InstrumentoModel("Campana", 21));
        instrumentos.add(new InstrumentoModel("Platillo", 22));
        instrumentos.add(new InstrumentoModel("Cascabeles", 23));
        instrumentos.add(new InstrumentoModel("Maracas", 24));
        instrumentos.add(new InstrumentoModel("Matraca", 25));
        instrumentos.add(new InstrumentoModel("Sonaja", 26));
        instrumentos.add(new InstrumentoModel("Arco sonoro", 27));
        instrumentos.add(new InstrumentoModel("Caja de música", 28));
        instrumentos.add(new InstrumentoModel("Sansa", 29));
        instrumentos.add(new InstrumentoModel("Tom", 30));
        instrumentos.add(new InstrumentoModel("Verrofon", 31));
        instrumentos.add(new InstrumentoModel("Djembe", 32));
        instrumentos.add(new InstrumentoModel("Timbales", 33));
        instrumentos.add(new InstrumentoModel("Tom-tom", 34));
        instrumentos.add(new InstrumentoModel("Contrabajo", 35));
        instrumentos.add(new InstrumentoModel("Octabajo", 36));
        instrumentos.add(new InstrumentoModel("Violonchelo", 37));
        instrumentos.add(new InstrumentoModel("Bateria", 38));
        instrumentos.add(new InstrumentoModel("Trombón", 39));
        instrumentos.add(new InstrumentoModel("Pandereta", 40));
        instrumentos.add(new InstrumentoModel("Ocarina", 41));
        instrumentos.add(new InstrumentoModel("Guitarra eléctrica", 42));
        instrumentos.add(new InstrumentoModel("Banjo", 43));
        instrumentos.add(new InstrumentoModel("Arpa", 44));
        instrumentos.add(new InstrumentoModel("Bajo", 45));
        instrumentos.add(new InstrumentoModel("Acordeón", 46));
        instrumentos.add(new InstrumentoModel("Órgano", 47));
        instrumentos.add(new InstrumentoModel("Flauta travesera", 48));
        instrumentos.add(new InstrumentoModel("Sintetizador", 49));
        instrumentos.add(new InstrumentoModel("Mesa de dj", 50));

        return instrumentos;
    }

    public static ArrayList<EstiloModel> getEstilos() {
        ArrayList<EstiloModel> estilos = new ArrayList<>();
        estilos.add(new EstiloModel("Música clásica", 1));
        estilos.add(new EstiloModel("Blues", 2));
        estilos.add(new EstiloModel("Jazz", 3));
        estilos.add(new EstiloModel("Rhythm and Blues (R&B)", 4));
        estilos.add(new EstiloModel("Rock and Roll", 5));
        estilos.add(new EstiloModel("Gospel", 6));
        estilos.add(new EstiloModel("Soul", 7));
        estilos.add(new EstiloModel("Rock", 8));
        estilos.add(new EstiloModel("Metal", 9));
        estilos.add(new EstiloModel("Country", 10));
        estilos.add(new EstiloModel("Funk", 11));
        estilos.add(new EstiloModel("Disco", 12));
        estilos.add(new EstiloModel("House", 13));
        estilos.add(new EstiloModel("Techno", 14));
        estilos.add(new EstiloModel("Pop", 15));
        estilos.add(new EstiloModel("Ska", 16));
        estilos.add(new EstiloModel("Reggae", 17));
        estilos.add(new EstiloModel("Hip Hop", 18));
        estilos.add(new EstiloModel("Drum and Bass", 19));
        estilos.add(new EstiloModel("Garage", 20));
        estilos.add(new EstiloModel("Flamenco", 21));
        estilos.add(new EstiloModel("Salsa", 22));
        estilos.add(new EstiloModel("Reggaeton", 23));
        estilos.add(new EstiloModel("Tango", 24));

        return estilos;
    }
}
