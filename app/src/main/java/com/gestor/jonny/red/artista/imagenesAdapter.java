package com.gestor.jonny.red.artista;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.gestor.jonny.red.R;
import com.gestor.jonny.red.Commons.Commons;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by jonny on 27/10/15.
 */
public class imagenesAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<String> urls;
    private Activity actividad;

    public imagenesAdapter(Context c, ArrayList<String> links, Activity act){
        mContext = c;
        urls=links;
        this.actividad = act;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //METER UN VIEWHOLDER
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.itemgaleria, null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imagenGale);
        convertView.setLayoutParams(new GridView.LayoutParams(Commons.getScreenWidth(mContext,actividad)/3, Commons.getScreenWidth(mContext,actividad)/3));
        if(position == urls.size()-1){
            hiloDeDescarga(urls.get(position), imageView);
        }else{
            hiloDeDescarga(Commons.getImagenMini(urls.get(position)), imageView);
        }
        return convertView;
    }

    //HACER ESTA FUNCION GENERICA (UTILIZAR PICASSO)
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

    private void hiloDeDescarga(final String url, final ImageView imagen){
        Thread thread = new Thread() {
            @Override
            public void run() {
                descargarImagen(url, imagen);
            }
        };
        thread.start();
    }

}
