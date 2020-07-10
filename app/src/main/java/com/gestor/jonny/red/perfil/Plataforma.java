package com.gestor.jonny.red.perfil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.gestor.jonny.red.SQLite.ImagenObject;
import com.gestor.jonny.red.SQLite.SqliteManager;
import com.gestor.jonny.red.artista.ArtistasAdapter;
import com.gestor.jonny.red.R;
import com.gestor.jonny.red.artista.artistaItem;
import com.gestor.jonny.red.busquedaGlobal.busquedaGlobal;
import com.gestor.jonny.red.busquedaLocal.busquedaLocal;
import com.gestor.jonny.red.Commons.Commons;
import com.gestor.jonny.red.Commons.Constants;
import com.gestor.jonny.red.Commons.Preferencias;
import com.gestor.jonny.red.editar.editarPerfil;
import com.gestor.jonny.red.Login.LoginActivity;
import com.gestor.jonny.red.mensajes.mensajeria;
import com.gestor.jonny.red.artista.perfilArtista;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Plataforma extends YouTubeBaseActivity implements  YouTubePlayer.OnInitializedListener{
    private YouTubePlayerView youTubeView;
    private YouTubePlayer youtubePlayer;
    String nombre = "";
    String apellidos = "";
    String edad = "";
    String fecha = "";
    String correo = "";
    String pais = "";
    String ciudad = "";
    String usuario = "";
    String recorri = "";
    String nombreBar = "";
    String instru =  "";
    String generos = "";
    String nombreArtista = "";
    String paginaWeb = "";
    String rol = "";
    String urlImgNueva = "";
    String youtubeVideo = "";
    String enlacesRedesSociales = "";
    ArrayList<String> urlImagenes = new ArrayList<String>();
    ArrayList<String> videos = new ArrayList<String>();
    Bitmap imagenDePortada = null;
    Bitmap imagenDeArtista = null;
    Bitmap imagenNueva = null;
    ArrayList<String> menuDesplegable =  new ArrayList<String>();
    menuAdapter adapter=null;
    ListView list;
    ListView listaAr;
    ArtistasAdapter adapterFormulario=null;
    ArrayList<artistaItem> artistas = new ArrayList<artistaItem>();
    ArrayList<artistaItem> artistaFormulario = new ArrayList<artistaItem>();
    Button botonSubir;
    Button botonPersonal;
    Button botonProfesional;
    Button botonRecorrido;
    Button perfilUsuario;
    Button galeriaUsuario;
    Button videosUsuario;
    ImageButton siguientePortada;
    ImageButton siguienteArtista;
    Button botonImagenArtista;
    ImageButton finalizarImagenes;
    Button guardarRecorrido;
    ImageButton menu;
    ImageView imagenLogo;
    EditText textoBusqueda;
    Button cerrarImgAmp;
    ImageButton botonTer;
    Button botonElegir;
    TextView nom;
    TextView ape;
    TextView eda;
    TextView fech;
    TextView corr;
    TextView pa;
    TextView ciu;
    GridView gridview;
    RelativeLayout layoutPersonal;
    RelativeLayout layoutProfesional;
    RelativeLayout layoutRecorrido;
    RelativeLayout infoPerfil;
    RelativeLayout infoGaleria;
    RelativeLayout infoVideos;
    RelativeLayout imagenFondo;
    RelativeLayout imagenArtista;
    RelativeLayout recorrido;
    RelativeLayout perfil;
    TextView textoTipo;
    TextView textoCiudadReco;
    TextView textoGeneroReco;
    TextView textoInstruReco;
    TextView textoFechIniReco;
    TextView textoFechFinReco;
    TextView resumenReco;
    RelativeLayout menuLayout;
    RelativeLayout layoutImgAmpliado;
    RelativeLayout layoutSubirImg;
    TextView nombreArtisticoView;
    TextView generosView;
    TextView instrumentosView;
    TextView paginaWebView;
    TextView profePrecio;
    ScrollView scrollRecorrido;
    EditText tituloNueva;
    ImageView portadaSelec;
    ImageView artistaSelec;
    ImageButton cross;
    EditText tituloImg;
    TextView generoCaso;
    TextView instruCaso;
    RelativeLayout layoutFormulario;
    Button botonYoutube;
    Button botonRedes;
    RelativeLayout layoutYoutube;
    RelativeLayout layoutRedes;
    EditText enlaceYoutube;
    ImageButton okYoutube;
    EditText enlaceRedes;
    ImageButton okRedes;
    RelativeLayout videosYoutube;
    LinearLayout enlacesRedes;
    RelativeLayout indicador;
    boolean artistaSeleccionado = false;
    boolean portadaSeleccionado = false;
    ArrayList<String> titulosImagenes = new ArrayList<>();
    ArrayList<ImagenObject> objetosImagenes = new ArrayList<>();
    SqliteManager usdbh;
    SQLiteDatabase db;

    ProgressDialog progress;

    //variables de control sobre las imagenes en el layout ampliado
    boolean esPortada = false;
    boolean esArtista = false;
    String tituloDeImagen = "";
    int imagenAmpliada = 0;
    int posPortada = -1;
    int posArtista = -1;

    WebView redSocial;
    RelativeLayout layoutViewRedSocial;
    ImageView blackCross;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plataforma);

        recogerIntent();

        setearLayouts();

        recogerCampos();

        inicializarBaseDeDatos();

        checkearImagenesPerfil();

        inicializamosMenuDespleagable();

        inicializarTextosPerfilPersonal();

        inicializarDatosProfesional();

        inicializarYoutube();

        separarRecorridos(recorri);
        
        seleccionarRol(Integer.valueOf(rol));

        inicializarRedesSociales();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        progress = ProgressDialog.show(Plataforma.this, "Cargando Perfil", " Unos segundos", true);

        recogerImagenes();


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == urlImagenes.size()-1){
                    RelativeLayout img =(RelativeLayout)findViewById(R.id.layoutSubirImg);
                    img.startAnimation(inFromRightAnimation(R.id.layoutSubirImg));
                }else{
                    imagenAmpliada = position;
                    tituloDeImagen = titulosImagenes.get(position);
                    checkImageSelected(position);
                    ImageView imagen = (ImageView)findViewById(R.id.imgGrande);
                    cargarImagenGrande(urlImagenes.get(imagenAmpliada), imagen);
                    RelativeLayout img =(RelativeLayout)findViewById(R.id.layoutImgAmpliado);
                    img.startAnimation(inFromRightAnimation(R.id.layoutImgAmpliado));
                }
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent myIntent = new Intent(Plataforma.this, busquedaLocal.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    myIntent.putExtra("ciudad", ciudad);
                    myIntent.putExtra("usuario", usuario);
                    myIntent.putExtra("nombreArtista", nombreArtista);
                    getApplicationContext().startActivity(myIntent);
                    menuLayout.startAnimation(outToRightAnimation(R.id.menuLayout));
                }else if(position == 1){
                    Intent myIntent = new Intent(Plataforma.this, busquedaGlobal.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    myIntent.putExtra("usuario", usuario);
                    getApplicationContext().startActivity(myIntent);
                    menuLayout.startAnimation(outToRightAnimation(R.id.menuLayout));
                }else if(position == 2){
                    Intent myIntent = new Intent(Plataforma.this, mensajeria.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    myIntent.putExtra("usuario", usuario);
                    getApplicationContext().startActivity(myIntent);
                    menuLayout.startAnimation(outToRightAnimation(R.id.menuLayout));
                }else if(position == 3){
                    Intent myIntent = new Intent(Plataforma.this, editarPerfil.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    myIntent.putExtra("usuario", usuario);
                    myIntent.putExtra("nombre", nombre);
                    myIntent.putExtra("apellidos",apellidos);
                    myIntent.putExtra("edad",edad);
                    myIntent.putExtra("fecha",fecha);
                    myIntent.putExtra("correo",correo);
                    myIntent.putExtra("pais",pais);
                    myIntent.putExtra("direccion",ciudad);
                    myIntent.putExtra("precio",nombreBar);
                    myIntent.putExtra("instru",instru);
                    myIntent.putExtra("generos",generos);
                    myIntent.putExtra("nombreArtista",nombreArtista);
                    myIntent.putExtra("paginaWeb",paginaWeb);
                    myIntent.putExtra("rol",rol);
                    myIntent.putExtra("recorrido",recorri);
                    getApplicationContext().startActivity(myIntent);
                    menuLayout.startAnimation(outToRightAnimation(R.id.menuLayout));
                }else if(position == 4){
                    Preferencias.removeUserCredentials(getApplicationContext());
                    Intent myIntent = new Intent(Plataforma.this, LoginActivity.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(myIntent);
                    finish();
                }
            }
        });

        //inicializamos la lista del formulario

        listaAr.setBackgroundColor(Color.WHITE);
        listaAr.getBackground().setAlpha(200);
        listaAr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombreUsu = artistaFormulario.get(position).getUsuario();
                Intent myIntent = new Intent(Plataforma.this, perfilArtista.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myIntent.putExtra("usuario", nombreUsu);
                myIntent.putExtra("usuarioSesion", usuario);
                getApplicationContext().startActivity(myIntent);
            }
        });

        textoBusqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                artistaFormulario.removeAll(artistaFormulario);
                adapterFormulario = new ArtistasAdapter(getApplicationContext(), artistaFormulario, Plataforma.this);
                listaAr.setAdapter(adapterFormulario);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(textoBusqueda.getText().length() > 0){
                    consultaArtistas();
                }
            }
        });
        botonSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                new subidaImagen().execute(i);
            }
        });

        botonPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = layoutPersonal.getLayoutParams();
                if(params.height != ViewGroup.LayoutParams.WRAP_CONTENT){
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }else{
                    params.height = 0;
                }
                layoutPersonal.setLayoutParams(params);
            }
        });
        botonProfesional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = layoutProfesional.getLayoutParams();
                if(params.height != ViewGroup.LayoutParams.WRAP_CONTENT){
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }else{
                    params.height = 0;
                }
                layoutProfesional.setLayoutParams(params);
            }
        });
        botonRecorrido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = layoutRecorrido.getLayoutParams();
                if(params.height != ViewGroup.LayoutParams.WRAP_CONTENT){
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }else{
                    params.height = 0;
                }
                layoutRecorrido.setLayoutParams(params);
            }
        });
        perfilUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoPerfil.setVisibility(View.VISIBLE);
                infoGaleria.setVisibility(View.INVISIBLE);
                infoVideos.setVisibility(View.INVISIBLE);
                RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) indicador.getLayoutParams();
                par.setMargins(Commons.dpToPx(25, Plataforma.this), Commons.dpToPx(36, Plataforma.this), 0, 0);
                par.width = perfilUsuario.getText().length()*20;
                indicador.setLayoutParams(par);
            }
        });
        galeriaUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoPerfil.setVisibility(View.INVISIBLE);
                infoGaleria.setVisibility(View.VISIBLE);
                infoVideos.setVisibility(View.INVISIBLE);
                RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) indicador.getLayoutParams();
                par.setMargins(Commons.getScreenWidth(getApplicationContext(), Plataforma.this)/2- Commons.dpToPx(15, Plataforma.this), Commons.dpToPx(36, Plataforma.this), 0, 0);
                par.width = galeriaUsuario.getText().length()*20;
                indicador.setLayoutParams(par);
            }
        });
        videosUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoPerfil.setVisibility(View.INVISIBLE);
                infoGaleria.setVisibility(View.INVISIBLE);
                infoVideos.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) indicador.getLayoutParams();
                par.setMargins(Commons.getScreenWidth(getApplicationContext(), Plataforma.this) - Commons.dpToPx(40, Plataforma.this), Commons.dpToPx(36, Plataforma.this), 0, 0);
                par.width = videosUsuario.getText().length()*20;
                indicador.setLayoutParams(par);
            }
        });
        siguientePortada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) findViewById(R.id.imageView2);
                if(((BitmapDrawable)imageView.getDrawable()).getBitmap() == null){
                    Toast.makeText(getApplicationContext(), "Deve seleccionar una imagen de portada", Toast.LENGTH_LONG).show();
                }else{
                    subidaImagenPortada(imagenDePortada);

                }
            }
        });
        siguienteArtista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) findViewById(R.id.imageView4);
                if(((BitmapDrawable)imageView.getDrawable()).getBitmap() == null){
                    Toast.makeText(getApplicationContext(), "Deve seleccionar una imagen de artista", Toast.LENGTH_LONG).show();
                }else{
                    subidaImagenArtista(imagenDeArtista);

                }
            }
        });
        botonImagenArtista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                new subidaImagenArtista().execute(i);
            }
        });
        finalizarImagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recorrido.setVisibility(View.INVISIBLE);
                perfil.setVisibility(View.VISIBLE);
                finalizarImagenes.setVisibility(View.INVISIBLE);
                imagenLogo.setVisibility(View.VISIBLE);
                textoBusqueda.setVisibility(View.VISIBLE);
                menu.setVisibility(View.VISIBLE);
                separarRecorridos(recorri);
                recogerImagenes();
            }
        });
        guardarRecorrido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer texto = new StringBuffer();
                if(textoTipo.getText().length()==0 || textoCiudadReco.getText().length()==0 || textoGeneroReco.getText().length()==0 || textoInstruReco.getText().length()==0 || textoFechIniReco.getText().length()==0 || textoFechFinReco.getText().length()==0 || resumenReco.getText().length()==0){
                    Toast.makeText(getApplicationContext(), "Deve rellenar todos los campos", Toast.LENGTH_LONG).show();
                }else{
                    texto.append(textoTipo.getText());
                    texto.append('^');
                    texto.append(textoCiudadReco.getText());
                    texto.append('^');
                    texto.append(textoGeneroReco.getText());
                    texto.append('^');
                    texto.append(textoInstruReco.getText());
                    texto.append('^');
                    texto.append(textoFechIniReco.getText());
                    texto.append('^');
                    texto.append(textoFechFinReco.getText());
                    texto.append('^');
                    texto.append(resumenReco.getText());
                    texto.append('º');
                    //guardar texto en la base de datos
                    guardarRecorrido(texto+"");
                    StringBuffer string = new StringBuffer();
                    string.append(recorri);
                    string.append(texto);
                    recorri = string+"";
                }


            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menuLayout.getVisibility() == View.VISIBLE){
                    menuLayout.startAnimation(outToRightAnimation(R.id.menuLayout));
                }else if(menuLayout.getVisibility() == View.INVISIBLE){
                    menuLayout.startAnimation(inFromRightAnimation(R.id.menuLayout));
                }

            }
        });
        cerrarImgAmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(esPortada){
                    Toast.makeText(getApplicationContext(), "No puede eliminar la foto de portada", Toast.LENGTH_LONG).show();
                }else if(esArtista){
                    Toast.makeText(getApplicationContext(), "No puede eliminar la foto de artista", Toast.LENGTH_LONG).show();
                }else{
                    esPortada = false;
                    esArtista = false;
                    portadaSeleccionado = false;
                    artistaSeleccionado = false;
                    tituloNueva.setText("");
                    String url = urlImagenes.get(imagenAmpliada);
                    eliminarImagen(url);
                    layoutImgAmpliado.startAnimation(outToRightAnimation(R.id.layoutImgAmpliado));
                }
            }
        });
        botonTer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imagenNueva == null){
                    layoutSubirImg.startAnimation(outToRightAnimation(R.id.layoutSubirImg));
                }else{
                   if(tituloNueva.getText().length()>2){
                       String random = Commons.generateRandomValue();
                       subidaImagenNueva(imagenNueva,  random, tituloNueva.getText() +"");
                       if(urlImagenes.size()>1){
                           urlImagenes.remove(urlImagenes.size()-1);
                           urlImagenes.add(random + "Nueva.jpg" );
                           urlImagenes.add("upload.png");
                           titulosImagenes.add(tituloNueva.getText()+"");
                       }else{
                           urlImagenes.add(0,random + "Nueva.jpg" );
                           titulosImagenes.add(0, tituloNueva.getText()+"");
                       }
                       imagenNueva = null;
                   }else{
                       Toast.makeText(getApplicationContext(), "Debe añadir un titulo", Toast.LENGTH_LONG).show();
                   }
                }
            }
        });
        botonElegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                new subidaImagen().execute(i);
            }
        });

        portadaSelec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!esPortada){
                    if(portadaSeleccionado){
                        portadaSeleccionado = false;
                        portadaSelec.setImageResource(R.drawable.portada);
                    }else{
                        portadaSeleccionado = true;
                        portadaSelec.setImageResource(R.drawable.portadaon);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "No se puede deseleccionar la imagen de portada", Toast.LENGTH_LONG).show();
                }

            }
        });
        artistaSelec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!esArtista){
                    if(artistaSeleccionado){
                        artistaSeleccionado = false;
                        artistaSelec.setImageResource(R.drawable.artista);
                    }else{
                        artistaSeleccionado = true;
                        artistaSelec.setImageResource(R.drawable.artistaon);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "No se puede deseleccionar la imagen de artista", Toast.LENGTH_LONG).show();
                }

            }
        });
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean controlVariables = false;
                String titulo = tituloImg.getText()+"";
                String tituloAnterior = tituloDeImagen;
                if(!esPortada && portadaSeleccionado){
                    if(!esArtista && !artistaSeleccionado){
                        String urlPortada = "";
                        if(posPortada>=0){
                            urlPortada = "imagenesSubidas/" + urlImagenes.get(posPortada);
                        }
                        String urlImagen = urlImagenes.get(imagenAmpliada);
                        actualizarPortada(urlPortada, urlImagen);
                    }else{
                        controlVariables = true;
                        Toast.makeText(getApplicationContext(), "No se puede seleccionar ambas posibilidades", Toast.LENGTH_LONG).show();
                    }
                }else if(!esArtista && artistaSeleccionado){
                    if(!esPortada && !portadaSeleccionado){
                        String urlArtista = "";
                        if(posArtista>=0){
                            urlArtista = "imagenesSubidas/" + urlImagenes.get(posArtista);
                        }
                        String urlImagen = urlImagenes.get(imagenAmpliada);
                        actualizarArtista(urlArtista, urlImagen);
                    }else{
                        controlVariables = true;
                        Toast.makeText(getApplicationContext(), "No se puede seleccionar ambas posibilidades", Toast.LENGTH_LONG).show();
                    }
                }else if(!titulo.equals(tituloAnterior)){
                    if(tituloImg.getText().length() > 2){
                        actualizarTitulo(urlImagenes.get(imagenAmpliada), tituloImg.getText()+"", imagenAmpliada);
                    }
                }
                if(!controlVariables){
                    esPortada = false;
                    esArtista = false;
                    portadaSeleccionado = false;
                    artistaSeleccionado = false;
                    tituloNueva.setText("");
                    layoutImgAmpliado.startAnimation(outToRightAnimation(R.id.layoutImgAmpliado));
                }
            }
        });

        botonYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = layoutYoutube.getLayoutParams();
                if(params.height != ViewGroup.LayoutParams.WRAP_CONTENT){
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }else{
                    params.height = 0;
                }
                layoutYoutube.setLayoutParams(params);
            }
        });
        botonRedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = layoutRedes.getLayoutParams();
                if(params.height != ViewGroup.LayoutParams.WRAP_CONTENT){
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }else{
                    params.height = 0;
                }
                layoutRedes.setLayoutParams(params);
            }
        });

        okYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enlaceYoutube.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Debe introducir un enlace", Toast.LENGTH_LONG).show();
                }else{
                    String enlace = cambiarEnlaceYoutube(enlaceYoutube.getText()+"");
                    actualizarYoutube(enlace);
                }
            }
        });
        okRedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enlaceRedes.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Debe introducir un enlace", Toast.LENGTH_LONG).show();
                }else {
                    String enlace = enlaceRedes.getText()+"";
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(enlacesRedesSociales);
                    if(enlacesRedesSociales.length()> 5){
                        buffer.append("^");
                    }
                    buffer.append(enlace);
                    actualizarRedes(buffer+"");
                    enlacesRedes.addView(generateRedLayout(enlace));

                }
            }
        });
        blackCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutViewRedSocial.startAnimation(outToRightAnimation(R.id.layoutViewRedSocial));
            }
        });
    }
    protected void onResume(){
        super.onResume();
        recargarDatos();
    }

    private void recogerImagenes(){
        objetosImagenes = SqliteManager.recojerImagenes(db);
        for (int i = 0; i < objetosImagenes.size(); i++) {
            urlImagenes.add(objetosImagenes.get(i).getUrl());
            titulosImagenes.add(objetosImagenes.get(i).getTitulo());
            if(objetosImagenes.get(i).getPortada().equals("true")){
                posPortada = i;
                ImageView foto = (ImageView)findViewById(R.id.fotoPerfil);
                foto.setImageBitmap(SqliteManager.loadImageFromStorage(objetosImagenes.get(i).getUrl()));
            }else if(objetosImagenes.get(i).getArtista().equals("true")){
                posArtista = i;
                ImageView foto = (ImageView)findViewById(R.id.fotoArtista);
                foto.setImageBitmap(SqliteManager.loadImageFromStorage(objetosImagenes.get(i).getUrl()));
            }
        }
        progress.dismiss();
        urlImagenes.add("upload.png");
        gridview.setAdapter(new fotosAdapter(getApplicationContext(), urlImagenes, Plataforma.this));
    }

    private void inicializarYoutube(){
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Commons.YOUTUBE_API_KEY, this);
    }

    private void cargarImagenGrande(final String url, final ImageView imagen){
        Thread thread = new Thread() {
            @Override
            public void run() {
                final Bitmap img = SqliteManager.loadImageFromStorage(url);
                Plataforma.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imagen.setImageBitmap(img);
                    }
                });
            }
        };
        thread.start();
    }

    private String cambiarEnlaceYoutube(String enlace){
        StringBuffer buffer = new StringBuffer();
        boolean control = false;
        for (int i = 0; i < enlace.length(); i++) {
            if(control){
                buffer.append(enlace.charAt(i));
            }
            if(enlace.charAt(i) == '='){
               control = true;
            }
        }
        return buffer+"";
    }




    private void checkImageSelected(int posicion){
        if(posicion == posPortada){
            esPortada = true;
            portadaSelec.setImageResource(R.drawable.portadaon);
            artistaSelec.setImageResource(R.drawable.artista);
        }else if(posicion == posArtista){
            esArtista = true;
            artistaSelec.setImageResource(R.drawable.artistaon);
            portadaSelec.setImageResource(R.drawable.portada);
        }else{
            portadaSelec.setImageResource(R.drawable.portada);
            artistaSelec.setImageResource(R.drawable.artista);
        }
        tituloImg.setText(titulosImagenes.get(posicion));
    }

    private void inicializarTextosPerfilPersonal(){
        nom.setText(nombre);
        ape.setText(apellidos);
        eda.setText(edad);
        fech.setText(fecha);
        corr.setText(correo);
        pa.setText(pais);
        ciu.setText(ciudad);
    }

    private void recogerCampos(){
        botonSubir = (Button) findViewById(R.id.botonsubir);
        botonPersonal = (Button)findViewById(R.id.botonPersonal);
        botonProfesional = (Button)findViewById(R.id.botonProfesional);
        botonRecorrido = (Button)findViewById(R.id.botonRecorrido);
        perfilUsuario = (Button)findViewById(R.id.perfilUsuario);
        galeriaUsuario = (Button)findViewById(R.id.galeriaUsuario);
        videosUsuario = (Button) findViewById(R.id.videosUsuario);
        siguientePortada = (ImageButton)findViewById(R.id.nextPortada);
        siguienteArtista = (ImageButton)findViewById(R.id.nextArtista);
        botonImagenArtista = (Button)findViewById(R.id.botonImagenArtista);
        finalizarImagenes = (ImageButton)findViewById(R.id.finalizarTuto);
        guardarRecorrido = (Button)findViewById(R.id.guardarRecorrido);
        menu = (ImageButton)findViewById(R.id.menuPrincipal);
        imagenLogo = (ImageView)findViewById(R.id.titApp);
        textoBusqueda =(EditText)findViewById(R.id.busquedaArtista);
        cerrarImgAmp = (Button)findViewById(R.id.cerrarImgAmp);
        botonTer = (ImageButton)findViewById(R.id.botonTer);
        botonElegir = (Button)findViewById(R.id.botonElegir);
        nom =(TextView)findViewById(R.id.personalNombre);
        ape = (TextView)findViewById(R.id.personalApe);
        eda = (TextView)findViewById(R.id.personalEdad);
        fech = (TextView)findViewById(R.id.personalFecha);
        corr = (TextView)findViewById(R.id.personalCorreo);
        pa = (TextView)findViewById(R.id.personalPais);
        ciu = (TextView)findViewById(R.id.personalCiudad);
        gridview = (GridView)findViewById(R.id.galeria);
        listaAr = (ListView)findViewById(R.id.listaArPlata);
        layoutPersonal = (RelativeLayout)findViewById(R.id.personal);
        layoutProfesional = (RelativeLayout)findViewById(R.id.profesional);
        layoutRecorrido = (RelativeLayout)findViewById(R.id.textoRecorrido);
        infoPerfil = (RelativeLayout)findViewById(R.id.infoPerfil);
        infoGaleria = (RelativeLayout)findViewById(R.id.infoGaleria);
        infoVideos = (RelativeLayout)findViewById(R.id.infoVideos);
        imagenFondo = (RelativeLayout)findViewById(R.id.imagenFondo);
        imagenArtista = (RelativeLayout)findViewById(R.id.imagenArtista);
        recorrido = (RelativeLayout)findViewById(R.id.recorrido);
        perfil = (RelativeLayout)findViewById(R.id.perfil);
        textoTipo = (TextView)findViewById(R.id.textoTipo);
        textoCiudadReco = (TextView)findViewById(R.id.textoCiudadReco);
        textoGeneroReco = (TextView)findViewById(R.id.textoGeneroReco);
        textoInstruReco = (TextView)findViewById(R.id.textoInstruReco);
        textoFechIniReco = (TextView)findViewById(R.id.textoFechIniReco);
        textoFechFinReco = (TextView)findViewById(R.id.textoFechFinReco);
        resumenReco = (TextView)findViewById(R.id.resumenReco);
        menuLayout = (RelativeLayout)findViewById(R.id.menuLayout);
        layoutImgAmpliado =(RelativeLayout)findViewById(R.id.layoutImgAmpliado);
        layoutSubirImg =(RelativeLayout)findViewById(R.id.layoutSubirImg);
        nombreArtisticoView = (TextView)findViewById(R.id.profeNombre);
        generosView = (TextView)findViewById(R.id.profeGenero);
        instrumentosView = (TextView)findViewById(R.id.profeInstru);
        paginaWebView = (TextView)findViewById(R.id.profeWeb);
        scrollRecorrido = (ScrollView)findViewById(R.id.scrollRecorrido);
        tituloNueva = (EditText)findViewById(R.id.nuevoTitulo);
        portadaSelec = (ImageView)findViewById(R.id.imgPortada);
        artistaSelec = (ImageView)findViewById(R.id.imgArtista);
        tituloImg = (EditText)findViewById(R.id.tituloImg);
        cross = (ImageButton)findViewById(R.id.cerrarLayout);
        layoutFormulario = (RelativeLayout)findViewById(R.id.layoutFormulario);
        profePrecio = (TextView)findViewById(R.id.profePrecio);
        generoCaso = (TextView)findViewById(R.id.textGeneroBar);
        instruCaso = (TextView)findViewById(R.id.textPagWebBar);
        botonYoutube = (Button)findViewById(R.id.botonYoutube);
        botonRedes = (Button)findViewById(R.id.botonSociales);
        layoutYoutube = (RelativeLayout)findViewById(R.id.layoutYoutube);
        layoutRedes = (RelativeLayout)findViewById(R.id.layoutSociales);
        enlaceYoutube = (EditText)findViewById(R.id.textoEnlaceYoutube);
        okYoutube = (ImageButton)findViewById(R.id.botonOkEnlace);
        enlaceRedes = (EditText)findViewById(R.id.textoEnlaceRedes);
        okRedes = (ImageButton)findViewById(R.id.botonEnlaceRedes);
        videosYoutube = (RelativeLayout)findViewById(R.id.videosYoutube);
        enlacesRedes = (LinearLayout)findViewById(R.id.layoutEnlacesRedes);
        redSocial = (WebView)findViewById(R.id.viewRedSocial);
        layoutViewRedSocial = (RelativeLayout)findViewById(R.id.layoutViewRedSocial);
        blackCross = (ImageView)findViewById(R.id.blackCross);
        indicador = (RelativeLayout)findViewById(R.id.indicadorPlataforma);

    }

    private void inicializamosMenuDespleagable(){
        menuDesplegable.add("Busqueda Local");
        menuDesplegable.add("Busqueda Global");
        menuDesplegable.add("Mensajes");
        menuDesplegable.add("Editar Perfil");
        menuDesplegable.add("Cerrar Sesion");
        adapter = new menuAdapter(menuDesplegable, this);
        list = (ListView)findViewById(R.id.menuDesple);
        list.setAdapter(adapter);
        list.setBackgroundColor(Color.WHITE);
        list.getBackground().setAlpha(200);
    }
    

    private boolean checkImgPortada(){
        SharedPreferences prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        return prefs.getBoolean("imgPortada", false);
    }

    private boolean checkImgArtista(){
        SharedPreferences prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        return prefs.getBoolean("imgArtista", false);
    }

    private void applyImgPortada(){
        SharedPreferences prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("imgPortada", true).apply();
    }

    private void applyImgArtista(){
        SharedPreferences prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("imgArtista", true).apply();
    }

    private void recogerIntent(){
        if(getIntent() != null){
            Intent intent = getIntent();
            nombre = intent.getStringExtra("nombre");
            apellidos = intent.getStringExtra("apellidos");
            edad = intent.getStringExtra("edad");
            fecha = intent.getStringExtra("fecha");
            correo = intent.getStringExtra("correo");
            pais = intent.getStringExtra("pais");
            ciudad = intent.getStringExtra("direccion");
            usuario = intent.getStringExtra("usuario");
            recorri = intent.getStringExtra("recorrido");
            nombreBar = intent.getStringExtra("precio");
            instru = intent.getStringExtra("instru");
            generos = intent.getStringExtra("generos");
            nombreArtista = intent.getStringExtra("nombreArtista");
            paginaWeb = intent.getStringExtra("paginaWeb");
            rol = intent.getStringExtra("rol");
            youtubeVideo = intent.getStringExtra("youtube");
            enlacesRedesSociales = intent.getStringExtra("redes");
        }
    }

    private void inicializarBaseDeDatos(){
        usdbh = new SqliteManager(this, "artistas", null, 1);
        db = usdbh.getWritableDatabase();
    }

    private void inicializarDatosProfesional(){
        nombreArtisticoView.setText(nombreArtista);
        generosView.setText(generos);
        instrumentosView.setText(instru);
        paginaWebView.setText(paginaWeb);
        profePrecio.setText(nombreBar);
    }

    private void setearLayouts(){
        //iniciamos los tamaños de los linearLayout
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.personal);
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        params.height = 0;
        layout.setLayoutParams(params);

        layout = (RelativeLayout)findViewById(R.id.profesional);
        params = layout.getLayoutParams();
        params.height = 0;
        layout.setLayoutParams(params);

        layout = (RelativeLayout)findViewById(R.id.textoRecorrido);
        params = layout.getLayoutParams();
        params.height = 0;
        layout.setLayoutParams(params);
    }

    private Bitmap rotateImage(Bitmap bitmap, String filePath){
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
        int rotationAngle = 0;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
        // Rotate Bitmap
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return rotatedBitmap;
    }

    private void saveUploadImg(){
        Bitmap bit = BitmapFactory.decodeResource(getResources(), R.drawable.upload);
        SqliteManager.saveUploadImage(getApplicationContext(),"uploadMini.png", bit);
    }

    public void checkearImagenesPerfil(){
        if(!checkImgPortada()){
            imagenFondo.setVisibility(View.VISIBLE);
            siguientePortada.setVisibility(View.VISIBLE);
            menu.setVisibility(View.INVISIBLE);
            imagenLogo.setVisibility(View.INVISIBLE);
            textoBusqueda.setVisibility(View.INVISIBLE);
            saveUploadImg();
        }else if(!checkImgArtista()){
            imagenArtista.setVisibility(View.VISIBLE);
            siguienteArtista.setVisibility(View.VISIBLE);
            menu.setVisibility(View.INVISIBLE);
            imagenLogo.setVisibility(View.INVISIBLE);
            textoBusqueda.setVisibility(View.INVISIBLE);
        }else if(recorri.length()<2){
            recorrido.setVisibility(View.VISIBLE);
            finalizarImagenes.setVisibility(View.VISIBLE);
            menu.setVisibility(View.INVISIBLE);
            imagenLogo.setVisibility(View.INVISIBLE);
            textoBusqueda.setVisibility(View.INVISIBLE);
            finalizarImagenes.setEnabled(false);
        }else{
            perfil.setVisibility(View.VISIBLE);
            menu.setVisibility(View.VISIBLE);
            imagenLogo.setVisibility(View.VISIBLE);
            textoBusqueda.setVisibility(View.VISIBLE);
        }
    }

    private void inicializarRedesSociales(){
        ArrayList<String> redes = separarRedesSociales(enlacesRedesSociales);
        for (int i = 0; i < redes.size(); i++) {
            enlacesRedes.addView(generateRedLayout(redes.get(i)));
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

    private String juntarRedesSociales(ArrayList<String> redes){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < redes.size(); i++) {
            if(i < redes.size()-1){
                buffer.append(redes.get(i) + "^");
            }else{
                buffer.append(redes.get(i));
            }
        }
        return buffer+"";
    }

    private RelativeLayout generateRedLayout(String red){
        RelativeLayout redLayout = new RelativeLayout(getApplicationContext());
        ImageView redImage = new ImageView(getApplicationContext());
        final TextView enlaceRed = new TextView(getApplicationContext());

        enlaceRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutViewRedSocial.setVisibility(View.VISIBLE);
                inFromRightAnimation(R.id.layoutViewRedSocial);
                redSocial.setWebViewClient(new WebViewClient());
                redSocial.loadUrl("http://"+enlaceRed.getText()+"");

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

    public void añadirCamposRecorrido(String texto, LinearLayout viewPrincipal, int contador){
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
        TextView ResumenTexto = new TextView(getApplicationContext());

        //aplicar estilo a los componentes
        RelativeLayout.LayoutParams paramsSubView = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsSubView.addRule(RelativeLayout.CENTER_IN_PARENT);
        paramsSubView.setMargins(0,Commons.dpToPx(20, this), 0, 0);
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
        ResumenTexto.setLayoutParams(ResumenTextoCampoParams);
        ResumenTexto.setTextColor(Color.BLACK);

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
        subview.addView(ResumenTexto);

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
        ResumenTexto.setText(array.get(6));
    }

    public void separarRecorridos(final String recorrido){
        scrollRecorrido.removeAllViews();
        Thread thread = new Thread() {
            @Override
            public void run() {
                final LinearLayout viewPrincipal = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams paramsPrincipal = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                viewPrincipal.setOrientation(LinearLayout.VERTICAL);
                viewPrincipal.setLayoutParams(paramsPrincipal);

                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < recorrido.length(); i++) {
                    char c = recorrido.charAt(i);
                    if(c != 'º'){
                        buffer.append(c);
                    }else{
                        añadirCamposRecorrido(buffer+"", viewPrincipal, i);
                        buffer.delete(0,buffer.length());
                    }
                }
                Plataforma.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        scrollRecorrido.addView(viewPrincipal);
                    }
                });
            }
        };
        thread.start();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            if(BitmapFactory.decodeFile(picturePath, options) != null){
                Bitmap imageBitmap = rotateImage(BitmapFactory.decodeFile(picturePath, options), picturePath);
                Bitmap imagenGrande = Commons.redimensionarImagen(imageBitmap, 1200);
                if(layoutSubirImg.getVisibility()==View.VISIBLE){
                    ImageView imageView = (ImageView) findViewById(R.id.imageSubida);
                    imagenNueva = imagenGrande;
                    imageView.setImageBitmap(imagenGrande);
                    urlImgNueva = picturePath;
                }else{
                    if (imagenFondo.getVisibility() == View.VISIBLE) {
                        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
                        imagenDePortada = imagenGrande;
                        imageView.setImageBitmap(imagenGrande);
                    } else if (imagenArtista.getVisibility() == View.VISIBLE) {
                        ImageView imageView = (ImageView) findViewById(R.id.imageView4);
                        imagenDeArtista = imagenGrande;
                        imageView.setImageBitmap(imagenGrande);
                    }
                }
            }else{
                Toast.makeText(getApplicationContext(), "Error al recoger la imagen por favor seleccione otra imagen", Toast.LENGTH_LONG).show();
            }

        }
    }

    public void consultaArtistas(){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Cargando Artistas",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap<String, String>();
            String url = Constants.URL_LOCALHOST + "formularioArtistas.php";
            params.put("usu", usuario);
            /*JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                if(response.length()>0){
                                    for (int i = 0; i < response.length(); i++) {
                                        JSONObject json = response.getJSONObject(i);
                                        artistaItem artista = new artistaItem();
                                        String direccion = (String) json.get("direccion");
                                        String nombre = (String) json.get("nombreArtista");
                                        String usuari = (String) json.get("usuario");
                                        String rol = (String) json.get("rol");
                                        String url = (String) json.get("url");
                                        artista.setDireccion(direccion);
                                        artista.setNombreAr(nombre);
                                        artista.setUsuario(usuari);
                                        artista.setRol(rol);
                                        artista.setLink(URL_LOCALHOST + url);
                                        String usuarioImpro = artista.getUsuario();
                                        if(!usuarioImpro.equals(usuario)){
                                            artistaFormulario.add(artista);
                                        }
                                    }
                                    if(artistaFormulario.size()>0){
                                        adapterFormulario = new ArtistasAdapter(getApplicationContext(), artistaFormulario, plataforma.this);
                                        listaAr.setAdapter(adapterFormulario);
                                        layoutFormulario.setVisibility(View.VISIBLE);
                                    }
                                    progress.dismiss();
                                }else{
                                    progress.dismiss();
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
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, no se han podido cargar las imagenes del perfil", Toast.LENGTH_LONG).show();
        }

    }

    private void actualizarPortada(String urlPortada, final String urlImagen){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Actualizando portada",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            String endPoint = Constants.URL_LOCALHOST + "cambiarPortada.php";
            String link = "imagenesSubidas/"+urlImagen;
            final String linkImgPortada = Commons.eliminarRuta(urlPortada);
            params.put("usu", usuario);
            params.put("urlImagen", link);
            params.put("urlPortada", urlPortada);
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,endPoint, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    if(SqliteManager.setPortada(urlImagen, linkImgPortada, db)){
                                        Thread thread = new Thread() {
                                            @Override
                                            public void run() {
                                                final Bitmap img = SqliteManager.loadImageFromStorage(urlImagen);
                                                plataforma.this.runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                    ImageView foto = (ImageView)findViewById(R.id.fotoPerfil);
                                                    foto.setImageBitmap(img);
                                                    Toast.makeText(getApplicationContext(), "Imagen Actualizada", Toast.LENGTH_SHORT).show();
                                                    posPortada = imagenAmpliada;
                                                    progress.dismiss();
                                                    }
                                                });
                                            }
                                        };
                                        thread.start();
                                    }else{
                                        progress.dismiss();
                                        Toast.makeText(getApplicationContext(), "Error al actualizar la imagen", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    progress.dismiss();
                                    Toast.makeText(getApplicationContext(), "Error al actualizar la imagen", Toast.LENGTH_SHORT).show();
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
    private void actualizarArtista(String urlArtista, final String urlImagen){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Actualizando imagen",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            String endPoint = Constants.URL_LOCALHOST + "cambiarArtista.php";
            String link = "imagenesSubidas/"+urlImagen;
            final String linkImgArtista = Commons.eliminarRuta(urlArtista);
            params.put("usu", usuario);
            params.put("urlImagen", link);
            params.put("urlArtista", urlArtista);
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,endPoint, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    if(SqliteManager.setArtista(urlImagen, linkImgArtista, db)){
                                        Thread thread = new Thread() {
                                            @Override
                                            public void run() {
                                                final Bitmap img = SqliteManager.loadImageFromStorage(urlImagen);
                                                plataforma.this.runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ImageView foto = (ImageView)findViewById(R.id.fotoArtista);
                                                        foto.setImageBitmap(img);
                                                        Toast.makeText(getApplicationContext(), "Imagen Actualizada", Toast.LENGTH_SHORT).show();
                                                        posArtista = imagenAmpliada;
                                                        progress.dismiss();
                                                    }
                                                });
                                            }
                                        };
                                        thread.start();
                                    }else{
                                        progress.dismiss();
                                        Toast.makeText(getApplicationContext(), "Error al actualizar la imagen", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al actualizar lod datos, intentelo de nuevo", Toast.LENGTH_SHORT).show();
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
    private void actualizarYoutube( final String enlace) {
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Actualizando enlace",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            String endPoint = Constants.URL_LOCALHOST + "enlaceYoutube.php";
            params.put("usu", usuario);
            params.put("enlace", enlace);
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, endPoint, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    youtubePlayer.cueVideo(enlace);
                                    Toast.makeText(getApplicationContext(), "Se ha actualizado el enlace", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al actualizar el enlace", Toast.LENGTH_SHORT).show();
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
    private void actualizarRedes( final String enlace) {
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Actualizando redes",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            String endPoint = Constants.URL_LOCALHOST + "enlaceRedes.php";
            params.put("usu", usuario);
            params.put("enlace", enlace);
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, endPoint, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(), "Enlace añadido", Toast.LENGTH_SHORT).show();
                                    enlaceRedes.setText("");
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al añadir el enlace, intentelo de nuevo", Toast.LENGTH_SHORT).show();
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

    private void recargarDatos(){
        if(Commons.isOnline(this)){
            Map<String, String> params = new HashMap();
            String endPoint = Constants.URL_LOCALHOST + "recargarDatos.php";
            params.put("usuario", usuario);
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,endPoint, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    nombre = (String) response.get("nombre");
                                    apellidos = (String) response.get("ape");
                                    edad = (String) response.get("edad");
                                    fecha = (String) response.get("fecha");
                                    correo = (String) response.get("correo");
                                    pais = (String) response.get("pais");
                                    ciudad = (String) response.get("direccion");
                                    usuario = (String) response.get("usuario");
                                    recorri = (String) response.get("recorrido");
                                    nombreBar = (String) response.get("precio");
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
                                    separarRecorridos(recorri);
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

    private void actualizarTitulo(final String url, final String titulo, final int pos){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Actualizando datos",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            String endPoint = Constants.URL_LOCALHOST + "modificarImagen.php";
            String link = "imagenesSubidas/"+url;
            params.put("usu", usuario);
            params.put("url", link);
            params.put("titulo", titulo);
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,endPoint, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    if(SqliteManager.editarTitulo(url, titulo, db)){
                                        titulosImagenes.set(pos, titulo);
                                        Toast.makeText(getApplicationContext(), "Imagen Actualizada", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Error al actualizar la imagen, intentelo de nuevo", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al actualizar la imagen, intentelo de nuevo", Toast.LENGTH_SHORT).show();
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
    private void eliminarImagen(final String url){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Eliminando imagen",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            String endPoint = Constants.URL_LOCALHOST + "eliminarArchivo.php";
            String link = "imagenesSubidas/"+url;
            params.put("usu", usuario);
            params.put("url", link);
            params.put("urlPeque", Commons.getImagenMini(link));
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,endPoint, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Thread thread = new Thread() {
                                        @Override
                                        public void run() {
                                            if(SqliteManager.eliminarImagen(url, db)){
                                                urlImagenes.remove(imagenAmpliada);
                                            }
                                            plataforma.this.runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    gridview.setAdapter(new fotosAdapter(getApplicationContext(), urlImagenes, plataforma.this));
                                                    Toast.makeText(getApplicationContext(), "Imagen eliminada", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    };
                                    thread.start();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al eliminar la imagen", Toast.LENGTH_SHORT).show();
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


    public void subidaImagenNueva(final Bitmap imagen, final String numero, final String titulo){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Guardando imagen",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap<String, String>();

            //imagen Grande
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            imagen.compress(Bitmap.CompressFormat.JPEG, 80, out);
            byte[] byte_arr = out.toByteArray();
            String img = Base64.encodeToString(byte_arr, 0);

            //imagen pequeña
            Bitmap imgPeque = Commons.redimensionarImagen(imagen, 400);
            ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            imgPeque.compress(Bitmap.CompressFormat.JPEG, 80, out2);
            byte[] byte_arr2 = out2.toByteArray();
            String img2 = Base64.encodeToString(byte_arr2, 0);

            String url = Constants.URL_LOCALHOST + "imagenNueva.php";
            params.put("imagen", img);
            params.put("imagenPeque", img2);
            params.put("usuario", usuario);
            params.put("url", "imagenesSubidas/"  + numero + "Nueva.jpg");
            params.put("urlPeque", "imagenesSubidas/"  + numero + "NuevaMini.jpg");
            params.put("titulo", titulo);
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int) response.get("valor") == 200){
                                    layoutSubirImg.startAnimation(outToRightAnimation(R.id.layoutSubirImg));
                                    Toast.makeText(getApplicationContext(), "Imagen Subida", Toast.LENGTH_SHORT).show();
                                    ImageView imageView = (ImageView) findViewById(R.id.imageSubida);
                                    final ImagenObject imag = new ImagenObject();
                                    imag.setUrl(numero + "Nueva.jpg");
                                    imag.setTitulo(titulo);
                                    imag.setPortada("false");
                                    imag.setArtista("false");
                                    Thread thread = new Thread() {
                                        @Override
                                        public void run() {
                                            if(SqliteManager.anadirImagen(imag,db,getApplicationContext(),imagen)){
                                                plataforma.this.runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        progress.dismiss();
                                                        gridview.setAdapter(new fotosAdapter(getApplicationContext(), urlImagenes, plataforma.this));
                                                    }
                                                });
                                            }else{
                                                progress.dismiss();
                                                Toast.makeText(getApplicationContext(), "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    };
                                    thread.start();
                                    imageView.setImageBitmap(null);
                                }else{
                                    progress.dismiss();
                                    Toast.makeText(getApplicationContext(), "Error al subir la imagen, intentelo de nuevo", Toast.LENGTH_SHORT).show();
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


    public void subidaImagenPortada(final Bitmap imagen){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Guardando imagen",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap<String, String>();

            //imagen grande
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            imagen.compress(Bitmap.CompressFormat.JPEG, 80, out);
            byte[] byte_arr = out.toByteArray();
            String img = Base64.encodeToString(byte_arr, 0);

            //imagen pequeña
            Bitmap imgPeque = Commons.redimensionarImagen(imagen, 400);
            ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            imgPeque.compress(Bitmap.CompressFormat.JPEG, 80, out2);
            byte[] byte_arr2 = out2.toByteArray();
            String img2 = Base64.encodeToString(byte_arr2, 0);

            String url = Constants.URL_LOCALHOST + "imagenPortada.php";
            params.put("imagen", img);
            params.put("imagenPeque", img2);
            params.put("usuario", usuario);
            params.put("url", "imagenesSubidas/" + usuario + "Portada.jpg");
            params.put("urlPeque", "imagenesSubidas/" + usuario + "PortadaMini.jpg");
            params.put("urlPhp", "imagenesSubidas/" + usuario + "Portada.jpg");
            params.put("urlPhpPeque", "imagenesSubidas/" + usuario + "PortadaMini.jpg");
            params.put("titulo", "Imagen Portada");
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(), "Imagen Cargada", Toast.LENGTH_SHORT).show();
                                    applyImgPortada();
                                    siguientePortada.setVisibility(View.INVISIBLE);
                                    imagenFondo.setVisibility(View.INVISIBLE);
                                    siguienteArtista.setVisibility(View.VISIBLE);
                                    imagenArtista.setVisibility(View.VISIBLE);
                                    imagenDePortada = null;
                                    final ImagenObject imag = new ImagenObject();
                                    imag.setUrl(usuario + "Portada.jpg");
                                    imag.setTitulo("Imagen Portada");
                                    imag.setPortada("true");
                                    imag.setArtista("false");
                                    Thread thread = new Thread() {
                                        @Override
                                        public void run() {
                                            if(SqliteManager.anadirImagen(imag,db,getApplicationContext(),imagen)){
                                                final Bitmap bitmap = SqliteManager.loadImageFromStorage(Commons.getImagenMini(imag.getUrl()));
                                                plataforma.this.runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        progress.dismiss();
                                                        ImageView foto = (ImageView)findViewById(R.id.fotoPerfil);
                                                        foto.setImageBitmap(bitmap);
                                                    }
                                                });
                                            }else{
                                                progress.dismiss();
                                                Toast.makeText(getApplicationContext(), "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    };
                                    thread.start();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al subir la imagen, intentelo de nuevo", Toast.LENGTH_SHORT).show();
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

    public void subidaImagenArtista(final Bitmap imagen){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Guardando imagen",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap<String, String>();

            //imagen grande
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            imagen.compress(Bitmap.CompressFormat.JPEG, 80, out);
            byte[] byte_arr = out.toByteArray();
            String img = Base64.encodeToString(byte_arr, 0);

            //imagen pequeña
            Bitmap imgPeque = Commons.redimensionarImagen(imagen, 400);
            ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            imgPeque.compress(Bitmap.CompressFormat.JPEG, 80, out2);
            byte[] byte_arr2 = out2.toByteArray();
            String img2 = Base64.encodeToString(byte_arr2, 0);

            String url = Constants.URL_LOCALHOST + "imagenArtista.php";
            params.put("imagen", img);
            params.put("imagenPeque", img2);
            params.put("usuario", usuario);
            params.put("url", "imagenesSubidas/" + usuario+ "Artista.jpg");
            params.put("urlPhp", "imagenesSubidas/" + usuario+ "Artista.jpg");
            params.put("urlPhpPeque", "imagenesSubidas/" + usuario+ "ArtistaMini.jpg");
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(), "Imagen Subida", Toast.LENGTH_SHORT).show();
                                    //imgArtista = "imagenesSubidas/" + usuario + "Portada.jpg";
                                    applyImgArtista();
                                    siguienteArtista.setVisibility(View.INVISIBLE);
                                    imagenArtista.setVisibility(View.INVISIBLE);
                                    finalizarImagenes.setVisibility(View.VISIBLE);
                                    recorrido.setVisibility(View.VISIBLE);
                                    final ImagenObject imag = new ImagenObject();
                                    imag.setUrl(usuario + "Artista.jpg");
                                    imag.setTitulo("Imagen Artista");
                                    imag.setPortada("false");
                                    imag.setArtista("true");
                                    Thread thread = new Thread() {
                                        @Override
                                        public void run() {
                                            if(SqliteManager.anadirImagen(imag,db,getApplicationContext(),imagen)){
                                                final Bitmap bitmap = SqliteManager.loadImageFromStorage(Commons.getImagenMini(imag.getUrl()));
                                                plataforma.this.runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        progress.dismiss();
                                                        ImageView foto = (ImageView)findViewById(R.id.fotoArtista);
                                                        foto.setImageBitmap(bitmap);
                                                        finalizarImagenes.setEnabled(false);
                                                    }
                                                });
                                            }else{
                                                progress.dismiss();
                                                Toast.makeText(getApplicationContext(), "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    };
                                    thread.start();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al subir la imagen, intentelo de nuevo", Toast.LENGTH_SHORT).show();
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

    public void guardarRecorrido(String recorrido){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Actualizando recorrido",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap<String, String>();
            String url = Constants.URL_LOCALHOST + "guardarRecorrido.php";
            params.put("usu",usuario);
            params.put("recorrido",recorrido);
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(), "Recorrido añadido", Toast.LENGTH_SHORT).show();
                                    textoCiudadReco.setText("");
                                    textoGeneroReco.setText("");
                                    textoInstruReco.setText("");
                                    textoFechIniReco.setText("");
                                    textoFechFinReco.setText("");
                                    resumenReco.setText("");
                                    finalizarImagenes.setEnabled(true);
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al añadir el recorrido, intentelo de nuevo", Toast.LENGTH_SHORT).show();
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
    private class subidaImagen extends AsyncTask<Intent, Integer, Void>{

        @Override
        protected Void doInBackground(Intent... params) {
            startActivityForResult(params[0], 1);
            return null;
        }
    }
    private class subidaImagenArtista extends AsyncTask<Intent, Integer, Void>{

        @Override
        protected Void doInBackground(Intent... params) {
            startActivityForResult(params[0], 1);
            return null;
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            youtubePlayer = player;
            if(youtubeVideo.length() > 2){
                player.cueVideo(youtubeVideo);
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
}
