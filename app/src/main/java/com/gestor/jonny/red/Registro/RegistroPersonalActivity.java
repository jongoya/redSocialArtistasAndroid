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
import com.gestor.jonny.red.SharedActivities.DatePickerActivity;
import com.gestor.jonny.red.SharedActivities.InputActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistroPersonalActivity extends AppCompatActivity {
    private final int nombreRequest = 0;
    private final int apellidosRequest = 1;
    private final int edadRequest = 2;
    private final int fechaRequest = 3;
    private final int correoRequest = 4;
    private final int direccionRequest = 5;


    @BindView(R.id.nombreField) TextView nombreField;
    @BindView(R.id.apellidosField) TextView apellidosField;
    @BindView(R.id.edadField) TextView edadField;
    @BindView(R.id.fechaField) TextView fechaField;
    @BindView(R.id.correoField) TextView correoField;
    @BindView(R.id.direccionField) TextView direccionField;

    UserModel user = new UserModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_personal_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.nombreLayout)void didClickNombre() {
        openInputActivity(nombreRequest, InputType.TYPE_CLASS_TEXT, nombreField.getText().toString());
    }

    @OnClick(R.id.apellidosLayout)void didClickApellidos() {
        openInputActivity(apellidosRequest, InputType.TYPE_CLASS_TEXT, apellidosField.getText().toString());
    }

    @OnClick(R.id.edadLayout)void didClickEdad() {
        openInputActivity(edadRequest, InputType.TYPE_CLASS_NUMBER, edadField.getText().toString());
    }

    @OnClick(R.id.fechaLayout)void didClickFecha() {
        openDatePickerActivity(fechaRequest, user.getFecha());
    }

    @OnClick(R.id.correoLayout)void didClickCorreo() {
        openInputActivity(correoRequest, InputType.TYPE_CLASS_TEXT, correoField.getText().toString());
    }

    @OnClick(R.id.direccionLayout)void didClickDireccion() {
        openInputActivity(direccionRequest, InputType.TYPE_CLASS_TEXT, direccionField.getText().toString());
    }

    @OnClick(R.id.siguienteButton)void didClickSiguienteButton() {
        checkFields();
    }

    private void openInputActivity(int requestCode, int inputType, String text) {
        Intent intent = new Intent(RegistroPersonalActivity.this, InputActivity.class);
        intent.putExtra("keyboardType", inputType);
        intent.putExtra("fieldValue", text);
        startActivityForResult(intent, requestCode);
    }

    private void openDatePickerActivity(int requestCode, long fecha) {
        Intent intent = new Intent(RegistroPersonalActivity.this, DatePickerActivity.class);
        intent.putExtra("timestamp", fecha);
        startActivityForResult(intent, requestCode);
    }

    private void openRegistroProfesionalActivity() {
        Intent intent = new Intent(RegistroPersonalActivity.this, RegistroProfesionalActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    private void checkFields() {
        if (nombreField.getText().length() == 0) {
            Commons.showGenericAlertMessage(RegistroPersonalActivity.this, "Debe rellenar el campo nombre");
            return;
        }

        if (apellidosField.getText().length() == 0) {
            Commons.showGenericAlertMessage(RegistroPersonalActivity.this, "Debe rellenar el campo apellidos");
            return;
        }

        if (edadField.getText().length() == 0) {
            Commons.showGenericAlertMessage(RegistroPersonalActivity.this, "Debe rellenar el campo edad");
            return;
        }

        if (fechaField.getText().length() == 0) {
            Commons.showGenericAlertMessage(RegistroPersonalActivity.this, "Debe rellenar el campo fecha");
            return;
        }

        if (correoField.getText().length() == 0) {
            Commons.showGenericAlertMessage(RegistroPersonalActivity.this, "Debe rellenar el campo correo");
            return;
        }

        if (direccionField.getText().length() == 0) {
            Commons.showGenericAlertMessage(RegistroPersonalActivity.this, "Debe rellenar el campo dirección");
            return;
        }

        if (!correoField.getText().toString().contains("@")) {
            Commons.showGenericAlertMessage(RegistroPersonalActivity.this, "Debe incluir un correo válido");
            return;
        }

        openRegistroProfesionalActivity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            assert data != null;
            switch (requestCode) {
                case nombreRequest:
                    String nombre = data.getStringExtra("fieldValue");
                    nombreField.setText(nombre);
                    user.setNombre(nombre);
                    break;
                case apellidosRequest:
                    String apellidos = data.getStringExtra("fieldValue");
                    apellidosField.setText(apellidos);
                    user.setApellidos(apellidos);
                    break;
                case edadRequest:
                    String edad = data.getStringExtra("fieldValue").replaceAll("[^\\d.]", "");
                    edadField.setText(edad);
                    user.setEdad(Integer.parseInt(edad));
                    break;
                case fechaRequest:
                    long fecha = data.getLongExtra("timestamp", 0);
                    fechaField.setText(Commons.convertTimestampDateString(fecha));
                    user.setFecha(fecha);
                    break;
                case correoRequest:
                    String correo = data.getStringExtra("fieldValue");
                    correoField.setText(correo);
                    user.setCorreo(correo);
                    break;
                case direccionRequest:
                    String direccion = data.getStringExtra("fieldValue");
                    direccionField.setText(direccion);
                    user.setDireccion(direccion);
                    break;
                default:
                    break;
            }
        }
    }
}
