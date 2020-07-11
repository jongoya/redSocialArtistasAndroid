package com.gestor.jonny.red.webService;

import com.gestor.jonny.red.Models.EstiloModel;
import com.gestor.jonny.red.Models.InstrumentoModel;
import com.gestor.jonny.red.Models.RolModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServices {

    @GET("get_estilos")
    Call<ArrayList<EstiloModel>> getAllEstilos();

    @GET("get_instrumentos")
    Call<ArrayList<InstrumentoModel>> getAllInstrumentos();

    @GET("get_rols")
    Call<ArrayList<RolModel>> getAllRols();
}
