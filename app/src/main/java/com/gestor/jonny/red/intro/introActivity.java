package com.gestor.jonny.red.intro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gestor.jonny.red.R;
import com.gestor.jonny.red.commons.Commons;
import com.gestor.jonny.red.login.MainActivity;
import java.util.HashMap;
import java.util.Map;

public class introActivity extends AppCompatActivity {
    public static String URL_LOCALHOST = "http://www.djmrbug.com/artistas/";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_layout);

        stop();
    }

    private void stop(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                checkPreferencias();
            }
        }, 4000);
    }

    private void checkPreferencias(){
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        if(prefs.getString("usuario", "") != ""){
            String usu = prefs.getString("usuario", "");
            String contra = prefs.getString("contra", "");
            login(usu, contra);
        }else{
            Intent myIntent = new Intent(introActivity.this, MainActivity.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(myIntent);
            finish();
        }
    }

    private void login(String usuario, String contrasena){
        if(Commons.conexion(this)){
            loginService(usuario, contrasena);
        }else{
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginService(String usuario, String contrasena){
        if(Commons.isOnline(this)){
            String url =  URL_LOCALHOST + "login.php";
            Map<String, String> params = new HashMap<String, String>();
            params.put("usuario", usuario);
            params.put("contra", contrasena);
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    if(response.length()>1){
                                        String nombre = (String) response.get("nombre");
                                        String apellidos =(String) response.get("ape");
                                        String edad = (String)response.get("edad");
                                        String fecha = (String)response.get("fecha");
                                        String correo = (String)response.get("correo");
                                        String pais = (String) response.get("pais");
                                        String direccion = (String) response.get("direccion");
                                        String usuario = (String) response.get("usuario");
                                        String recorrido = "";
                                        if(!response.isNull("recorrido")){
                                            recorrido = (String) response.get("recorrido");
                                        }
                                        String instru = (String) response.get("instru");
                                        String generos = (String) response.get("generos");
                                        String nombreArtista = (String) response.get("nombreArtista");
                                        String paginaWeb = (String) response.get("paginaWeb");
                                        String precio = (String) response.get("precio");
                                        String rol = (String) response.get("rol");
                                        String youtube = (String) response.get("youtube");
                                        String redes = (String) response.get("redes");
                                        Intent myIntent = new Intent(introActivity.this, plataforma.class);
                                        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        myIntent.putExtra("nombre", nombre);
                                        myIntent.putExtra("apellidos", apellidos);
                                        myIntent.putExtra("edad", edad);
                                        myIntent.putExtra("fecha", fecha);
                                        myIntent.putExtra("correo", correo);
                                        myIntent.putExtra("pais", pais);
                                        myIntent.putExtra("direccion", direccion);
                                        myIntent.putExtra("usuario", usuario);
                                        myIntent.putExtra("recorrido", recorrido);
                                        myIntent.putExtra("precio", precio);
                                        myIntent.putExtra("instru", instru);
                                        myIntent.putExtra("generos", generos);
                                        myIntent.putExtra("nombreArtista", nombreArtista);
                                        myIntent.putExtra("paginaWeb", paginaWeb);
                                        myIntent.putExtra("rol", rol);
                                        myIntent.putExtra("youtube", youtube);
                                        myIntent.putExtra("redes", redes);
                                        getApplicationContext().startActivity(myIntent);
                                        finish();
                                    }
                                }
                                onConnectionFinished();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    onConnectionFailed(error.toString());
                }
            });
            addToQueue(request);*/
        }else{
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }
    }
}
