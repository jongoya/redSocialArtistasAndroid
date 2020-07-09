package com.gestor.jonny.red.perfil;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gestor.jonny.red.R;

import java.util.ArrayList;


public class menuAdapter extends BaseAdapter {
    ArrayList<String> menu;
    Context contexto;
    public menuAdapter(ArrayList array, Context cont){
        this.menu = array;
        this.contexto = cont;
    }
    @Override
    public int getCount() {
        return menu.size();
    }

    @Override
    public Object getItem(int position) {
        return menu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //VIEWHOLDER
        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.menu_cell, parent, false);
        TextView texto = (TextView)convertView.findViewById(R.id.opcionMenu);
        ViewGroup.LayoutParams para = (ViewGroup.LayoutParams)convertView.getLayoutParams();
        para.height = dpToPx(60);
        convertView.setLayoutParams(para);
        texto.setText(menu.get(position));
        return convertView;
    }

    //HACER ESTE METODO GENERICO
    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = contexto.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
