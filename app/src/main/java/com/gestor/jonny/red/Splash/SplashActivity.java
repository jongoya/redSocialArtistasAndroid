package com.gestor.jonny.red.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.gestor.jonny.red.R;
import com.gestor.jonny.red.Commons.Preferencias;
import com.gestor.jonny.red.Login.LoginActivity;
import com.gestor.jonny.red.perfil.Plataforma;

public class SplashActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

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
}
