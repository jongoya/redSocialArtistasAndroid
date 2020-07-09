package com.gestor.jonny.red.mensajes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gestor.jonny.red.R;
import com.gestor.jonny.red.commons.Commons;

import java.util.ArrayList;

/**
 * Created by jonny on 19/11/15.
 */
public class listaMensajes extends BaseAdapter {
    Context context;
    ArrayList<mensajesItem> array;
    int entradaOEnviados;

    public listaMensajes(Context cont, ArrayList<mensajesItem> mensajes, int caso){
        this.context = cont;
        this.array = mensajes;
        this.entradaOEnviados = caso;
    }
    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //VIEWHOLDER
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.mensaje, parent, false);
        TextView usuario = (TextView)convertView.findViewById(R.id.UsuarioMensaje);
        TextView asunto = (TextView)convertView.findViewById(R.id.asuntoMensaje);
        TextView fecha = (TextView)convertView.findViewById(R.id.fechaMensaje);
        ImageView imagen = (ImageView)convertView.findViewById(R.id.imagenLeido);
        asunto.setText(array.get(position).getAsunto());
        if(entradaOEnviados == 0){
            usuario.setText(array.get(position).getUsuarioOrigen());
        }else if(entradaOEnviados == 1){
            usuario.setText(array.get(position).getUsuarioDestino());
        }
        if(array.get(position).getLeido().equals("1")){
            imagen.setVisibility(View.VISIBLE);
        }
        fecha.setText(Commons.formatearFecha(array.get(position).getFecha()));
        AbsListView.LayoutParams param = (AbsListView.LayoutParams)convertView.getLayoutParams();
        param.height = Commons.dpToPx(80, context);
        convertView.setLayoutParams(param);
        return convertView;
    }
}
