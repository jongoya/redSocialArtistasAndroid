package com.gestor.jonny.red.mensajes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.gestor.jonny.red.R;
import com.gestor.jonny.red.Commons.Commons;

import java.util.ArrayList;

/**
 * Created by jonny on 16/9/16.
 */
public class eliminarAdapter extends BaseAdapter {
    ArrayList<mensajesItem> mensajes;
    Context contexto;



    public eliminarAdapter(Context cont, ArrayList<mensajesItem> men){
        this.mensajes = men;
        this.contexto = cont;
    }
    @Override
    public int getCount() {
        return mensajes.size();
    }

    @Override
    public Object getItem(int position) {
        return mensajes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //VIEWHOLDER
        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.layout_eliminar_cell, parent, false);
        AbsListView.LayoutParams param = (AbsListView.LayoutParams)convertView.getLayoutParams();
        param.height = Commons.dpToPx(80, contexto);
        convertView.setLayoutParams(param);
        return convertView;
    }
}
