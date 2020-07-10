package com.gestor.jonny.red.Login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gestor.jonny.red.R;

import java.util.ArrayList;


public class spinerAdapter extends BaseAdapter {
    private ArrayList<String> opciones;
    Context contexto;

    public spinerAdapter(ArrayList<String> array,Context cont){
        this.contexto = cont;
        this.opciones = array;

    }
    @Override
    public int getCount() {
        return opciones.size();
    }

    @Override
    public Object getItem(int position) {
        return opciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //VIEWHOLDER
        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.spinner_cell, null);
        TextView texto = (TextView)convertView.findViewById(R.id.opcion);
        texto.setText(opciones.get(position));
        return convertView;
    }
}
