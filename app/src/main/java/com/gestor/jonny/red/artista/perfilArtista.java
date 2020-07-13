package com.gestor.jonny.red.artista;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.gestor.jonny.red.R;
import com.gestor.jonny.red.Commons.Commons;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class perfilArtista extends YouTubeBaseActivity implements  YouTubePlayer.OnInitializedListener {
    //METER INYECCIÓN DE DEPENDENCIAS(BUTTERKNIFE, DAGGER)
    //REVISAR DISEÑO Y LIMPIAR LA CLASE

    public static String URL_LOCALHOST = "http://www.djmrbug.com/artistas/";
    private YouTubePlayerView youTubeView;
    private YouTubePlayer youtubePlayer;
    private ArrayList<String> urlImagenes = new ArrayList<>();
    private ArrayList<String> tituloImagenes = new ArrayList<>();
    Bitmap imagenDePortada = null;
    Bitmap imagenDeArtista = null;
    String usuario = "";
    String nombre = "";
    String apellidos = "";
    String edad = "";
    String fecha = "";
    String correo = "";
    String pais = "";
    String ciudad = "";
    String recorrido = "";
    String precio = "";
    String instru =  "";
    String generos = "";
    String nombreArtista = "";
    String paginaWeb = "";
    String rol = "";
    String usuarioSesion;
    Button perfilVista;
    Button galeriaVista;
    Button videosVista;
    ImageButton escribirVista;
    Button personalVista;
    Button profesionalVista;
    Button recorridoVista;
    GridView imagenes;
    RelativeLayout perfil;
    RelativeLayout galeria;
    RelativeLayout videos;
    RelativeLayout txtPersoVista;
    RelativeLayout txtProfeVista;
    RelativeLayout txtRecoVista;
    TextView nombreVista;
    TextView apeVista;
    TextView edadVista;
    TextView fechaVista;
    TextView correoVista;
    TextView paisVista;
    TextView ciudadVista;
    TextView nombreArVista;
    TextView generosVista;
    TextView instruVista;
    TextView pagWebVista;
    TextView usuVista;
    TextView precioVista;
    ScrollView scrollRecorrido;
    RelativeLayout imgAmpliado;
    ImageView imgArtistaAmpliado;
    TextView tituloImgAmpliadoArtista;
    ImageButton crossImgArtista;
    TextView generoCaso;
    TextView instruCaso;
    Button videoPromocionVista;
    Button redesSocialesVista;
    RelativeLayout layoutPromocion;
    RelativeLayout layoutRedesSociales;
    LinearLayout layoutEnlacesRedes;
    RelativeLayout layoutWebView;
    ImageButton crossButton;
    WebView viewRedesSociales;
    RelativeLayout indicador;
    ProgressDialog progress;

    String youtubeVideo;
    String enlacesRedesSociales;
    RelativeLayout dialogoMensaje;
    Button enviarMensaje;
    ImageButton cerrarDialogoMensaje;
    EditText asuntoMensaje;
    EditText textoMensaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_vista);

        recojerCampos();

        recojerIntent();

        recojerDatos();

        inicializarYoutube();

        consultaImagenes();

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        imagenes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView image = (ImageView)view.findViewById(R.id.imagenGale);
                tituloImgAmpliadoArtista.setText(tituloImagenes.get(position));
                imgArtistaAmpliado.setImageBitmap(((BitmapDrawable)image.getDrawable()).getBitmap());
                imgAmpliado.startAnimation(inFromRightAnimation(R.id.imgAmpliadoArtista));
            }
        });

        perfilVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfil.setVisibility(View.VISIBLE);
                galeria.setVisibility(View.INVISIBLE);
                videos.setVisibility(View.INVISIBLE);
                RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) indicador.getLayoutParams();
                par.setMargins(Commons.dpToPx(25,perfilArtista.this), Commons.dpToPx(36,perfilArtista.this), 0, 0);
                par.width = perfilVista.getText().length() * 20;
                indicador.setLayoutParams(par);
            }
        });

        galeriaVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfil.setVisibility(View.INVISIBLE);
                galeria.setVisibility(View.VISIBLE);
                videos.setVisibility(View.INVISIBLE);
                RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) indicador.getLayoutParams();
                par.setMargins(Commons.getScreenWidth(getApplicationContext(), perfilArtista.this)/2- Commons.dpToPx(15,perfilArtista.this), Commons.dpToPx(36,perfilArtista.this), 0, 0);
                par.width = galeriaVista.getText().length() * 20;
                indicador.setLayoutParams(par);

            }
        });

        videosVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfil.setVisibility(View.INVISIBLE);
                galeria.setVisibility(View.INVISIBLE);
                videos.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) indicador.getLayoutParams();
                par.setMargins(Commons.getScreenWidth(getApplicationContext(), perfilArtista.this) - Commons.dpToPx(40,perfilArtista.this), Commons.dpToPx(36,perfilArtista.this), 0, 0);
                par.width = videosVista.getText().length() * 20;
                indicador.setLayoutParams(par);
            }
        });

        escribirVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoMensaje.startAnimation(inFromRightAnimation(R.id.dialogEnviarMensaje));
            }
        });

        personalVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = txtPersoVista.getLayoutParams();
                if (params.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
                    params.height = 0;
                } else {
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }

                txtPersoVista.setLayoutParams(params);
            }
        });

        profesionalVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = txtProfeVista.getLayoutParams();
                if (params.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
                    params.height = 0;
                } else {
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }

                txtProfeVista.setLayoutParams(params);
            }
        });

        recorridoVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = txtRecoVista.getLayoutParams();
                if (params.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
                    params.height = 0;
                }else{
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }
                txtRecoVista.setLayoutParams(params);
            }
        });

        crossImgArtista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgAmpliado.startAnimation(outToRightAnimation(R.id.imgAmpliadoArtista));
                imgArtistaAmpliado.setImageBitmap(null);
            }
        });

        videoPromocionVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = layoutPromocion.getLayoutParams();
                if(params.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
                    params.height = 0;
                }else{
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }
                layoutPromocion.setLayoutParams(params);
            }
        });
        redesSocialesVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = layoutRedesSociales.getLayoutParams();
                if(params.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
                    params.height = 0;
                }else{
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }
                layoutRedesSociales.setLayoutParams(params);
            }

        });

        crossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutRedesSociales.startAnimation(outToRightAnimation(R.id.WebViewLayout));
            }
        });

        enviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkCamposMensaje()){
                    enviarMensaje(asuntoMensaje.getText()+"", textoMensaje.getText()+"");
                }else{
                    Toast.makeText(getApplicationContext(), "Debe rellenar ambos campos", Toast.LENGTH_LONG).show();
                }
            }
        });

        cerrarDialogoMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoMensaje.startAnimation(outToRightAnimation(R.id.dialogEnviarMensaje));
            }
        });
    }

    private void recojerCampos() {
        perfilVista = (Button)findViewById(R.id.perfilVista);
        galeriaVista = (Button)findViewById(R.id.galeriaVista);
        videosVista = (Button)findViewById(R.id.videosVista);
        escribirVista = (ImageButton)findViewById(R.id.escribirVista);
        personalVista = (Button)findViewById(R.id.personalVista);
        profesionalVista = (Button)findViewById(R.id.profesionalVista);
        recorridoVista = (Button)findViewById(R.id.recorridoVista);
        imagenes = (GridView)findViewById(R.id.gridVista);
        perfil = (RelativeLayout)findViewById(R.id.layoutPerfilVista);
        galeria = (RelativeLayout)findViewById(R.id.layoutGaleVista);
        videos = (RelativeLayout)findViewById(R.id.layoutVideosVista);
        txtPersoVista = (RelativeLayout)findViewById(R.id.txtPersoVista);
        txtProfeVista = (RelativeLayout)findViewById(R.id.txtProfeVista);
        txtRecoVista = (RelativeLayout)findViewById(R.id.txtRecoVista);
        nombreVista =(TextView)findViewById(R.id.nombreVista);
        apeVista = (TextView)findViewById(R.id.apeVista);
        edadVista = (TextView)findViewById(R.id.edadVista);
        fechaVista = (TextView)findViewById(R.id.fechaVista);
        correoVista = (TextView)findViewById(R.id.correoVista);
        paisVista = (TextView)findViewById(R.id.paisVista);
        ciudadVista = (TextView)findViewById(R.id.ciudadVista);
        nombreArVista =(TextView)findViewById(R.id.nombreArVista);
        generosVista = (TextView)findViewById(R.id.generosVista);
        instruVista = (TextView)findViewById(R.id.instruVista);
        pagWebVista = (TextView)findViewById(R.id.pagWebVista);
        usuVista = (TextView)findViewById(R.id.usuVista);
        scrollRecorrido = (ScrollView)findViewById(R.id.scrollRecoVista);
        precioVista = (TextView)findViewById(R.id.precioVista);
        imgAmpliado = (RelativeLayout)findViewById(R.id.imgAmpliadoArtista);
        imgArtistaAmpliado = (ImageView)findViewById(R.id.fotoAmpliado);
        tituloImgAmpliadoArtista = (TextView)findViewById(R.id.tituloFotoArtista);
        crossImgArtista = (ImageButton)findViewById(R.id.crossFotoArtista);
        generoCaso = (TextView)findViewById(R.id.generoBarVista);
        instruCaso = (TextView)findViewById(R.id.pagWebBarVista);
        videoPromocionVista = (Button)findViewById(R.id.videoPromocionVista);
        layoutPromocion = (RelativeLayout)findViewById(R.id.layoutPromocionVista);
        redesSocialesVista = (Button) findViewById(R.id.redesSocialesVista);
        layoutRedesSociales = (RelativeLayout)findViewById(R.id.layoutRedesSocialesVista);
        layoutEnlacesRedes = (LinearLayout)findViewById(R.id.layoutEnlacesRedesVista);
        layoutWebView = (RelativeLayout)findViewById(R.id.WebViewLayout);
        crossButton = (ImageButton)findViewById(R.id.crossButtonVista);
        viewRedesSociales = (WebView)findViewById(R.id.webViewVista);
        dialogoMensaje = (RelativeLayout)findViewById(R.id.dialogEnviarMensaje);
        enviarMensaje = (Button)findViewById(R.id.botonEnviarMensaje);
        cerrarDialogoMensaje = (ImageButton)findViewById(R.id.cerrarDialogoMensaje);
        asuntoMensaje = (EditText)findViewById(R.id.campoAsuntoMensaje);
        textoMensaje = (EditText)findViewById(R.id.campoTextoMensaje);
        indicador = (RelativeLayout)findViewById(R.id.indicadorVista);
    }

    private void recojerIntent(){
        Intent intent = getIntent();
        if (intent != null){
            usuario = intent.getStringExtra("usuario");
            usuarioSesion = intent.getStringExtra("usuarioSesion");
        }
    }

    private boolean checkCamposMensaje(){
        return asuntoMensaje.getText().length() > 0 && textoMensaje.getText().length() > 0;
    }

    private void inicializarDatosProfesional(){
        nombreArVista.setText(nombreArtista);
        generosVista.setText(generos);
        instruVista.setText(instru);
        pagWebVista.setText(paginaWeb);
        precioVista.setText(precio);
    }

    private void inicializarTextosPerfilPersonal(){
        nombreVista.setText(nombre);
        apeVista.setText(apellidos);
        edadVista.setText(edad);
        fechaVista.setText(fecha);
        correoVista.setText(correo);
        paisVista.setText(pais);
        ciudadVista.setText(ciudad);
    }

    private void inicializarYoutube(){
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_viewVista);
        youTubeView.initialize(Commons.YOUTUBE_API_KEY, this);
    }

    public void separarRecorridos(String recorrido){
        LinearLayout viewPrincipal = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams paramsPrincipal = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        viewPrincipal.setLayoutParams(paramsPrincipal);
        viewPrincipal.setOrientation(LinearLayout.VERTICAL);

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < recorrido.length(); i++) {
            char c = recorrido.charAt(i);
            if(c != 'º'){
                buffer.append(c);
            }else{
                añadirCamposRecorrido(buffer + "", viewPrincipal);
                buffer.delete(0,buffer.length());
            }
        }

        scrollRecorrido.addView(viewPrincipal);
    }

    public void añadirCamposRecorrido(String texto, LinearLayout viewPrincipal){
        RelativeLayout subview = new RelativeLayout(getApplicationContext());
        TextView tipoCampo = new TextView(getApplicationContext());
        TextView ciudadCampo = new TextView(getApplicationContext());
        TextView generoCampo = new TextView(getApplicationContext());
        TextView instruCampo = new TextView(getApplicationContext());
        TextView fechaIniCampo = new TextView(getApplicationContext());
        TextView fechafinCampo = new TextView(getApplicationContext());
        TextView ResumenCampo = new TextView(getApplicationContext());
        TextView tipoTexto = new TextView(getApplicationContext());
        TextView ciudadTexto = new TextView(getApplicationContext());
        TextView generoTexto = new TextView(getApplicationContext());
        TextView instruTexto = new TextView(getApplicationContext());
        TextView fechaIniTexto = new TextView(getApplicationContext());
        TextView fechaFinTexto = new TextView(getApplicationContext());
        TextView resumenTexto = new TextView(getApplicationContext());

        //aplicar estilo a los componentes

        RelativeLayout.LayoutParams paramsSubView = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsSubView.addRule(RelativeLayout.CENTER_IN_PARENT);
        subview.setLayoutParams(paramsSubView);


        RelativeLayout.LayoutParams tipoCampoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tipoCampoParams.setMargins(Commons.dpToPx(15, this),Commons.dpToPx(15, this),0,0);
        tipoCampo.setLayoutParams(tipoCampoParams);
        tipoCampo.setTextColor(Color.BLACK);

        RelativeLayout.LayoutParams tipoTextoCampoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tipoTextoCampoParams.setMargins(Commons.dpToPx(150, this),Commons.dpToPx(15, this),Commons.dpToPx(15, this),0);
        tipoTexto.setLayoutParams(tipoTextoCampoParams);
        tipoTexto.setTextColor(Color.BLACK);

        RelativeLayout.LayoutParams generoCampoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        generoCampoParams.setMargins(Commons.dpToPx(15, this),Commons.dpToPx(45, this),0,0);
        generoCampo.setLayoutParams(generoCampoParams);
        generoCampo.setTextColor(Color.BLACK);

        RelativeLayout.LayoutParams ciudadTextoCampoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ciudadTextoCampoParams.setMargins(Commons.dpToPx(150, this),Commons.dpToPx(45, this),Commons.dpToPx(15, this),0);
        ciudadTexto.setLayoutParams(ciudadTextoCampoParams);
        ciudadTexto.setTextColor(Color.BLACK);

        RelativeLayout.LayoutParams instruCampoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        instruCampoParams.setMargins(Commons.dpToPx(15, this),Commons.dpToPx(75, this),0,0);
        instruCampo.setLayoutParams(instruCampoParams);
        instruCampo.setTextColor(Color.BLACK);

        RelativeLayout.LayoutParams generoTextoCampoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        generoTextoCampoParams.setMargins(Commons.dpToPx(150, this),Commons.dpToPx(75, this),Commons.dpToPx(15, this),0);
        generoTexto.setLayoutParams(generoTextoCampoParams);
        generoTexto.setTextColor(Color.BLACK);

        RelativeLayout.LayoutParams fechaIniCampoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fechaIniCampoParams.setMargins(Commons.dpToPx(15, this),Commons.dpToPx(105, this),0,0);
        fechaIniCampo.setLayoutParams(fechaIniCampoParams);
        fechaIniCampo.setTextColor(Color.BLACK);

        RelativeLayout.LayoutParams instruTextoCampoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        instruTextoCampoParams.setMargins(Commons.dpToPx(150, this),Commons.dpToPx(105, this),Commons.dpToPx(15, this),0);
        instruTexto.setLayoutParams(instruTextoCampoParams);
        instruTexto.setTextColor(Color.BLACK);

        RelativeLayout.LayoutParams fechafinCampoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fechafinCampoParams.setMargins(Commons.dpToPx(15, this),Commons.dpToPx(135, this),0,0);
        fechafinCampo.setLayoutParams(fechafinCampoParams);
        fechafinCampo.setTextColor(Color.BLACK);

        RelativeLayout.LayoutParams fechaIniTextoCampoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fechaIniTextoCampoParams.setMargins(Commons.dpToPx(150, this),Commons.dpToPx(135, this),Commons.dpToPx(15, this),0);
        fechaIniTexto.setLayoutParams(fechaIniTextoCampoParams);
        fechaIniTexto.setTextColor(Color.BLACK);

        RelativeLayout.LayoutParams ciudadCampoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ciudadCampoParams.setMargins(Commons.dpToPx(15, this),Commons.dpToPx(165, this),0,0);
        ciudadCampo.setLayoutParams(ciudadCampoParams);
        ciudadCampo.setTextColor(Color.BLACK);

        RelativeLayout.LayoutParams fechaFinTextoCampoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fechaFinTextoCampoParams.setMargins(Commons.dpToPx(150, this),Commons.dpToPx(165, this),Commons.dpToPx(15, this),0);
        fechaFinTexto.setLayoutParams(fechaFinTextoCampoParams);
        fechaFinTexto.setTextColor(Color.BLACK);

        RelativeLayout.LayoutParams ResumenCampoCampoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ResumenCampoCampoParams.setMargins(Commons.dpToPx(15, this),Commons.dpToPx(195, this),0,Commons.dpToPx(15, this));
        ResumenCampo.setLayoutParams(ResumenCampoCampoParams);
        ResumenCampo.setTextColor(Color.BLACK);

        RelativeLayout.LayoutParams ResumenTextoCampoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ResumenTextoCampoParams.setMargins(Commons.dpToPx(150, this),Commons.dpToPx(195, this),Commons.dpToPx(15, this),Commons.dpToPx(15, this));
        resumenTexto.setLayoutParams(ResumenTextoCampoParams);
        resumenTexto.setTextColor(Color.BLACK);

        subview.addView(tipoCampo);
        subview.addView(ciudadCampo);
        subview.addView(generoCampo);
        subview.addView(instruCampo);
        subview.addView(fechaIniCampo);
        subview.addView(fechafinCampo);
        subview.addView(tipoTexto);
        subview.addView(ciudadTexto);
        subview.addView(generoTexto);
        subview.addView(instruTexto);
        subview.addView(fechaIniTexto);
        subview.addView(fechaFinTexto);
        subview.addView(ResumenCampo);
        subview.addView(resumenTexto);
        viewPrincipal.addView(subview);

        tipoCampo.setText("Experiencia:");
        ciudadCampo.setText("Ciudad:");
        generoCampo.setText("Genero:");
        instruCampo.setText("Instrumentos:");
        fechaIniCampo.setText("Fecha Inicio:");
        fechafinCampo.setText("Fecha Fin:");
        ResumenCampo.setText("Resumen:");

        //separamos los campos de recorrido y los colocamos en sus campos correspondientes
        ArrayList<String> array = new ArrayList<String>();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if(c != '^'){
                buffer.append(c);
            }else{
                array.add(buffer+"");
                buffer.delete(0,buffer.length());
            }
        }
        array.add(buffer + "");
        buffer.delete(0,buffer.length());

        //rellenamos los campos
        tipoTexto.setText(array.get(0));
        ciudadTexto.setText(array.get(1));
        generoTexto.setText(array.get(2));
        instruTexto.setText(array.get(3));
        fechaIniTexto.setText(array.get(4));
        fechaFinTexto.setText(array.get(5));
        resumenTexto.setText(array.get(6));
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

    private void recojerDatos(){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(perfilArtista.this, "Cargando Perfil",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap<String, String>();
            String endPoint = URL_LOCALHOST + "recargarDatos.php";
            params.put("usuario", usuario);
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,endPoint, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(), "Exito al recojer los datos", Toast.LENGTH_LONG).show();
                                    nombre = (String) response.get("nombre");
                                    apellidos = (String) response.get("ape");
                                    edad = (String) response.get("edad");
                                    fecha = (String) response.get("fecha");
                                    correo = (String) response.get("correo");
                                    pais = (String) response.get("pais");
                                    ciudad = (String) response.get("direccion");
                                    usuario = (String) response.get("usuario");
                                    recorrido = (String) response.get("recorrido");
                                    precio = (String) response.get("precio");
                                    instru = (String) response.get("instru");
                                    generos = (String) response.get("generos");
                                    nombreArtista = (String) response.get("nombreArtista");
                                    paginaWeb = (String) response.get("paginaWeb");
                                    rol = (String) response.get("rol");
                                    youtubeVideo = (String) response.get("youtube");
                                    enlacesRedesSociales = (String) response.get("redes");
                                    inicializarTextosPerfilPersonal();
                                    inicializarDatosProfesional();
                                    scrollRecorrido.removeAllViews();
                                    separarRecorridos(recorrido);
                                    if(youtubeVideo != null && youtubeVideo.length()>0){
                                        youtubePlayer.cueVideo(youtubeVideo);
                                    }
                                    inicializarRedesSociales();
                                    seleccionarRol(Integer.valueOf(rol));

                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al recojer los datos, intentelo de nuevo", Toast.LENGTH_LONG).show();
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
            Toast.makeText(getApplicationContext(), "No dispone de conexión a internet, intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
        }

    }

    public void consultaImagenes(){
        if(Commons.isOnline(this)){
            Map<String, String> params = new HashMap<String, String>();
            String url = URL_LOCALHOST + "consultaImagenes.php";
            params.put("usu", usuario);
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    if(response.length()>0){
                                        JSONArray contenido = (JSONArray) response.get("res");
                                        for (int i = 0; i < contenido.length(); i++) {
                                            JSONObject elemento = (JSONObject) contenido.get(i);
                                            String url = (String)elemento.get("url");
                                            String titulo = (String)elemento.get("titulo");
                                            String portada = (String)elemento.get("portada");
                                            String artista = (String)elemento.get("artista");
                                            if(url.length() > 5){
                                                urlImagenes.add(URL_LOCALHOST + url);
                                                tituloImagenes.add(titulo);
                                                if(portada.equals("true")){
                                                    new imagenPortada().execute(URL_LOCALHOST + url);
                                                }else if(artista.equals("true")){
                                                    new imagenArtista().execute(URL_LOCALHOST + url);
                                                }
                                            }
                                        }
                                        imagenes.setAdapter(new imagenesAdapter(getApplicationContext(), urlImagenes, perfilArtista.this));
                                    }else{
                                        imagenes.setAdapter(new imagenesAdapter(getApplicationContext(), urlImagenes, perfilArtista.this));
                                    }
                                    progress.dismiss();
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
            Toast.makeText(getApplicationContext(), "No dispone de conexión a internet, intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
        }
    }

    public void enviarMensaje(String asunto, String mensaje){
        if(Commons.isOnline(this)){

            progress = ProgressDialog.show(perfilArtista.this, "Enviando mensaje",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap<String, String>();
            String url = URL_LOCALHOST + "enviarMensaje.php";
            params.put("usuarioDestino", usuario);
            params.put("usuarioOrigen", usuarioSesion);
            params.put("asunto", asunto);
            params.put("mensaje", mensaje);
            params.put("fecha", Commons.fechaActual());
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(), "Mensaje enviado", Toast.LENGTH_LONG).show();
                                    dialogoMensaje.startAnimation(outToRightAnimation(R.id.dialogEnviarMensaje));
                                    textoMensaje.setText("");
                                    asuntoMensaje.setText("");
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al enviar el mensaje, intentelo de nuevo", Toast.LENGTH_LONG).show();
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
            Toast.makeText(getApplicationContext(), "No dispone de conexión a internet, intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
        }
    }

    public void descargaImagenPortada(String url){
        try {
            URL urlImagen = new URL(url);
            try {
                Bitmap bmp = BitmapFactory.decodeStream(urlImagen.openConnection().getInputStream());
                imagenDePortada = bmp;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void descargaImagenArtista(String url){
        try {
            URL urlImagen = new URL(url);
            try {
                Bitmap bmp = BitmapFactory.decodeStream(urlImagen.openConnection().getInputStream());
                imagenDeArtista = bmp;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            youtubePlayer = player;
            if(youtubeVideo != null){
                if(youtubeVideo.length() > 2){
                    player.cueVideo(youtubeVideo);// Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
                }
            }
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, 99).show();
        } else {
            //String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, "error al cargar youtube", Toast.LENGTH_LONG).show();
        }
    }

    private class imagenPortada extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... grid) {
            descargaImagenPortada(grid[0]);
            return grid[0];
        }
        @Override
        protected void onPostExecute(String grid) {
            ImageView foto = (ImageView)findViewById(R.id.fotoPerfilArtista);
            foto.setImageBitmap(imagenDePortada);
        }
    }


    private class imagenArtista extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... grid) {
            descargaImagenArtista(grid[0]);
            return grid[0];
        }
        @Override
        protected void onPostExecute(String grid) {
            ImageView foto = (ImageView)findViewById(R.id.imgArtistaVista);
            foto.setImageBitmap(imagenDeArtista);
        }

    }

    private void seleccionarRol(int position) {
        switch (position){
            case 0:
                break;
            case 1:
                generoCaso.setText("Enfoque");
                instruCaso.setText("Idiomas");
                break;
            case 2:
                generoCaso.setText("Enfoque");
                instruCaso.setText("Idiomas");
                break;
            case 4:
                generoCaso.setText("Enfoque");
                instruCaso.setText("Idiomas");
                break;
            case 5:
                generoCaso.setText("Enfoque");
                instruCaso.setText("Idiomas");
                break;
            case 6:
                generoCaso.setText("Enfoque");
                instruCaso.setText("Idiomas");
                break;
            case 7:
                generoCaso.setText("Enfoque");
                instruCaso.setText("Idiomas");
                break;
            case 8:
                generoCaso.setText("Enfoque");
                instruCaso.setText("Idiomas");
                break;
            case 9:
                generoCaso.setText("Enfoque");
                instruCaso.setText("Idiomas");
                break;
            case 11:
                generoCaso.setText("Enfoque");
                instruCaso.setText("Idiomas");
                break;
            case 12:
                generoCaso.setText("Enfoque");
                instruCaso.setText("Idiomas");
                break;
            case 13:
                generoCaso.setText("Enfoque");
                instruCaso.setText("Idiomas");
                break;
            case 14:
                generoCaso.setText("Sector");
                instruCaso.setText("Idiomas");
                break;
            case 15:
                generoCaso.setText("Medidas");
                instruCaso.setText("Idiomas");
                break;
            case 17:
                generoCaso.setText("Enfoque");
                instruCaso.setText("Idiomas");
                break;
            case 18:
                generoCaso.setText("Enfoque");
                instruCaso.setText("Idiomas");
                break;
            case 19:
                generoCaso.setText("Sector");
                instruCaso.setText("Idiomas");
                break;
            case 20:
                generoCaso.setText("Enfoque");
                instruCaso.setText("Idiomas");
                break;
            case 21:
                generoCaso.setText("Enfoque");
                instruCaso.setText("Idiomas");
                break;
            default:
                generoCaso.setText("Enfoque");
                instruCaso.setText("Idiomas");
                break;
        }
    }
    private void inicializarRedesSociales(){
        ArrayList<String> redes = separarRedesSociales(enlacesRedesSociales);
        for (int i = 0; i < redes.size(); i++) {
            layoutEnlacesRedes.addView(generateRedLayout(redes.get(i)));
        }
    }

    private ArrayList<String> separarRedesSociales(String texto){
        ArrayList<String> redes = new ArrayList<>();
        StringBuffer contenido = new StringBuffer();
        for (int i = 0; i < texto.length(); i++) {
            if(texto.charAt(i) == '^'){
                redes.add(contenido+"");
                contenido.delete(0, contenido.length());
            }else{
                contenido.append(texto.charAt(i));
            }
        }
        redes.add(contenido+"");
        return redes;
    }

    private RelativeLayout generateRedLayout(String red){
        RelativeLayout redLayout = new RelativeLayout(getApplicationContext());
        ImageView redImage = new ImageView(getApplicationContext());
        final TextView enlaceRed = new TextView(getApplicationContext());

        enlaceRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutRedesSociales.setVisibility(View.VISIBLE);
                //inFromRightAnimation(R.id.layoutViewRedSocial);
                viewRedesSociales.setWebViewClient(new WebViewClient());
                viewRedesSociales.loadUrl("http://"+enlaceRed.getText()+"");

            }
        });

        RelativeLayout.LayoutParams paramsSubView = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsSubView.addRule(RelativeLayout.CENTER_IN_PARENT);
        paramsSubView.setMargins(0,Commons.dpToPx(20, this), 0, 0);
        redLayout.setLayoutParams(paramsSubView);

        RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(Commons.dpToPx(35, this), Commons.dpToPx(35,this));
        imageParams.setMargins(Commons.dpToPx(15, this),Commons.dpToPx(15, this),0,0);
        redImage.setLayoutParams(imageParams);
        redImage.setImageBitmap(setImage(red));
        redImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

        RelativeLayout.LayoutParams enlaceParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        enlaceParams.setMargins(Commons.dpToPx(70, this),Commons.dpToPx(15, this),0,0);
        enlaceRed.setLayoutParams(enlaceParams);
        enlaceRed.setTextColor(Color.BLACK);
        enlaceRed.setText(red);

        redLayout.addView(redImage);
        redLayout.addView(enlaceRed);

        return redLayout;
    }

    private Bitmap setImage(String red){
        Bitmap bmp = null;
        if(red.contains("uenti")){
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.tuenti);
        }else if(red.contains("witter")){
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.twitter);
        }else if(red.contains("outube")){
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.youtube);
        }else if(red.contains("nstagram")){
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.instagram);
        }else if(red.contains("ahoo")){
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.yahoo);
        }else if(red.contains("otmail")){
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.hotmail);
        }else if(red.contains("acebook")){
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.facebook);
        }else if(red.contains("napchat")){
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.snapchat);
        }else if(red.contains("utlook")){
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.outlook);
        }else if(red.contains("inkedIn")){
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.linkedin);
        }
        return bmp;
    }
    
}
