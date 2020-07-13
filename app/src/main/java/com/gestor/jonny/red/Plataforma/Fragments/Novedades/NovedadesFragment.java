package com.gestor.jonny.red.Plataforma.Fragments.Novedades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.gestor.jonny.red.R;

public class NovedadesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.novedades_layout, container, false);
    }
}
