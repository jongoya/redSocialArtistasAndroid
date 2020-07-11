package com.gestor.jonny.red.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.gestor.jonny.red.Commons.Constants;
import com.gestor.jonny.red.Models.EstiloModel;
import com.gestor.jonny.red.Models.InstrumentoModel;
import com.gestor.jonny.red.Models.RolModel;
import com.gestor.jonny.red.R;
import com.gestor.jonny.red.Commons.Preferencias;
import com.gestor.jonny.red.Login.LoginActivity;
import com.gestor.jonny.red.SQLite.DatabaseManager;
import com.gestor.jonny.red.perfil.Plataforma;
import com.gestor.jonny.red.webService.ApiServices;
import com.gestor.jonny.red.webService.RetrofitClientInstance;
import com.gestor.jonny.red.webService.WebServices;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        Constants.databaseManager = new DatabaseManager(getApplicationContext());
        initializeWebServicesApi();
        downloadAppData();

        splashEffect();
    }

    private void splashEffect(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                checkPreferencias();
            }
        }, 1000);
    }

    private void checkPreferencias(){
        if (Preferencias.checkUserCredentials(getApplicationContext())) {
            Intent intent = new Intent(SplashActivity.this, Plataforma.class);
            startActivity(intent);
        } else {
            Intent myIntent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(myIntent);
        }

        finish();
    }

    private void initializeWebServicesApi() {
        Constants.webServices = RetrofitClientInstance.getRetrofitInstance(getApplicationContext()).create(WebServices.class);
    }

    private void downloadAppData() {
        ApiServices.getEstilos();
        ApiServices.getInstrumentos();
        ApiServices.getRols();
    }
}
