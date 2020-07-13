package com.gestor.jonny.red.Plataforma.Fragments.Portfolio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.gestor.jonny.red.Commons.Commons;
import com.gestor.jonny.red.Plataforma.Plataforma;
import com.gestor.jonny.red.R;
import com.gestor.jonny.red.SQLite.SqliteManager;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class PortfolioFragment extends Fragment {// extends YouTubeBaseActivity implements  YouTubePlayer.OnInitializedListener
    //TODO Perfil usuario
    //TODO editar perfil
    //TODO Ajustes(Cerrar sesión)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.portfolio_layout, container, false);
    }

    /*public void añadirCamposRecorrido(String texto, LinearLayout viewPrincipal, int contador){
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
        paramsSubView.setMargins(0, Commons.dpToPx(20, this), 0, 0);
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
    }*/

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

    /*private RelativeLayout generateRedLayout(String red){
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
    }*/

    /*private void inicializarRedesSociales(){
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
    }*/


    /*private void cargarImagenGrande(final String url, final ImageView imagen){
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
    }*/

    /*private void inicializarYoutube(){
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Commons.YOUTUBE_API_KEY, this);
    }*/
}
