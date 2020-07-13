package com.gestor.jonny.red.Registro;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gestor.jonny.red.Commons.Commons;
import com.gestor.jonny.red.Commons.Constants;
import com.gestor.jonny.red.Models.LoginModel;
import com.gestor.jonny.red.Models.UserAndLoginModel;
import com.gestor.jonny.red.Models.UserModel;
import com.gestor.jonny.red.R;
import com.gestor.jonny.red.SharedActivities.InputActivity;
import com.gestor.jonny.red.Plataforma.Plataforma;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroUsuarioActivity extends AppCompatActivity {
    @BindView(R.id.usuarioField) TextView usuarioField;
    @BindView(R.id.contraField) TextView contraField;
    @BindView(R.id.repiteContraField) TextView repiteContraField;
    @BindView(R.id.claveField) TextView claveField;
    @BindView(R.id.root) RelativeLayout rootLayout;

    private final int usuarioRequest = 0;
    private final int contraRequest = 1;
    private final int repiteContraRequest = 2;
    private final int claveRequest = 3;

    private UserModel user;
    private LoginModel login = new LoginModel();
    private RelativeLayout loadingStateView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_usuario_layout);
        ButterKnife.bind(this);
        getUserIntent();
    }


    @OnClick(R.id.usuarioLayout) void didClickUsuario() {
        openInputActivity(usuarioRequest, InputType.TYPE_CLASS_TEXT, login.getUsuario());
    }

    @OnClick(R.id.contraLayout) void didClickContraseña() {
        openInputActivity(contraRequest, InputType.TYPE_TEXT_VARIATION_PASSWORD, login.getPassword());
    }

    @OnClick(R.id.repiteContraLayout) void didClickRepiteContraseña() {
        openInputActivity(repiteContraRequest, InputType.TYPE_TEXT_VARIATION_PASSWORD, repiteContraField.getText().toString());
    }

    @OnClick(R.id.claveLayout) void didClickClave() {
        openInputActivity(claveRequest, InputType.TYPE_CLASS_NUMBER, login.getClave());
    }

    @OnClick(R.id.siguienteButton) void didClickSiguienteButton() {
        checkFields();
    }

    private void getUserIntent() {
        user = (UserModel) getIntent().getSerializableExtra("user");
    }

    private void checkFields() {
        if (usuarioField.getText().length() == 0) {
            Commons.showGenericAlertMessage(RegistroUsuarioActivity.this, "Debe incluir un nombre de usuario");
            return;
        }

        if (contraField.getText().length() < 6) {
            Commons.showGenericAlertMessage(RegistroUsuarioActivity.this, "Debe incluir una contraseña válida");
            return;
        }

        if (repiteContraField.getText().length() == 0) {
            Commons.showGenericAlertMessage(RegistroUsuarioActivity.this, "Debe repetir la contraseña introducida");
            return;
        }

        if (claveField.getText().length() == 0) {
            Commons.showGenericAlertMessage(RegistroUsuarioActivity.this, "Debe incluir una clave personal de seguridad");
            return;
        }

        if (contraField.getText().toString().compareTo(repiteContraField.getText().toString()) != 0) {
            Commons.showGenericAlertMessage(RegistroUsuarioActivity.this, "Las dos contraseñas deben coincidir");
            return;
        }

        registerUser(new UserAndLoginModel(user, login));
    }

    private void openInputActivity(int requestCode, int inputType, String text) {
        Intent intent = new Intent(RegistroUsuarioActivity.this, InputActivity.class);
        intent.putExtra("keyboardType", inputType);
        intent.putExtra("fieldValue", text);
        startActivityForResult(intent, requestCode);
    }

    private void openPlataformaActivity() {
        Intent intent = new Intent(RegistroUsuarioActivity.this, Plataforma.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case usuarioRequest:
                    String usuario = data.getStringExtra("fieldValue");
                    usuarioField.setText(usuario);
                    login.setUsuario(usuario);
                    break;
                case contraRequest:
                    String password = data.getStringExtra("fieldValue");
                    contraField.setText(password);
                    login.setPassword(password);
                    break;
                case repiteContraRequest:
                    String repitePass = data.getStringExtra("fieldValue");
                    repiteContraField.setText(repitePass);
                    break;
                case claveRequest:
                    String clave = data.getStringExtra("fieldValue");
                    claveField.setText(clave);
                    login.setClave(clave);
                    break;
                default:
                    break;
            }
        }
    }

    private void registerUser(UserAndLoginModel model) {
        loadingStateView = Commons.createLoadingStateView(getApplicationContext(), "Registrando usuario");
        rootLayout.addView(loadingStateView);
        Call<UserAndLoginModel> call = Constants.webServices.register(model);
        call.enqueue(new Callback<UserAndLoginModel>() {
            @Override
            public void onResponse(Call<UserAndLoginModel> call, Response<UserAndLoginModel> response) {
                rootLayout.removeView(loadingStateView);
                if (response.code() == 201) {
                    Constants.databaseManager.loginManager.saveLogin(response.body().getLogin());
                    Constants.databaseManager.userManager.saveUser(response.body().getUser());
                    openPlataformaActivity();
                } else if (response.code() == 302) {
                    Commons.showGenericAlertMessage(RegistroUsuarioActivity.this, "El usuario ya se encuentra registrado");
                } else if (response.code() == 420) {
                    Commons.showGenericAlertMessage(RegistroUsuarioActivity.this, "El usuario ya se encuentra registrado");
                }
            }

            @Override
            public void onFailure(Call<UserAndLoginModel> call, Throwable t) {
                rootLayout.removeView(loadingStateView);
                Commons.showGenericAlertMessage(RegistroUsuarioActivity.this, "Error registrando usuario, inténtelo de nuevo");
            }
        });
    }
}
