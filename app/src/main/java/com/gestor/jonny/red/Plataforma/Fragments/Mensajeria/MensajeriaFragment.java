package com.gestor.jonny.red.Plataforma.Fragments.Mensajeria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.gestor.jonny.red.R;

public class MensajeriaFragment extends Fragment {
    //TODO buscar por nombre
    //TODO busquedaLocal(Google Maps)
    //TODO busqueda global(Google Maps)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mensajeria_layout, container, false);
    }
}
