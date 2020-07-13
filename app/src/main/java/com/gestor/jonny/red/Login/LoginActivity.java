package com.gestor.jonny.red.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import com.gestor.jonny.red.Commons.Commons;
import com.gestor.jonny.red.Commons.Constants;
import com.gestor.jonny.red.ContraseñaOlvidada.ContraseñaOlvidadaActivity;
import com.gestor.jonny.red.Models.LoginModel;
import com.gestor.jonny.red.Models.UserAndLoginModel;
import com.gestor.jonny.red.R;
import com.gestor.jonny.red.Registro.RegistroPersonalActivity;
import com.gestor.jonny.red.Plataforma.Plataforma;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.textoUsuario)EditText userField;
    @BindView(R.id.textoContra)EditText passwordField;
    @BindView(R.id.Login) RelativeLayout rootLayout;

    private RelativeLayout loadingStateView;

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

    private void openPlataformaActivity() {
        Intent intent = new Intent(LoginActivity.this, Plataforma.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void loginService(){
        LoginModel login = new LoginModel(userField.getText().toString(), passwordField.getText().toString());
        loadingStateView = Commons.createLoadingStateView(getApplicationContext(), "Iniciando sesión");
        rootLayout.addView(loadingStateView);
        Call<UserAndLoginModel> call = Constants.webServices.login(login);
        call.enqueue(new Callback<UserAndLoginModel>() {
            @Override
            public void onResponse(Call<UserAndLoginModel> call, Response<UserAndLoginModel> response) {
                rootLayout.removeView(loadingStateView);
                if (response.code() == 200) {
                    Constants.databaseManager.loginManager.saveLogin(response.body().getLogin());
                    Constants.databaseManager.userManager.saveUser(response.body().getUser());
                    openPlataformaActivity();
                } else if (response.code() == 302) {
                    Commons.showGenericAlertMessage(LoginActivity.this, "El usuario no existe, revise credenciales");
                } else if (response.code() == 420) {
                    Commons.showGenericAlertMessage(LoginActivity.this, "La contraseña es incorrecta");
                }
            }

            @Override
            public void onFailure(Call<UserAndLoginModel> call, Throwable t) {
                rootLayout.removeView(loadingStateView);
                Commons.showGenericAlertMessage(LoginActivity.this, "Error iniciando sesión, inténtelo de nuevo");
            }
        });
    }
}

