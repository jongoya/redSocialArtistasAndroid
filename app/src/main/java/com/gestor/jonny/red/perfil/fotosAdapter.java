package com.gestor.jonny.red.perfil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.gestor.jonny.red.R;
import com.gestor.jonny.red.SQLite.SqliteManager;
import com.gestor.jonny.red.Commons.Commons;

import java.util.ArrayList;

/**
 * Created by jonny on 10/9/16.
 */
public class fotosAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> urls;
    private Activity actividad;

    public fotosAdapter(Context c, ArrayList<String> links, Activity ac){
        mContext = c;
        urls=links;
        this.actividad = ac;
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
        //VIEWHOLDER
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.itemgaleria, null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imagenGale);
        convertView.setLayoutParams(new GridView.LayoutParams(Commons.getScreenWidth(mContext,actividad)/3, Commons.getScreenWidth(mContext,actividad)/3));
        if(urls.get(position).contains(".png")){
            hiloDeDescarga(urls.get(position), imageView);
        }else{
            hiloDeDescarga(Commons.getImagenMini(urls.get(position)), imageView);
        }
        return convertView;
    }

    //PICASSO!!!!!
    public void descargarImagen(String url, final ImageView imagen){
        final Bitmap img = SqliteManager.loadImageFromStorage(url);
        //Llamamos al main thread para modificar el contenido del view
        actividad.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imagen.setImageBitmap(img);
            }
        });
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
