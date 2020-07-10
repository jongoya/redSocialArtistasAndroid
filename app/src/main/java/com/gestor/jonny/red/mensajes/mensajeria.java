package com.gestor.jonny.red.mensajes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gestor.jonny.red.R;
import com.gestor.jonny.red.Commons.Commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jonny on 17/11/15.
 */
public class mensajeria extends AppCompatActivity {
    public static String URL_LOCALHOST = "http://www.djmrbug.com/artistas/";
    private int entradaOEnviados = 0;
    String usuario = "";
    ArrayList <mensajesItem> mensajesRecibidios = new ArrayList();
    ArrayList <mensajesItem> mensajesEnviados = new ArrayList();
    ArrayList <mensajesItem> todosLosMensajes = new ArrayList();
    listaMensajes adapter = null;
    eliminarAdapter adapterEliminar;
    ListView listaMensajes;
    Button recibidos;
    Button enviados;
    ImageButton redactar;
    TextView fechaMensaje;
    TextView asuntoMensaje;
    TextView contenidoMensaje;
    RelativeLayout principalMensajes;
    ImageButton cerrarMensaje;
    ImageButton enviarMensaje;
    ImageButton volverNuevoMensaje;
    EditText nombreNuevoMensaje;
    EditText asuntoNuevoMensaje;
    EditText cuerpoNuevoMensaje;
    ImageButton responderMensaje;
    RelativeLayout layoutEliminarMensajes;
    ListView listaELiminarMensajes;
    RelativeLayout indicador;
    RelativeLayout layoutPestañas;
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajeria);

        recojerIntent();

        recojerCampos();

        inicializarBandeja();

        listaMensajes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                inFromRightAnimation(R.id.visualizar);
                if(entradaOEnviados == 0){
                    asuntoMensaje.setText(mensajesRecibidios.get(position).getAsunto());
                    contenidoMensaje.setText(mensajesRecibidios.get(position).getMensaje());
                    fechaMensaje.setText(Commons.formatearFecha(mensajesRecibidios.get(position).getFecha()));
                    if(mensajesRecibidios.get(position).getLeido().equals("0")){
                        marcarComoLeido(mensajesRecibidios.get(position));
                    }
                }else if(entradaOEnviados == 1){
                    asuntoMensaje.setText(mensajesEnviados.get(position).getAsunto());
                    contenidoMensaje.setText(mensajesEnviados.get(position).getMensaje());
                    fechaMensaje.setText(Commons.formatearFecha(mensajesEnviados.get(position).getFecha()));
                    if(mensajesEnviados.get(position).getLeido().equals("0")){
                        marcarComoLeido(mensajesEnviados.get(position));
                    }
                }
            }
        });

        recibidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entradaOEnviados = 0;
                adapter = new listaMensajes(getApplicationContext(), mensajesRecibidios, entradaOEnviados);
                listaMensajes.setAdapter(adapter);
                adapterEliminar = new eliminarAdapter(getApplicationContext(), mensajesRecibidios);
                listaELiminarMensajes.setAdapter(adapterEliminar);
                RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) indicador.getLayoutParams();
                par.setMargins(Commons.dpToPx(18,mensajeria.this), Commons.dpToPx(36,mensajeria.this), 0, 0);
                par.width = recibidos.getText().length()*20;
                indicador.setLayoutParams(par);
            }
        });

        enviados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entradaOEnviados = 1;
                adapter = new listaMensajes(getApplicationContext(), mensajesEnviados, entradaOEnviados);
                listaMensajes.setAdapter(adapter);
                adapterEliminar = new eliminarAdapter(getApplicationContext(), mensajesEnviados);
                listaELiminarMensajes.setAdapter(adapterEliminar);
                RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) indicador.getLayoutParams();
                RelativeLayout.LayoutParams layoutPar = (RelativeLayout.LayoutParams) recibidos.getLayoutParams();
                par.setMargins(Commons.getScreenWidth(getApplicationContext(), mensajeria.this)/2 - Commons.dpToPx(20, mensajeria.this), Commons.dpToPx(36,mensajeria.this), 0, 0);
                par.width = enviados.getText().length()*20;
                indicador.setLayoutParams(par);
            }
        });

        redactar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inFromRightAnimation(R.id.redacMensaje);
            }
        });

        cerrarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outToRightAnimation(R.id.visualizar);
            }
        });
        enviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkNewMessage()){
                    enviarMensaje(usuario, nombreNuevoMensaje.getText()+"", asuntoNuevoMensaje.getText()+"", cuerpoNuevoMensaje.getText()+"");
                }
            }
        });

        volverNuevoMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombreNuevoMensaje.setText("");
                asuntoNuevoMensaje.setText("");
                cuerpoNuevoMensaje.setText("");
                outToRightAnimation(R.id.redacMensaje);
            }
        });

        responderMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inFromRightAnimation(R.id.redacMensaje);
            }
        });
        listaELiminarMensajes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                eliminar(position);
            }
        });
    }
    private boolean checkNewMessage(){
        int contador = 0;
        if(nombreNuevoMensaje.getText().length()>0){
            contador++;
        }
        if (asuntoNuevoMensaje.getText().length()>0){
            contador++;
        }
        if(cuerpoNuevoMensaje.getText().length()> 0){
            contador++;
        }
        if(contador == 3){
            return true;
        }else{
            return false;
        }
    }
    private void eliminar(int position){
        mensajesItem mensaje = null;
        if(entradaOEnviados == 0){
            mensaje = mensajesRecibidios.get(position);
        }else if(entradaOEnviados == 1){
            mensaje = mensajesEnviados.get(position);
        }
        eliminarMensaje(mensaje.getUsuarioOrigen(), mensaje.getUsuarioDestino(), mensaje.getAsunto(), mensaje.getFecha(), position);
    }

    private void recojerIntent(){
        Intent intent = getIntent();
        if(intent != null){
            usuario = intent.getStringExtra("usuario");
        }
    }

    private  void recojerCampos(){
        listaMensajes = (ListView)findViewById(R.id.listaMensajes);
        recibidos = (Button)findViewById(R.id.entrada);
        enviados = (Button)findViewById(R.id.enviados);
        redactar = (ImageButton)findViewById(R.id.redactar);
        fechaMensaje = (TextView)findViewById(R.id.campoFechaMensaje);
        asuntoMensaje = (TextView)findViewById(R.id.campoAsuntoMensaje);
        contenidoMensaje = (TextView)findViewById(R.id.campoCuerpoMensaje);
        principalMensajes = (RelativeLayout)findViewById(R.id.principalMensajes);
        cerrarMensaje = (ImageButton)findViewById(R.id.cerrarMensaje);
        enviarMensaje = (ImageButton)findViewById(R.id.enviarNuevoMensaje);
        volverNuevoMensaje = (ImageButton)findViewById(R.id.volverNuevoMensaje);
        nombreNuevoMensaje = (EditText)findViewById(R.id.editUsuMensajes);
        asuntoNuevoMensaje = (EditText)findViewById(R.id.editAsunMensajes);
        cuerpoNuevoMensaje = (EditText)findViewById(R.id.editMenMensajes);
        responderMensaje = (ImageButton)findViewById(R.id.responderVisualizarMensaje);
        layoutEliminarMensajes = (RelativeLayout)findViewById(R.id.layoutEliminarMensajes);
        listaELiminarMensajes = (ListView)findViewById(R.id.listaEliminarMensajes);
        indicador = (RelativeLayout)findViewById(R.id.indicadorMensajes);
        layoutPestañas = (RelativeLayout)findViewById(R.id.layoutPestañasMensajeria);
    }

    private void separarMensajes(ArrayList<mensajesItem> mensajes){
        for (int i = 0; i < mensajes.size(); i++) {
            if(mensajes.get(i).getUsuarioOrigen().equals(usuario)){
                mensajesEnviados.add(mensajes.get(i));
            }else if(mensajes.get(i).getUsuarioDestino().equals(usuario)){
                mensajesRecibidios.add(mensajes.get(i));
            }
        }
    }

    public void inicializarBandeja(){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(mensajeria.this, "Cargando Bandeja",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            String url = URL_LOCALHOST+ "recogerMensajes.php";
            /*JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                if(response.length()>0){
                                    for (int i = 0; i < response.length(); i++) {
                                        JSONObject json = response.getJSONObject(i);
                                        String usuOrigen = (String) json.get("usuarioOrigen");
                                        String usuDestino = (String) json.get("usuarioDestino");
                                        String asunto = (String) json.get("asunto");
                                        String mensaje = (String) json.get("mensaje");
                                        String fecha = (String) json.get("fecha");
                                        String leido = (String) json.get("leido");
                                        mensajesItem correo = new mensajesItem();
                                        correo.setUsuarioOrigen(usuOrigen);
                                        correo.setUsuarioDestino(usuDestino);
                                        correo.setMensaje(mensaje);
                                        correo.setAsunto(asunto);
                                        correo.setFecha(fecha);
                                        correo.setLeido(leido);
                                        todosLosMensajes.add(correo);
                                    }
                                    separarMensajes(todosLosMensajes);
                                    adapter = new listaMensajes(getApplicationContext(), mensajesRecibidios,entradaOEnviados);
                                    listaMensajes.setAdapter(adapter);
                                }else{
                                    Toast.makeText(getApplicationContext(), "No hay mensajes", Toast.LENGTH_LONG).show();
                                }
                                progress.dismiss();
                                onConnectionFinished();
                            } catch (Exception e) {
                                progress.dismiss();
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progress.dismiss();
                    onConnectionFailed(error.toString());
                }
            });
            addToQueue(request);*/
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexión a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }

    }

    private Animation outToRightAnimation(int lay) {
        RelativeLayout layout = (RelativeLayout)findViewById(lay);
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(500);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        layout.setVisibility(View.INVISIBLE);
        return outtoRight;
    }

    private Animation inFromRightAnimation(int lay) {
        RelativeLayout layout = (RelativeLayout)findViewById(lay);
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        layout.setVisibility(View.VISIBLE);
        inFromRight.setDuration(400);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }
    
    public void enviarMensaje(final String usuOr, final String usuDes, final String asun, final String mensa){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(mensajeria.this, "Enviando mensaje",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap<String, String>();
            String url = URL_LOCALHOST + "enviarNuevoMensaje.php";
            params.put("usuarioDestino", usuDes);
            params.put("usuarioOrigen", usuOr);
            params.put("asunto", asun);
            params.put("mensaje", mensa);
            params.put("fecha", Commons.fechaActual());
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(), "Mensaje enviado", Toast.LENGTH_LONG).show();
                                    nombreNuevoMensaje.setText("");
                                    asuntoNuevoMensaje.setText("");
                                    cuerpoNuevoMensaje.setText("");
                                    outToRightAnimation(R.id.redacMensaje);
                                    mensajesItem correo = new mensajesItem();
                                    correo.setUsuarioOrigen(usuOr);
                                    correo.setUsuarioDestino(usuDes);
                                    correo.setMensaje(mensa);
                                    correo.setAsunto(asun);
                                    correo.setFecha(Commons.fechaActual());
                                    correo.setLeido("0");
                                    mensajesEnviados.add(correo);
                                    if(entradaOEnviados==1){
                                        adapter = new listaMensajes(getApplicationContext(), mensajesEnviados, entradaOEnviados);
                                        listaMensajes.setAdapter(adapter);
                                    }
                                }else if((int)response.get("valor") == 300){
                                    Toast.makeText(getApplicationContext(), "El artista es erroneo, por favor verifique el nombre", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al enviar el mensaje", Toast.LENGTH_LONG).show();
                                }
                                progress.dismiss();
                                onConnectionFinished();
                            } catch (Exception e) {
                                progress.dismiss();
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progress.dismiss();
                    onConnectionFailed(error.toString());
                }
            });
            addToQueue(request);*/
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexión a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }
    }

    public void eliminarMensaje(String usuOrigen, String usuDestino, String asunto, String fecha, final int position){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(mensajeria.this, "Eliminando mensaje",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap<String, String>();
            String url = URL_LOCALHOST + "eliminarMensaje.php";
            params.put("usuarioDestino", usuDestino);
            params.put("usuarioOrigen", usuOrigen);
            params.put("asunto", asunto);
            params.put("fecha", fecha);
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(), "Mensaje eliminado", Toast.LENGTH_LONG).show();
                                    if(entradaOEnviados == 0){
                                        mensajesRecibidios.remove(position);
                                        adapter = new listaMensajes(getApplicationContext(), mensajesRecibidios, entradaOEnviados);
                                        listaMensajes.setAdapter(adapter);
                                    }else if(entradaOEnviados == 1){
                                        mensajesEnviados.remove(position);
                                        adapter = new listaMensajes(getApplicationContext(), mensajesEnviados, entradaOEnviados);
                                        listaMensajes.setAdapter(adapter);
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al eliminar el mensaje", Toast.LENGTH_LONG).show();
                                }
                                progress.dismiss();
                                onConnectionFinished();
                            } catch (Exception e) {
                                progress.dismiss();
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progress.dismiss();
                    onConnectionFailed(error.toString());
                }
            });
            addToQueue(request);*/
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexión a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }
    }

    public void marcarComoLeido(final mensajesItem mensaje){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(mensajeria.this, "Marcando....",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap<String, String>();
            String url = URL_LOCALHOST + "leido.php";
            params.put("usuarioDestino", mensaje.getUsuarioDestino());
            params.put("usuarioOrigen", mensaje.getUsuarioOrigen());
            params.put("asunto", mensaje.getAsunto());
            params.put("fecha", mensaje.getFecha());
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(), "Mensaje Marcado", Toast.LENGTH_LONG).show();
                                    mensaje.setLeido("1");
                                    adapter.notifyDataSetChanged();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al marcar el mensaje", Toast.LENGTH_LONG).show();
                                }
                                progress.dismiss();
                                onConnectionFinished();
                            } catch (Exception e) {
                                progress.dismiss();
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progress.dismiss();
                    onConnectionFailed(error.toString());
                }
            });
            addToQueue(request);*/
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexión a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }
    }
}
