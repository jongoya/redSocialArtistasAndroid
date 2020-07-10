package com.gestor.jonny.red.artista;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.gestor.jonny.red.R;
import com.gestor.jonny.red.Commons.Commons;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ArtistasAdapter extends BaseAdapter {
    Context context;
    ArrayList<artistaItem> artistas;
    Activity actividad;
    public ArtistasAdapter(Context cont, ArrayList<artistaItem> array, Activity act){
        this.context = cont;
        this.artistas = array;
        this.actividad = act;
    }
    @Override
    public int getCount() {
        return artistas.size();
    }

    @Override
    public Object getItem(int position) {
        return artistas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //METER AQUI UN VIEWHOLDER

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.artistasitem, parent, false);
        ImageView imagen =(ImageView)convertView.findViewById(R.id.imagenArtistaItem);
        TextView nombre = (TextView)convertView.findViewById(R.id.nombreArItem);
        TextView rol = (TextView)convertView.findViewById(R.id.rolText);
        hiloDeDescarga(artistas.get(position).getLink(), imagen);
        nombre.setText(artistas.get(position).getNombreAr());
        //rol.setText(Commons.getRol(Integer.valueOf(artistas.get(position).getRol())));
        ViewGroup.LayoutParams params = convertView.getLayoutParams();
        params.height = Commons.dpToPx(70, context);
        convertView.setLayoutParams(params);
        return convertView;
    }

    private void hiloDeDescarga(final String url, final ImageView imagen){
        Thread thread = new Thread() {
            @Override
            public void run() {
                descargarImagen(url, imagen);
            }
        };
        thread.start();

    }

    //HACER ESTA FUNCION GENERICA(UTILIZAR LA LIBRERIA PICASSO)
    public void descargarImagen(String url, final ImageView imagen){
        try {
            URL urlImagen = new URL(url);
            try {
                final Bitmap img = BitmapFactory.decodeStream(urlImagen.openConnection().getInputStream());
                //Llamamos al main thread para modificar el contenido del view
                actividad.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imagen.setImageBitmap(img);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
