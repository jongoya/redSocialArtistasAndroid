package com.gestor.jonny.red.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
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

import com.gestor.jonny.red.commons.Commons;
import com.gestor.jonny.red.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    Menu menuPrincipal;
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
    TextView texOlvidado;
    ImageButton botonRegistrar;
    ImageButton botonpag2;
    ImageButton botonpag3;
    ImageButton botonFinalizar;
    ImageButton botonAtras;
    ImageButton botonEnviar;
    Button botonClave;
    Button botonCorreo;
    RelativeLayout claveLayout;
    RelativeLayout correoLayout;
    Spinner spinner;
    EditText textoUsuario;
    EditText textoContra;
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
    public static String URL_LOCALHOST = "http://www.djmrbug.com/artistas/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recogerCamposInicio();

        recogerCampos();

        inicializarOpcionesRol();

        inicioLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        adapter = new spinerAdapter(opcionesRol, this);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    rolSeleccionado = position;
                    parteArtista.setVisibility(View.VISIBLE);
                    mostrarRol(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                parteArtista.setVisibility(View.GONE);
            }
        });
        texOlvidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarInterfazOlvidado();
            }
        });
        botonClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                claveLayout.setVisibility(View.VISIBLE);
                correoLayout.setVisibility(View.GONE);
            }
        });
        botonCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                claveLayout.setVisibility(View.GONE);
                correoLayout.setVisibility(View.VISIBLE);

            }
        });
        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarInterfaz();
            }
        });
        botonpag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarInterfaz2();
            }
        });
        botonpag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarInterfaz3();
            }
        });
        botonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarCampos();
            }
        });
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarCamposOlvidado();
            }
        });
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eleccionVolver();
            }
        });
    }

    private void mostrarRol(int position) {
        switch (position){
            case 0:
                break;
            case 1:
                genero.setHint("Enfoque");
                instru.setHint("Idiomas");
                break;
            case 2:
                genero.setHint("Enfoque");
                instru.setHint("Idiomas");
                break;
            case 4:
                genero.setHint("Enfoque");
                instru.setHint("Idiomas");
                break;
            case 5:
                genero.setHint("Enfoque");
                instru.setHint("Idiomas");
                break;
            case 6:
                genero.setHint("Enfoque");
                instru.setHint("Idiomas");
                break;
            case 7:
                genero.setHint("Enfoque");
                instru.setHint("Idiomas");
                break;
            case 8:
                genero.setHint("Enfoque");
                instru.setHint("Idiomas");
                break;
            case 9:
                genero.setHint("Enfoque");
                instru.setHint("Idiomas");
                break;
            case 11:
                genero.setHint("Enfoque");
                instru.setHint("Idiomas");
                break;
            case 12:
                genero.setHint("Enfoque");
                instru.setHint("Idiomas");
                break;
            case 13:
                genero.setHint("Enfoque");
                instru.setHint("Idiomas");
                break;
            case 14:
                genero.setHint("Sector");
                instru.setHint("Idiomas");
                break;
            case 15:
                genero.setHint("Medidas");
                instru.setHint("Idiomas");
                break;
            case 17:
                genero.setHint("Enfoque");
                instru.setHint("Idiomas");
                break;
            case 18:
                genero.setHint("Enfoque");
                instru.setHint("Idiomas");
                break;
            case 19:
                genero.setHint("Sector");
                instru.setHint("Idiomas");
                break;
            case 20:
                genero.setHint("Enfoque");
                instru.setHint("Idiomas");
                break;
            case 21:
                genero.setHint("Enfoque");
                instru.setHint("Idiomas");
                break;
            default:
                genero.setHint("Enfoque");
                instru.setHint("Idiomas");
                break;
        }
    }

    private void login(){
        if(textoUsuario.length()==0 && textoContra.length()==0){
            Toast.makeText(getApplicationContext(), "Deve rellenar ambos campos", Toast.LENGTH_SHORT).show();
        }else if(textoUsuario.length()>0 && textoContra.length()==0){
            Toast.makeText(getApplicationContext(), "Deve rellenar el campo contraseña", Toast.LENGTH_SHORT).show();
        }else if(textoUsuario.length()==0 && textoContra.length()>0){
            Toast.makeText(getApplicationContext(), "Deve rellenar el campo usuario", Toast.LENGTH_SHORT).show();
        }else{
            if(Commons.conexion(this)){
                if(recordar.isChecked()){
                    guardarEnPreferencias(textoUsuario.getText().toString(), textoContra.getText().toString());
                }
                loginService();
            }else{
                Toast.makeText(getApplicationContext(), "No dispone de conexion a internet", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void recogerCampos(){
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
        inicioLogin = (Button)findViewById(R.id.botonIniciar);
        texOlvidado = (TextView)findViewById(R.id.textoOlvidado);
        botonRegistrar = (ImageButton) findViewById(R.id.botonRegistro);
        botonpag2 = (ImageButton) findViewById(R.id.botonPag2);
        botonpag3 = (ImageButton)findViewById(R.id.botonPag3);
        botonFinalizar = (ImageButton)findViewById(R.id.botonFinalizar);
        botonAtras = (ImageButton)findViewById(R.id.botonAtras);
        botonEnviar = (ImageButton)findViewById(R.id.botonEnviar);
        textoUsuario = (EditText)findViewById(R.id.textoUsuario);
        textoContra = (EditText)findViewById(R.id.textoContra);
        recordar = (CheckBox)findViewById(R.id.recordar);
        layoutLogin = (RelativeLayout)findViewById(R.id.Login);
        layoutRegistro1 = (RelativeLayout)findViewById(R.id.Registro1);
        layoutRegistro2 = (RelativeLayout)findViewById(R.id.registro2);
        layoutRegistro3 = (RelativeLayout)findViewById(R.id.registro3);
        layoutOlvidado = (RelativeLayout)findViewById(R.id.olvidado);
        claveOlvidado =  (EditText)findViewById(R.id.claveOlvidado);
        correoOlvidado = (EditText)findViewById(R.id.correoOlvidado);
        precioArtista = (EditText)findViewById(R.id.precio);
        parteArtista = (RelativeLayout)findViewById(R.id.parteArtista);
    }

    private void recogerCamposInicio(){
        botonClave = (Button)findViewById(R.id.botonClave);
        botonCorreo = (Button)findViewById(R.id.botonCorreo);
        claveLayout =(RelativeLayout)findViewById(R.id.layoutClave);
        correoLayout = (RelativeLayout)findViewById(R.id.layoutCorreo);
        spinner = (Spinner)findViewById(R.id.selecOpciones);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menuPrincipal = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void cambiarInterfaz(){
        if(verificarInicioRegistro()){
            Animation fadeIn = new AlphaAnimation(0, 1);
            fadeIn.setInterpolator(new DecelerateInterpolator());
            fadeIn.setDuration(500);
            AnimationSet animation = new AnimationSet(false);
            animation.addAnimation(fadeIn);
            layoutRegistro1.setAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    layoutRegistro1.setAlpha(1);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }
                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            TranslateAnimation slide = new TranslateAnimation(0,2000,0,0);
            slide.setDuration(500);
            slide.setFillAfter(true);
            layoutLogin.setVisibility(View.VISIBLE);
            layoutLogin.startAnimation(slide);
            slide.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    layoutLogin.setVisibility(View.GONE);
                    layoutRegistro1.setVisibility(View.VISIBLE);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            //actualizar el actionbar
            botonRegistrar.setVisibility(View.INVISIBLE);
            botonpag2.setVisibility(View.VISIBLE);
            botonAtras.setVisibility(View.VISIBLE);
            nom.setVisibility(View.VISIBLE);
            ape.setVisibility(View.VISIBLE);
            edad.setVisibility(View.VISIBLE);
            correo.setVisibility(View.VISIBLE);
            fecha.setVisibility(View.VISIBLE);
            pais.setVisibility(View.VISIBLE);
            ciudad.setVisibility(View.VISIBLE);
        }else{
            Toast.makeText(getApplicationContext(), "Ya te encuentras regitrado en la aplicación", Toast.LENGTH_SHORT).show();
        }
    }

    public void cambiarInterfaz2(){
        if(!verificarCamposRegistro1()){
            Animation fadeIn = new AlphaAnimation(0, 1);
            fadeIn.setInterpolator(new DecelerateInterpolator());
            fadeIn.setDuration(500);
            AnimationSet animation = new AnimationSet(false);
            animation.addAnimation(fadeIn);
            layoutRegistro2.setAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    layoutRegistro2.setAlpha(1);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            TranslateAnimation slide = new TranslateAnimation(0,2000,0,0);
            slide.setDuration(500);
            slide.setFillAfter(true);
            layoutRegistro1.setVisibility(View.VISIBLE);
            layoutRegistro1.startAnimation(slide);
            slide.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    layoutRegistro1.setVisibility(View.GONE);
                    layoutRegistro2.setVisibility(View.VISIBLE);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            botonpag2.setVisibility(View.INVISIBLE);
            botonpag3.setVisibility(View.VISIBLE);
            nomArtis.setVisibility(View.VISIBLE);
            genero.setVisibility(View.VISIBLE);
            instru.setVisibility(View.VISIBLE);
            pagWeb.setVisibility(View.VISIBLE);
        }else{
            Toast.makeText(getApplicationContext(), "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void cambiarInterfaz3(){
        if(verificarCamposRegistro2()){
            Animation fadeIn = new AlphaAnimation(0, 1);
            fadeIn.setInterpolator(new DecelerateInterpolator());
            fadeIn.setDuration(500);
            AnimationSet animation = new AnimationSet(false);
            animation.addAnimation(fadeIn);
            layoutRegistro3.setAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    layoutRegistro3.setAlpha(1);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            TranslateAnimation slide = new TranslateAnimation(0,2000,0,0);
            slide.setDuration(500);
            slide.setFillAfter(true);
            layoutRegistro2.setVisibility(View.VISIBLE);
            layoutRegistro2.startAnimation(slide);
            slide.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    layoutRegistro2.setVisibility(View.GONE);
                    layoutRegistro3.setVisibility(View.VISIBLE);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            botonpag3.setVisibility(View.INVISIBLE);
            botonFinalizar.setVisibility(View.VISIBLE);
            usu.setVisibility(View.VISIBLE);
            contra.setVisibility(View.VISIBLE);
            contra2.setVisibility(View.VISIBLE);
            clave.setVisibility(View.VISIBLE);
        }else{
            Toast.makeText(getApplicationContext(), "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    public void cambiarInterfazFinal(){
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(500);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(fadeIn);
        layoutLogin.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layoutLogin.setAlpha(1);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        TranslateAnimation slide = new TranslateAnimation(0,2000,0,0);
        slide.setDuration(500);
        slide.setFillAfter(true);
        layoutRegistro3.setVisibility(View.VISIBLE);
        layoutRegistro3.startAnimation(slide);
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layoutLogin.setVisibility(View.VISIBLE);
                layoutRegistro3.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        botonFinalizar.setVisibility(View.INVISIBLE);
        botonRegistrar.setVisibility(View.VISIBLE);
        botonAtras.setVisibility(View.INVISIBLE);

    }

    public void cambiarInterfazOlvidado(){
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(500);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(fadeIn);
        layoutOlvidado.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layoutOlvidado.setAlpha(1);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        TranslateAnimation slide = new TranslateAnimation(0,2000,0,0);
        slide.setDuration(500);
        slide.setFillAfter(true);
        layoutLogin.setVisibility(View.VISIBLE);
        layoutLogin.startAnimation(slide);
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layoutLogin.setVisibility(View.GONE);
                layoutOlvidado.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        botonRegistrar.setVisibility(View.INVISIBLE);
        botonEnviar.setVisibility(View.VISIBLE);
        botonAtras.setVisibility(View.VISIBLE);
        claveOlvidado.setVisibility(View.VISIBLE);
        correoOlvidado.setVisibility(View.VISIBLE);
    }

    public void volverInterfazLogin(){
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(500);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(fadeIn);
        layoutLogin.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layoutLogin.setAlpha(1);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        TranslateAnimation slide = new TranslateAnimation(0,2000,0,0);
        slide.setDuration(500);
        slide.setFillAfter(true);
        layoutOlvidado.setVisibility(View.VISIBLE);
        layoutOlvidado.startAnimation(slide);
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                layoutOlvidado.setVisibility(View.GONE);
                layoutLogin.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        botonEnviar.setVisibility(View.INVISIBLE);
        botonRegistrar.setVisibility(View.VISIBLE);
        botonAtras.setVisibility(View.INVISIBLE);
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

    public void volver(int id1, int id2, int id3, int id4){
        final RelativeLayout ln = (RelativeLayout)findViewById(id1);
        final RelativeLayout ln2 = (RelativeLayout)findViewById(id2);
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(500);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(fadeIn);
        ln2.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                ln2.setAlpha(1);
            }
            @Override
            public void onAnimationEnd(Animation animation) {
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        TranslateAnimation slide = new TranslateAnimation(0,2000,0,0);
        slide.setDuration(500);
        slide.setFillAfter(true);
        ln.setVisibility(View.VISIBLE);
        ln.startAnimation(slide);
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                ln.setVisibility(View.GONE);
                ln2.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        ImageButton bot =(ImageButton) findViewById(id3);
        bot.setVisibility(View.INVISIBLE);
        ImageButton bot2 = (ImageButton)findViewById(id4);
        bot2.setVisibility(View.VISIBLE);
    }

    public void eleccionVolver(){
        if(botonpag2.getVisibility() == View.VISIBLE){
            volver(R.id.Registro1,R.id.Login,R.id.botonPag2,R.id.botonRegistro);
            reiniciarParteRegistro();
            botonAtras.setVisibility(View.INVISIBLE);
        }else if(botonpag3.getVisibility() == View.VISIBLE){
            volver(R.id.registro2,R.id.Registro1,R.id.botonPag3,R.id.botonPag2);
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
            volver(R.id.registro3,R.id.registro2,R.id.botonFinalizar,R.id.botonPag3);
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
    }

    public void verificarCamposOlvidado(){
        if(claveOlvidado.getText().length()==0 && correoOlvidado.getText().length()==0){
            Toast.makeText(getApplicationContext(), "deve rellenar uno de los 2 campos", Toast.LENGTH_SHORT).show();
        }else if(claveOlvidado.getText().length()>0 && correoOlvidado.getText().length()>0){
             Toast.makeText(getApplicationContext(), "deve elegir solo uno de los 2 opciones", Toast.LENGTH_SHORT).show();
        }else{
            volverInterfazLogin();
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

    private void guardarEnPreferencias(String usuario, String contraseña){
        SharedPreferences prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("usuario", usuario).apply();
        editor.putString("contra", contraseña).apply();
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
            progress = ProgressDialog.show(MainActivity.this, "Iniciando sesion",
                    " Unos segundos", true);
            String url =  URL_LOCALHOST + "login.php";
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
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }
    }

    private void registroService(int rol){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(MainActivity.this, "Realizando registro",
                    " Unos segundos", true);
            String url = URL_LOCALHOST + "registro.php";
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

