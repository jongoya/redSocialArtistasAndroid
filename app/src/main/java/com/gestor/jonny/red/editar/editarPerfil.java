package com.gestor.jonny.red.editar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gestor.jonny.red.R;
import com.gestor.jonny.red.commons.Commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class editarPerfil extends AppCompatActivity {

    public static String URL_LOCALHOST = "http://www.djmrbug.com/artistas/";
    ObjetoArtista artista = new ObjetoArtista();
    Button personalEditar;
    Button profeEditar;
    Button recoEditar;
    Button botCredenciales;
    Button guardarPersoEditar;
    Button guardarProfeEditar;
    Button botCredenEditar;
    Button recorridoActual;
    Button añadirRecorrido;
    Button guarNuevoReco;
    ScrollView scrollRecorrido;
    EditText nombreEditar;
    EditText apeEditar;
    EditText edadEditar;
    EditText fechaEditar;
    EditText correoEditar;
    EditText paisEditar;
    EditText ciudadEditar;
    RelativeLayout layPersoEdit;
    RelativeLayout layProfeEdit;
    RelativeLayout layRecoEdit;
    RelativeLayout layoCredenciales;
    EditText nombreArEditar;
    EditText generoEditar;
    EditText instruEditar;
    EditText pagWebEditar;
    EditText usuEditar;
    EditText contraEditar;
    EditText claveEditar;
    RelativeLayout recoActual;
    RelativeLayout anadirReco;
    EditText tipoEdit;
    EditText generoEdit;
    EditText fechaIniEdit;
    EditText ciudadEdit;
    EditText instruEdit;
    EditText fechaFinEdit;
    EditText comentariosEdit;
    EditText precioEditar;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_perfil);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        recogerIntent();

        recogerCampos();

        separarRecorridos(artista.getRecorrido());

        rellenarCampos(artista);
        
        seleccionarRol(Integer.valueOf(artista.getRol()));

        personalEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = layPersoEdit.getLayoutParams();
                if(params.height != ViewGroup.LayoutParams.WRAP_CONTENT){
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }else{
                    params.height = 0;
                }
                layPersoEdit.setLayoutParams(params);
            }
        });

        profeEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = layProfeEdit.getLayoutParams();
                if(params.height != ViewGroup.LayoutParams.WRAP_CONTENT){
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }else{
                    params.height = 0;
                }
                layProfeEdit.setLayoutParams(params);
            }
        });

        recoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = layRecoEdit.getLayoutParams();
                if(params.height != ViewGroup.LayoutParams.WRAP_CONTENT){
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }else{
                    params.height = 0;
                }
                layRecoEdit.setLayoutParams(params);
            }
        });

        botCredenciales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = layoCredenciales.getLayoutParams();
                if(params.height != ViewGroup.LayoutParams.WRAP_CONTENT){
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }else{
                    params.height = 0;
                }
                layoCredenciales.setLayoutParams(params);
            }
        });

        guardarPersoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = nombreEditar.getText()+"";
                String apellid = apeEditar.getText()+"";
                String ed = edadEditar.getText()+"";
                String fech = fechaEditar.getText()+"";
                String cor = correoEditar.getText()+"";
                String pa = paisEditar.getText()+"";
                String ciu = ciudadEditar.getText()+"";
                if(nom.length()>1 && apellid.length()>1 && ed.length()>1 && fech.length()>1 && cor.length()>1 && pa.length()>1 && ciu.length()>1){
                    if(nom.equals(artista.getNombre()) && apellid.equals(artista.getApellidos()) && ed.equals(artista.getEdad()) && fech.equals(artista.getFecha()) && cor.equals(artista.getCorreo()) && pa.equals(artista.getPais()) && ciu.equals(artista.getCiudad())){
                        Toast.makeText(getApplicationContext(), "Deve modificar algun valor", Toast.LENGTH_SHORT).show();
                    }else{
                        ArrayList<String> array = new ArrayList();
                        array.add(nom);
                        array.add(apellid);
                        array.add(ed);
                        array.add(fech);
                        array.add(cor);
                        array.add(pa);
                        array.add(ciu);
                        actualizarDatosPerso(array);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        guardarProfeEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomAr = nombreArEditar.getText()+"";
                String gnAr = generoEditar.getText()+"";
                String instAr = instruEditar.getText()+"";
                String pgWebAr = pagWebEditar.getText()+"";
                String precio = precioEditar.getText()+"";
                if(nomAr.length()>1 && gnAr.length()>1 && instAr.length()>1 && pgWebAr.length()>1 && precio.length()>1) {
                    if (nomAr.equals(artista.getNombreArtista()) && gnAr.equals(artista.getGeneros()) && instAr.equals(artista.getInstru()) && pgWebAr.equals(artista.getPaginaWeb()) && precio.equals(artista.getPrecio())) {
                        Toast.makeText(getApplicationContext(), "Debe modificar algun valor", Toast.LENGTH_SHORT).show();
                    } else {
                        ArrayList<String> array = new ArrayList();
                        array.add(nomAr);
                        array.add(gnAr);
                        array.add(instAr);
                        array.add(pgWebAr);
                        array.add(precio);
                        actualizarDatosProfe(array);
                    }
                }
            }
        });

        botCredenEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuar = usuEditar.getText()+"";
                String contr = contraEditar.getText()+"";
                String cl = claveEditar.getText()+"";
                if(usuar.length()>1 && contr.length()>1 && cl.length()>1) {
                    if (usuar.equals(artista.getUsuario()) && contr.equals(artista.getContraseña()) && cl.equals(artista.getClave())) {
                        Toast.makeText(getApplicationContext(), "Deve modificar algun valor", Toast.LENGTH_SHORT).show();
                    } else {
                        ArrayList<String> array = new ArrayList<String>();
                        array.add(usuar);
                        array.add(contr);
                        array.add(cl);
                        actualizarCredenciales(array);
                    }
                }
            }
        });

        recorridoActual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = recoActual.getLayoutParams();
                ViewGroup.LayoutParams params2 = anadirReco.getLayoutParams();
                if(params.height != ViewGroup.LayoutParams.WRAP_CONTENT){
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    params2.height=0;
                }else{
                    params.height = 0;
                }
                recoActual.setLayoutParams(params);
                anadirReco.setLayoutParams(params2);
            }
        });
        añadirRecorrido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = recoActual.getLayoutParams();
                ViewGroup.LayoutParams params2 = anadirReco.getLayoutParams();
                if(params2.height != ViewGroup.LayoutParams.WRAP_CONTENT){
                    params2.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    params.height=0;
                }else{
                    params2.height = 0;
                }
                recoActual.setLayoutParams(params);
                anadirReco.setLayoutParams(params2);
            }
        });
        guarNuevoReco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tip = tipoEdit.getText()+"";
                String gen = generoEdit.getText()+"";
                String fechIn = fechaIniEdit.getText()+"";
                String ciu = ciudadEdit.getText()+"";
                String ins = instruEdit.getText()+"";
                String fechFin = fechaFinEdit.getText()+"";
                String comen = comentariosEdit+"";
                if(tip.length()>1 && gen.length()>1 && fechIn.length()>1 &&ciu.length()>1 &&ins.length()>1 &&fechFin.length()>1 &&comen.length()>1){
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(tip+"^");
                    buffer.append(ciu+"^");
                    buffer.append(gen+"^");
                    buffer.append(ins+"^");
                    buffer.append(fechIn+"^");
                    buffer.append(fechFin+"º");
                    guardarRecorrido(buffer+"");
                }else{
                    Toast.makeText(getApplicationContext(), "Debe modificar uno de los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void recogerCampos(){
        personalEditar = (Button)findViewById(R.id.personalEditar);
        profeEditar = (Button)findViewById(R.id.profeEditar);
        recoEditar = (Button)findViewById(R.id.recoEditar);
        botCredenciales = (Button)findViewById(R.id.botCredenciales);
        guardarPersoEditar = (Button)findViewById(R.id.guardarPersoEditar);
        guardarProfeEditar = (Button)findViewById(R.id.guardarProfeEditar);
        botCredenEditar = (Button)findViewById(R.id.botCredenEditar);
        recorridoActual = (Button)findViewById(R.id.recorridoActual);
        añadirRecorrido = (Button)findViewById(R.id.añadirRecorrido);
        guarNuevoReco = (Button)findViewById(R.id.guarNuevoReco);
        scrollRecorrido = (ScrollView)findViewById(R.id.scrollEditar);
        nombreEditar =(EditText)findViewById(R.id.nombreEditar);
        apeEditar =(EditText)findViewById(R.id.apeEditar);
        edadEditar =(EditText)findViewById(R.id.edadEditar);
        fechaEditar =(EditText)findViewById(R.id.fechaEditar);
        correoEditar =(EditText)findViewById(R.id.correoEditar);
        paisEditar =(EditText)findViewById(R.id.paisEditar);
        ciudadEditar =(EditText)findViewById(R.id.ciudadEditar);
        layPersoEdit = (RelativeLayout)findViewById(R.id.layPersoEdit);
        layProfeEdit = (RelativeLayout)findViewById(R.id.layProfeEdit);
        layRecoEdit = (RelativeLayout)findViewById(R.id.layRecoEdit);
        layoCredenciales = (RelativeLayout)findViewById(R.id.layoCredenciales);
        nombreArEditar =(EditText)findViewById(R.id.nombreArEditar);
        generoEditar =(EditText)findViewById(R.id.generoEditar);
        instruEditar =(EditText)findViewById(R.id.instruEditar);
        pagWebEditar =(EditText)findViewById(R.id.pagWebEditar);
        usuEditar =(EditText)findViewById(R.id.usuEditar);
        contraEditar =(EditText)findViewById(R.id.contraEditar);
        claveEditar =(EditText)findViewById(R.id.claveEditar);
        recoActual = (RelativeLayout)findViewById(R.id.recoActual);
        anadirReco = (RelativeLayout)findViewById(R.id.anadirReco);
        tipoEdit = (EditText)findViewById(R.id.tipoEdit);
        generoEdit = (EditText)findViewById(R.id.generoEdit);
        fechaIniEdit = (EditText)findViewById(R.id.fechaIniEdit);
        ciudadEdit = (EditText)findViewById(R.id.ciudadEdit);
        instruEdit = (EditText)findViewById(R.id.instruEdit);
        fechaFinEdit = (EditText)findViewById(R.id.fechaFinEdit);
        comentariosEdit = (EditText)findViewById(R.id.comentariosEdit);
        precioEditar = (EditText)findViewById(R.id.precioEditar);
    }

    private void recogerIntent(){
        if(getIntent() != null){
            Intent intent = getIntent();
            artista.setNombre(intent.getStringExtra("nombre"));
            artista.setApellidos(intent.getStringExtra("apellidos"));
            artista.setEdad(intent.getStringExtra("edad"));
            artista.setFecha(intent.getStringExtra("fecha"));
            artista.setCorreo(intent.getStringExtra("correo"));
            artista.setPais(intent.getStringExtra("pais"));
            artista.setCiudad(intent.getStringExtra("direccion"));
            artista.setUsuario(intent.getStringExtra("usuario"));
            artista.setRecorrido(intent.getStringExtra("recorrido"));
            artista.setPrecio(intent.getStringExtra("precio"));
            artista.setInstru(intent.getStringExtra("instru"));
            artista.setGeneros(intent.getStringExtra("generos"));
            artista.setNombreArtista(intent.getStringExtra("nombreArtista"));
            artista.setPaginaWeb(intent.getStringExtra("paginaWeb"));
            artista.setRol(intent.getStringExtra("rol"));
        }
    }

    private void rellenarCampos(ObjetoArtista artist){
        nombreEditar.setText(artist.getNombre());
        apeEditar.setText(artist.getApellidos());
        edadEditar.setText(artist.getEdad());
        fechaEditar.setText(artist.getFecha());
        correoEditar.setText(artist.getCorreo());
        paisEditar.setText(artist.getPais());
        ciudadEditar.setText(artist.getCiudad());
        usuEditar.setText(artist.getUsuario());
        precioEditar.setText(artist.getPrecio());
        instruEditar.setText(artist.getInstru());
        generoEditar.setText(artist.getGeneros());
        nombreArEditar.setText(artist.getNombreArtista());
        pagWebEditar.setText(artist.getPaginaWeb());
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
                añadirCamposRecorrido(buffer+"", viewPrincipal, i);
                buffer.delete(0,buffer.length());
            }
        }
        scrollRecorrido.addView(viewPrincipal);
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
        ArrayList<String> array = new ArrayList();
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


    private void actualizarDatosPerso(final ArrayList<String> array){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(editarPerfil.this, "Actualizando Perfil",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            params.put("usuario", artista.getUsuario());
            params.put("nombre", array.get(0));
            params.put("apellidos", array.get(1));
            params.put("edad", array.get(2));
            params.put("fecha", array.get(3));
            params.put("correo", array.get(4));
            params.put("pais", array.get(5));
            params.put("ciudad", array.get(6));
            String url = URL_LOCALHOST + "actualizarDatosPersonales.php";
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(),"Éxito al recojer los datos", Toast.LENGTH_LONG).show();
                                    artista.setNombre(array.get(0));
                                    artista.setApellidos(array.get(1));
                                    artista.setEdad(array.get(2));
                                    artista.setFecha(array.get(3));
                                    artista.setCorreo(array.get(4));
                                    artista.setPais(array.get(5));
                                    artista.setCiudad(array.get(6));
                                    rellenarCampos(artista);
                                }else{
                                    Toast.makeText(getApplicationContext(),"No se han encontrado coincidencias", Toast.LENGTH_LONG).show();
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
            Toast.makeText(getApplicationContext(),"No dispone de conexión a internet, intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
        }
    }

    private void actualizarDatosProfe(final ArrayList<String> array){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(editarPerfil.this, "Actualizando Perfil",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            params.put("usuario", artista.getUsuario());
            params.put("nombreArtista", array.get(0));
            params.put("generos", array.get(1));
            params.put("instru", array.get(2));
            params.put("pagWeb", array.get(3));
            params.put("precio", array.get(4));
            String url = URL_LOCALHOST + "actualizarDatosProfesionales.php";
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(),"Éxito al recojer los datos", Toast.LENGTH_LONG).show();
                                    artista.setNombreArtista(array.get(0));
                                    artista.setGeneros(array.get(1));
                                    artista.setInstru(array.get(2));
                                    artista.setPaginaWeb(array.get(3));
                                    artista.setPrecio(array.get(4));
                                    rellenarCampos(artista);
                                }else{
                                    Toast.makeText(getApplicationContext(),"Error al consultar los datos", Toast.LENGTH_LONG).show();
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
            Toast.makeText(getApplicationContext(),"No dispone de conexión a internet, intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
        }
    }

    private void actualizarCredenciales(final ArrayList<String> array){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(editarPerfil.this, "Actualizando Perfil",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            params.put("usuario", artista.getUsuario());
            params.put("usuarioNuevo", array.get(0));
            params.put("contra", array.get(1));
            params.put("clave", array.get(2));
            String url = URL_LOCALHOST + "actualizarCredenciales.php";
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(),"Exito al actualizar los datos", Toast.LENGTH_LONG).show();
                                    artista.setUsuario(array.get(0));
                                    artista.setContraseña(array.get(1));
                                    artista.setClave(array.get(2));
                                    rellenarCampos(artista);
                                }else{
                                    Toast.makeText(getApplicationContext(),"Error al actualizar los datos", Toast.LENGTH_LONG).show();
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
            Toast.makeText(getApplicationContext(),"No dispone de conexión a internet, intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
        }
    }

    private void guardarRecorrido(String recorrido){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(editarPerfil.this, "Actualizando Perfil",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            params.put("usuario", artista.getUsuario());
            params.put("reco",recorrido);
            String url = URL_LOCALHOST + "guardarRecorrido.php";
            /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(), "Éxito al actualizar los datos", Toast.LENGTH_SHORT).show();
                                    tipoEdit.setText("");
                                    generoEdit.setText("");
                                    fechaIniEdit.setText("");
                                    ciudadEdit.setText("");
                                    instruEdit.setText("");
                                    fechaFinEdit.setText("");
                                    comentariosEdit.setText("");
                                    separarRecorridos(artista.getRecorrido());
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al actualizar los datos", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getApplicationContext(),"No dispone de conexión a internet, intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
        }
    }


    private void seleccionarRol(int position) {
        switch (position){
            case 0:
                break;
            case 1:
                generoEditar.setText("Enfoque");
                instruEditar.setText("Idiomas");
                break;
            case 2:
                generoEditar.setText("Enfoque");
                instruEditar.setText("Idiomas");
                break;
            case 4:
                generoEditar.setText("Enfoque");
                instruEditar.setText("Idiomas");
                break;
            case 5:
                generoEditar.setText("Enfoque");
                instruEditar.setText("Idiomas");
                break;
            case 6:
                generoEditar.setText("Enfoque");
                instruEditar.setText("Idiomas");
                break;
            case 7:
                generoEditar.setText("Enfoque");
                instruEditar.setText("Idiomas");
                break;
            case 8:
                generoEditar.setText("Enfoque");
                instruEditar.setText("Idiomas");
                break;
            case 9:
                generoEditar.setText("Enfoque");
                instruEditar.setText("Idiomas");
                break;
            case 11:
                generoEditar.setText("Enfoque");
                instruEditar.setText("Idiomas");
                break;
            case 12:
                generoEditar.setText("Enfoque");
                instruEditar.setText("Idiomas");
                break;
            case 13:
                generoEditar.setText("Enfoque");
                instruEditar.setText("Idiomas");
                break;
            case 14:
                generoEditar.setText("Sector");
                instruEditar.setText("Idiomas");
                break;
            case 15:
                generoEditar.setText("Medidas");
                instruEditar.setText("Idiomas");
                break;
            case 17:
                generoEditar.setText("Enfoque");
                instruEditar.setText("Idiomas");
                break;
            case 18:
                generoEditar.setText("Enfoque");
                instruEditar.setText("Idiomas");
                break;
            case 19:
                generoEditar.setText("Sector");
                instruEditar.setText("Idiomas");
                break;
            case 20:
                generoEditar.setText("Enfoque");
                instruEditar.setText("Idiomas");
                break;
            case 21:
                generoEditar.setText("Enfoque");
                instruEditar.setText("Idiomas");
                break;
            default:
                generoEditar.setText("Enfoque");
                instruEditar.setText("Idiomas");
                break;
        }
    }
}
