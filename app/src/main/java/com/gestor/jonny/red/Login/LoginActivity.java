package com.gestor.jonny.red.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.gestor.jonny.red.Commons.Commons;
import com.gestor.jonny.red.Commons.Preferencias;
import com.gestor.jonny.red.ContraseñaOlvidada.ContraseñaOlvidadaActivity;
import com.gestor.jonny.red.R;
import com.gestor.jonny.red.Commons.Constants;
import com.gestor.jonny.red.Registro.RegistroPersonalActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.textoUsuario)EditText userField;
    @BindView(R.id.textoContra)EditText passwordField;

    SpinnerAdapter adapter;
    ArrayList <String> opcionesRol = new ArrayList<>();
    Button inicioLogin;
    int rolSeleccionado;
    EditText nom;
    EditText ape;
    EditText edad;
    EditText correo;
    EditText fecha;
    EditText pais;
    EditText ciudad;
    EditText nomArtis;
    EditText genero;
    EditText instru;
    EditText pagWeb;
    EditText usu;
    EditText contra;
    EditText contra2;
    EditText clave;
    Button botonClave;
    Button botonCorreo;
    RelativeLayout claveLayout;
    RelativeLayout correoLayout;
    Spinner spinner;
    CheckBox recordar;
    RelativeLayout layoutLogin;
    RelativeLayout layoutRegistro1;
    RelativeLayout layoutRegistro2;
    RelativeLayout layoutRegistro3;
    RelativeLayout layoutOlvidado;
    EditText claveOlvidado;
    EditText correoOlvidado;
    EditText precioArtista;
    RelativeLayout parteArtista;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //recogerCamposInicio();

        //recogerCampos();

        //inicializarOpcionesRol();

        /*adapter = new spinerAdapter(opcionesRol, this);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    rolSeleccionado = position;
                    parteArtista.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                parteArtista.setVisibility(View.GONE);
            }
        });

        botonClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        botonCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        botonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarCampos();
            }
        });*/
    }

    @OnClick(R.id.botonLogin)void didClickLogin() {
        checkLoginFields();
    }

    @OnClick(R.id.botonRegistro) void didClickRegitro() {
        Intent intent = new Intent(LoginActivity.this, RegistroPersonalActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.textoOlvidado) void didClickContraseñaOlvidada() {
        Intent intent = new Intent(LoginActivity.this, ContraseñaOlvidadaActivity.class);
        startActivity(intent);
    }

    private void checkLoginFields(){
        if (userField.getText().length() == 0) {
            Commons.showGenericAlertMessage(LoginActivity.this, "Debe rellenar el campo usuario");
            return;
        }

        if (passwordField.getText().length() == 0) {
            Commons.showGenericAlertMessage(LoginActivity.this, "Debe rellenar el campo contraseña");
            return;
        }

        if (!Commons.conexion(this)) {
            Commons.showGenericAlertMessage(LoginActivity.this, "No dispone de conexion a internet");
            return;
        }

        loginService();
    }

    /*private void recogerCampos(){
        nom =(EditText)findViewById(R.id.nombre);
        ape =(EditText)findViewById(R.id.apellidos);
        edad =(EditText)findViewById(R.id.edad);
        correo =(EditText)findViewById(R.id.correo);
        fecha =(EditText)findViewById(R.id.fecha);
        pais =(EditText)findViewById(R.id.pais);
        ciudad =(EditText)findViewById(R.id.ciudad);
        nomArtis =(EditText)findViewById(R.id.nomArtista);
        genero =(EditText)findViewById(R.id.generos);
        instru =(EditText)findViewById(R.id.instru);
        pagWeb =(EditText)findViewById(R.id.pagWeb);
        usu =(EditText)findViewById(R.id.usu);
        contra =(EditText)findViewById(R.id.contra);
        contra2 =(EditText)findViewById(R.id.contra2);
        clave =(EditText)findViewById(R.id.clave);
        inicioLogin = (Button)findViewById(R.id.botonLogin);
        texOlvidado = (TextView)findViewById(R.id.textoOlvidado);
        botonRegistrar = (ImageButton) findViewById(R.id.botonRegistro);
        botonpag2 = (ImageButton) findViewById(R.id.botonPag2);
        botonpag3 = (ImageButton)findViewById(R.id.botonPag3);
        botonFinalizar = (ImageButton)findViewById(R.id.botonFinalizar);
        botonAtras = (ImageButton)findViewById(R.id.botonAtras);
        botonEnviar = (ImageButton)findViewById(R.id.botonEnviar);
        recordar = (CheckBox)findViewById(R.id.recordar);
        layoutLogin = (RelativeLayout)findViewById(R.id.Login);
        layoutRegistro1 = (RelativeLayout)findViewById(R.id.root);
        layoutRegistro2 = (RelativeLayout)findViewById(R.id.root);
        layoutRegistro3 = (RelativeLayout)findViewById(R.id.root);
        layoutOlvidado = (RelativeLayout)findViewById(R.id.olvidado);
        claveOlvidado =  (EditText)findViewById(R.id.claveOlvidado);
        correoOlvidado = (EditText)findViewById(R.id.correoOlvidado);
        precioArtista = (EditText)findViewById(R.id.precio);
        parteArtista = (RelativeLayout)findViewById(R.id.parteArtista);
    }*/

    private void recogerCamposInicio(){
        botonClave = (Button)findViewById(R.id.botonClave);
        botonCorreo = (Button)findViewById(R.id.botonCorreo);
        claveLayout =(RelativeLayout)findViewById(R.id.layoutClave);
        correoLayout = (RelativeLayout)findViewById(R.id.layoutCorreo);
        //spinner = (Spinner)findViewById(R.id.selecOpciones);
    }

    public void verificarCampos(){
        if(verificarCamposRegistro3()){
         //deve rellenar todos los campos
            Toast.makeText(getApplicationContext(), "deve rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            String contra1 = contra.getText()+"";
            String contraDoble = contra2.getText()+"";
            if(contra1.equals(contraDoble)) {
                //las contraseñas deven coincidir
                if (contra.getText().length() < 6) {
                    //las contraseñas deven ser superiores a 6 digitos
                    Toast.makeText(getApplicationContext(), "las contraseñas deven ser superiores a 6 digitos", Toast.LENGTH_SHORT).show();
                } else if (usu.getText().length() < 6) {
                    //el usuario deve tener al menos 6 cifras
                    Toast.makeText(getApplicationContext(), "el usuario deve tener al menos 6 cifras", Toast.LENGTH_SHORT).show();
                } else {
                    registroService(rolSeleccionado);
                }
            }else{
                Toast.makeText(getApplicationContext(), "las contraseñas deven coincidir", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /*public void eleccionVolver(){
        if(botonpag2.getVisibility() == View.VISIBLE){
            volver(R.id.root,R.id.Login,R.id.botonPag2,R.id.botonRegistro);
            reiniciarParteRegistro();
            botonAtras.setVisibility(View.INVISIBLE);
        }else if(botonpag3.getVisibility() == View.VISIBLE){
            volver(R.id.root,R.id.root,R.id.botonPag3,R.id.botonPag2);
            nom.setVisibility(View.VISIBLE);
            ape.setVisibility(View.VISIBLE);
            edad.setVisibility(View.VISIBLE);
            correo.setVisibility(View.VISIBLE);
            fecha.setVisibility(View.VISIBLE);
            pais.setVisibility(View.VISIBLE);
            ciudad.setVisibility(View.VISIBLE);
            nomArtis.setVisibility(View.GONE);
            genero.setVisibility(View.GONE);
            instru.setVisibility(View.GONE);
            pagWeb.setVisibility(View.GONE);

        }else if(botonFinalizar.getVisibility() == View.VISIBLE){
            volver(R.id.root,R.id.root,R.id.botonFinalizar,R.id.botonPag3);
            nomArtis.setVisibility(View.VISIBLE);
            genero.setVisibility(View.VISIBLE);
            instru.setVisibility(View.VISIBLE);
            pagWeb.setVisibility(View.VISIBLE);
            usu.setVisibility(View.GONE);
            contra.setVisibility(View.GONE);
            contra2.setVisibility(View.GONE);
            clave.setVisibility(View.GONE);

        }else if(botonEnviar.getVisibility() == View.VISIBLE){
            volver(R.id.olvidado,R.id.Login,R.id.botonEnviar,R.id.botonRegistro);
            botonAtras.setVisibility(View.INVISIBLE);
            claveOlvidado.setVisibility(View.GONE);
            correoOlvidado.setVisibility(View.GONE);

        }
    }*/

    public void verificarCamposOlvidado(){
        if(claveOlvidado.getText().length()==0 && correoOlvidado.getText().length()==0){
            Toast.makeText(getApplicationContext(), "deve rellenar uno de los 2 campos", Toast.LENGTH_SHORT).show();
        }else if(claveOlvidado.getText().length()>0 && correoOlvidado.getText().length()>0){
             Toast.makeText(getApplicationContext(), "deve elegir solo uno de los 2 opciones", Toast.LENGTH_SHORT).show();
        }else{
            //volverInterfazLogin();
            reiniciarParteOlvidado();
        }

    }

    public void reiniciarParteRegistro(){
        nom.setText("");
        nom.setVisibility(View.GONE);
        ape.setText("");
        ape.setVisibility(View.GONE);
        edad.setText("");
        edad.setVisibility(View.GONE);
        correo.setText("");
        correo.setVisibility(View.GONE);
        fecha.setText("");
        fecha.setVisibility(View.GONE);
        pais.setText("");
        pais.setVisibility(View.GONE);
        ciudad.setText("");
        ciudad.setVisibility(View.GONE);
        nomArtis.setText("");
        nomArtis.setVisibility(View.GONE);
        genero.setText("");
        genero.setVisibility(View.GONE);
        instru.setText("");
        instru.setVisibility(View.GONE);
        pagWeb.setText("");
        pagWeb.setVisibility(View.GONE);
        usu.setText("");
        usu.setVisibility(View.GONE);
        contra.setText("");
        contra.setVisibility(View.GONE);
        contra2.setText("");
        contra2.setVisibility(View.GONE);
        clave.setText("");
        clave.setVisibility(View.GONE);
    }

    public void reiniciarParteOlvidado(){
        claveOlvidado.setText("");
        claveOlvidado.setVisibility(View.GONE);
        correoOlvidado.setText("");
        correoOlvidado.setVisibility(View.GONE);
    }

    private void inicializarOpcionesRol(){
        opcionesRol.add("");
        opcionesRol.add("Cantante");
        opcionesRol.add("Dj");
        opcionesRol.add("Musico");
    }

    private boolean verificarCamposRegistro1(){
        int vacio = 0;
        if(nom.getText().length()==0){
            vacio++;
        }
        if(ape.getText().length()==0){
            vacio++;
        }
        if(edad.getText().length()==0){
            vacio++;
        }
        if(correo.getText().length()==0){
            vacio++;
        }
        if(fecha.getText().length()==0){
            vacio++;
        }
        if(pais.getText().length()==0){
            vacio++;
        }
        if(ciudad.getText().length()==0){
            vacio++;
        }
        if(vacio != 0){
            return true;
        }
        return false;
    }

    private boolean verificarCamposRegistro2(){
        int valor = 0;
        if (nomArtis.getText().length() == 0) {
            valor++;
        }
        if (genero.getText().length() == 0) {
            valor++;
        }
        if (instru.getText().length() == 0) {
            valor++;
        }
        if (pagWeb.getText().length() == 0) {
            valor++;
        }
        if (precioArtista.getText().length() == 0) {
            valor++;
        }
        if(valor!=0){
            //si hay algun campo por rellenar
            return false;
        }else{
            return true;
        }
    }

    private boolean verificarCamposRegistro3(){
        boolean vacio = false;
        if(usu.getText().length()==0){
            vacio = true;
        }
        if(contra.getText().length()==0){
            vacio = true;
        }
        if(contra2.getText().length()==0){
            vacio = true;
        }
        if(clave.getText().length()==0){
            vacio = true;
        }
        return vacio;
    }

    private boolean verificarInicioRegistro(){
        SharedPreferences prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        if(prefs.getString("usuario", "") != ""){
            return false;
        }
        return true;
    }

    private void loginService(){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(LoginActivity.this, "Iniciando sesion",
                    " Unos segundos", true);
            String url =  Constants.URL_LOCALHOST + "login.php";
            final EditText num1 = (EditText)findViewById(R.id.textoUsuario);
            final EditText num2 = (EditText)findViewById(R.id.textoContra);
            Map<String, String> params = new HashMap<String, String>();
            params.put("usuario", num1.getText()+"");
            params.put("contra", num2.getText() + "");
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
                                        Intent myIntent = new Intent(MainActivity.this, plataforma.class);
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
                                    }else{
                                        Toast.makeText(getApplicationContext(), "No se han encontrado coincidencias", Toast.LENGTH_LONG).show();
                                    }
                                    progress.dismiss();
                                }else{
                                    progress.dismiss();
                                    Toast.makeText(getApplicationContext(), "Este usuario no esta registrado", Toast.LENGTH_LONG).show();
                                }

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

            //GUARDAR los datos del usuario si el login esta bien
            if (recordar.isChecked()){
                Preferencias.saveUserName(getApplicationContext(), userField.getText().toString());
                Preferencias.savePassword(getApplicationContext(), passwordField.getText().toString());
            }
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }
    }

    private void registroService(int rol){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(LoginActivity.this, "Realizando registro",
                    " Unos segundos", true);
            String url = Constants.URL_LOCALHOST + "registro.php";
            Map<String, String> params = new HashMap<String, String>();
            params.put("nom", nom.getText()+"");
            params.put("ape", ape.getText()+"");
            params.put("edad", edad.getText()+"");
            params.put("correo", correo.getText()+"");
            params.put("fecha", fecha.getText()+"");
            params.put("pais", pais.getText()+"");
            params.put("ciudad", ciudad.getText()+"");
            params.put("nomArtis", nomArtis.getText()+"");
            params.put("genero", genero.getText()+"");
            params.put("instru", instru.getText()+"");
            params.put("pagWeb", pagWeb.getText()+"");
            params.put("precio", precioArtista.getText()+"");
            params.put("usu", usu.getText()+"");
            params.put("contra", contra.getText()+"");
            params.put("clave", clave.getText()+"");
            params.put("rol",String.valueOf(rol));
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String valor = (String)response.get("valor");
                                if(Integer.valueOf(valor) == 200 ||Integer.valueOf(valor) == 250){
                                    cambiarInterfazFinal();
                                    reiniciarParteRegistro();
                                    Toast.makeText(getApplicationContext(), "Exito en el registro", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al registrate intentelo de nuevo", Toast.LENGTH_LONG).show();
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
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }
    }
}

