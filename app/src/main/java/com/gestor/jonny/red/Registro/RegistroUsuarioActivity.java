package com.gestor.jonny.red.Registro;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gestor.jonny.red.Commons.Commons;
import com.gestor.jonny.red.Models.UserModel;
import com.gestor.jonny.red.R;
import com.gestor.jonny.red.SharedActivities.InputActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistroUsuarioActivity extends AppCompatActivity {
    @BindView(R.id.usuarioField) TextView usuarioField;
    @BindView(R.id.contraField) TextView contraField;
    @BindView(R.id.repiteContraField) TextView repiteContraField;
    @BindView(R.id.claveField) TextView claveField;

    private final int usuarioRequest = 0;
    private final int contraRequest = 1;
    private final int repiteContraRequest = 2;
    private final int claveRequest = 3;

    private UserModel user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_usuario_layout);
        ButterKnife.bind(this);
        getUserIntent();
    }


    @OnClick(R.id.usuarioLayout) void didClickUsuario() {
        openInputActivity(usuarioRequest, InputType.TYPE_CLASS_TEXT, user.getUsuario());
    }

    @OnClick(R.id.contraLayout) void didClickContraseña() {
        openInputActivity(contraRequest, InputType.TYPE_TEXT_VARIATION_PASSWORD, user.getPassword());
    }

    @OnClick(R.id.repiteContraLayout) void didClickRepiteContraseña() {
        openInputActivity(repiteContraRequest, InputType.TYPE_TEXT_VARIATION_PASSWORD, repiteContraField.getText().toString());
    }

    @OnClick(R.id.claveLayout) void didClickClave() {
        openInputActivity(claveRequest, InputType.TYPE_CLASS_NUMBER, user.getClave());
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

        Commons.showGenericAlertMessage(RegistroUsuarioActivity.this, "Registro finalizado");
    }

    private void openInputActivity(int requestCode, int inputType, String text) {
        Intent intent = new Intent(RegistroUsuarioActivity.this, InputActivity.class);
        intent.putExtra("keyboardType", inputType);
        intent.putExtra("fieldValue", text);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case usuarioRequest:
                    String usuario = data.getStringExtra("fieldValue");
                    usuarioField.setText(usuario);
                    user.setUsuario(usuario);
                    break;
                case contraRequest:
                    String password = data.getStringExtra("fieldValue");
                    contraField.setText(password);
                    user.setPassword(password);
                    break;
                case repiteContraRequest:
                    String repitePass = data.getStringExtra("fieldValue");
                    repiteContraField.setText(repitePass);
                    break;
                case claveRequest:
                    String clave = data.getStringExtra("fieldValue");
                    claveField.setText(clave);
                    user.setClave(clave);
                    break;
                default:
                    break;
            }
        }
    }
}
