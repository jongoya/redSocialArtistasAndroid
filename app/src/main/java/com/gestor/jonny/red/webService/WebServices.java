package com.gestor.jonny.red.webService;

import com.gestor.jonny.red.Models.EstiloModel;
import com.gestor.jonny.red.Models.InstrumentoModel;
import com.gestor.jonny.red.Models.LoginModel;
import com.gestor.jonny.red.Models.RolModel;
import com.gestor.jonny.red.Models.UserAndLoginModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WebServices {

    @GET("get_estilos")
    Call<ArrayList<EstiloModel>> getAllEstilos();

    @GET("get_instrumentos")
    Call<ArrayList<InstrumentoModel>> getAllInstrumentos();

    @GET("get_rols")
    Call<ArrayList<RolModel>> getAllRols();

    @Headers("Content-Type: application/json")
    @POST("register")
    Call<UserAndLoginModel> register(@Body UserAndLoginModel model);

    @Headers("Content-Type: application/json")
    @POST("login")
    Call<UserAndLoginModel> login(@Body LoginModel model);
}
