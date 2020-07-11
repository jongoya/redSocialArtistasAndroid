package com.gestor.jonny.red.webService;

import com.gestor.jonny.red.Commons.Constants;
import com.gestor.jonny.red.Models.EstiloModel;
import com.gestor.jonny.red.Models.InstrumentoModel;
import com.gestor.jonny.red.Models.RolModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiServices {
    public static void getEstilos() {
        Call<ArrayList<EstiloModel>> call = Constants.webServices.getAllEstilos();
        call.enqueue(new Callback<ArrayList<EstiloModel>>() {
            @Override
            public void onResponse(Call<ArrayList<EstiloModel>> call, Response<ArrayList<EstiloModel>> response) {
                if (response.code() == 200) {
                    ArrayList<EstiloModel> estilos = response.body();
                    for (int i = 0; i < estilos.size(); i++) {
                        EstiloModel estilo = estilos.get(i);
                        if (Constants.databaseManager.estiloManager.getEstiloForEstiloId(estilo.getId()) == null) {
                            Constants.databaseManager.estiloManager.addEstilo(estilo);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<EstiloModel>> call, Throwable t) {
            }
        });
    }

    public static void getInstrumentos() {
        Call<ArrayList<InstrumentoModel>> call = Constants.webServices.getAllInstrumentos();
        call.enqueue(new Callback<ArrayList<InstrumentoModel>>() {
            @Override
            public void onResponse(Call<ArrayList<InstrumentoModel>> call, Response<ArrayList<InstrumentoModel>> response) {
                if (response.code() == 200) {
                    ArrayList<InstrumentoModel> instrumentos = response.body();
                    for (int i = 0; i < instrumentos.size(); i++) {
                        InstrumentoModel instrumento = instrumentos.get(i);
                        if (Constants.databaseManager.instrumentoManager.getInstrumentoForInstrumentoId(instrumento.getId()) == null) {
                            Constants.databaseManager.instrumentoManager.addInstrumento(instrumento);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<InstrumentoModel>> call, Throwable t) {
            }
        });
    }

    public static void getRols() {
        Call<ArrayList<RolModel>> call = Constants.webServices.getAllRols();
        call.enqueue(new Callback<ArrayList<RolModel>>() {
            @Override
            public void onResponse(Call<ArrayList<RolModel>> call, Response<ArrayList<RolModel>> response) {
                if (response.code() == 200) {
                    ArrayList<RolModel> rols = response.body();
                    for (int i = 0; i < rols.size(); i++) {
                        RolModel rol = rols.get(i);
                        if (Constants.databaseManager.rolManager.getRolForRolId(rol.getId()) == null) {
                            Constants.databaseManager.rolManager.addRol(rol);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RolModel>> call, Throwable t) {
            }
        });
    }
}
