package com.gestor.jonny.red.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.gestor.jonny.red.Commons.Commons;
import com.gestor.jonny.red.Commons.Preferencias;
import com.gestor.jonny.red.ContraseñaOlvidada.ContraseñaOlvidadaActivity;
import com.gestor.jonny.red.R;
import com.gestor.jonny.red.Commons.Constants;
import com.gestor.jonny.red.Registro.RegistroPersonalActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.textoUsuario)EditText userField;
    @BindView(R.id.textoContra)EditText passwordField;
    @BindView(R.id.recordar) CheckBox recordar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.botonLogin)void didClickLogin() {
        checkLoginFields();
    }

    @OnClick(R.id.botonRegistro) void didClickRegitro() {
        Intent intent = new Intent(LoginActivity.this, RegistroPersonalActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.textoOlvidado) void didClickContraseñaOlvidada() {
        Intent intent = new Intent(LoginActivity.this, ContraseñaOlvidadaActivity.class);
        startActivity(intent);
    }

    private void checkLoginFields(){
        if (userField.getText().length() == 0) {
            Commons.showGenericAlertMessage(LoginActivity.this, "Debe rellenar el campo usuario");
            return;
        }

        if (passwordField.getText().length() == 0) {
            Commons.showGenericAlertMessage(LoginActivity.this, "Debe rellenar el campo contraseña");
            return;
        }

        if (!Commons.conexion(this)) {
            Commons.showGenericAlertMessage(LoginActivity.this, "No dispone de conexion a internet");
            return;
        }

        loginService();
    }

    private void loginService(){
        //TODO servicio login
        //GUARDAR los datos del usuario si el login esta bien
        if (recordar.isChecked()){
            Preferencias.saveUserName(getApplicationContext(), userField.getText().toString());
            Preferencias.savePassword(getApplicationContext(), passwordField.getText().toString());
        }
    }
}

