package com.gestor.jonny.red.Registro;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.gestor.jonny.red.Commons.Commons;
import com.gestor.jonny.red.Models.EstiloModel;
import com.gestor.jonny.red.Models.InstrumentoModel;
import com.gestor.jonny.red.Models.RolModel;
import com.gestor.jonny.red.Models.UserModel;
import com.gestor.jonny.red.R;
import com.gestor.jonny.red.SharedActivities.InputActivity;
import com.gestor.jonny.red.SharedActivities.ListSelectorActivity;
import com.gestor.jonny.red.SharedActivities.Models.ListSelectorModel;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistroProfesionalActivity extends AppCompatActivity {
    @BindView(R.id.rolField) TextView rolField;
    @BindView(R.id.instrumentoField) TextView instrumentoField;
    @BindView(R.id.estiloField) TextView estiloField;
    @BindView(R.id.webField) TextView webField;
    @BindView(R.id.precioField) TextView precioField;
    @BindView(R.id.nombreField) TextView nombreField;

    private final int rolRequest = 0;
    private final int nombreArtisticoRequest = 1;
    private final int instrumentoRequest = 2;
    private final int estiloRequest = 3;
    private final int webRequest = 4;
    private final int precioRequest = 5;

    private UserModel user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_profesional_layout);
        ButterKnife.bind(this);
        getUserIntent();
    }

    @OnClick(R.id.rolLayout) void didClickRol() {
        ArrayList<RolModel> rols = Commons.getOpcionesRol();
        openListSelectorActivity(rolRequest, convertRolsToSelectorObjects(rols), false);
    }

    @OnClick(R.id.nombreLayout) void didClickNombreArtistico() {
        openInputActivity(nombreArtisticoRequest, InputType.TYPE_CLASS_TEXT, user.getNombreArtista());
    }

    @OnClick(R.id.instrumentoLayout) void didClickInstrumento() {
        ArrayList<InstrumentoModel> instrumentos = Commons.getInstrumentos();
        openListSelectorActivity(instrumentoRequest, convertInstrumentosToSelectorObjects(instrumentos), true);
    }

    @OnClick(R.id.estiloLayout) void didClickEstilo() {
        ArrayList<EstiloModel> estilos = Commons.getEstilos();
        openListSelectorActivity(estiloRequest, convertEstilosToSelectorObjects(estilos), true);
    }

    @OnClick(R.id.webLayout) void didClickWeb() {
        openInputActivity(webRequest, InputType.TYPE_CLASS_TEXT, user.getPaginaWeb());
    }

    @OnClick(R.id.precioLayout) void didClickPrecio() {
        openInputActivity(precioRequest, InputType.TYPE_CLASS_NUMBER, String.valueOf(user.getPrecio()));
    }

    @OnClick(R.id.siguienteButton) void didClickSiguiente() {
        checkFields();
    }

    private void getUserIntent() {
        user = (UserModel) getIntent().getSerializableExtra("user");
    }

    private void openInputActivity(int requestCode, int inputType, String text) {
        Intent intent = new Intent(RegistroProfesionalActivity.this, InputActivity.class);
        intent.putExtra("keyboardType", inputType);
        intent.putExtra("fieldValue", text);
        startActivityForResult(intent, requestCode);
    }

    private void openListSelectorActivity(int requestCode, ArrayList<ListSelectorModel> options, boolean isMultiSelection) {
        Intent intent = new Intent(RegistroProfesionalActivity.this, ListSelectorActivity.class);
        intent.putExtra("options", options);
        intent.putExtra("isMultiSelection", isMultiSelection);
        startActivityForResult(intent, requestCode);
    }

    private ArrayList<ListSelectorModel> convertRolsToSelectorObjects(ArrayList<RolModel> rols) {
        ArrayList<ListSelectorModel> options = new ArrayList<>();
        for (int i = 0; i < rols.size(); i++) {
            options.add(new ListSelectorModel(rols.get(i).getNombre(), rols.get(i).getId()));
        }

        return options;
    }

    private ArrayList<ListSelectorModel> convertInstrumentosToSelectorObjects(ArrayList<InstrumentoModel> instrumentos) {
        ArrayList<ListSelectorModel> options = new ArrayList<>();
        for (int i = 0; i < instrumentos.size(); i++) {
            options.add(new ListSelectorModel(instrumentos.get(i).getNombre(), instrumentos.get(i).getId()));
        }

        return options;
    }

    private ArrayList<ListSelectorModel> convertEstilosToSelectorObjects(ArrayList<EstiloModel> estilos) {
        ArrayList<ListSelectorModel> options = new ArrayList<>();
        for (int i = 0; i < estilos.size(); i++) {
            options.add(new ListSelectorModel(estilos.get(i).getNombre(), estilos.get(i).getId()));
        }

        return options;
    }

    private String getInstrumentosStringFromArray(ArrayList<InstrumentoModel> instrumentos) {
        String instrumentosString = "";
        for (int i = 0; i < instrumentos.size(); i++) {
            instrumentosString = instrumentosString + instrumentos.get(i).getNombre() +", ";
        }

        return instrumentosString;
    }

    private String getEstilosStringFromArray(ArrayList<EstiloModel> estilos) {
        String estilosString = "";
        for (int i = 0; i < estilos.size(); i++) {
            estilosString = estilosString + estilos.get(i).getNombre() +", ";
        }

        return estilosString;
    }

    private RolModel convertListSelectorToRol(ListSelectorModel option) {
        ArrayList<RolModel> rols = Commons.getOpcionesRol();
        for (int i = 0; i < rols.size(); i++) {
            if (rols.get(i).getId() == option.getId()) {
                return rols.get(i);
            }
        }

        return null;
    }

    private ArrayList<InstrumentoModel> getInstrumentosFromOptions(ArrayList<ListSelectorModel> options) {
        ArrayList<InstrumentoModel> instrumentos = Commons.getInstrumentos();
        ArrayList<InstrumentoModel> results = new ArrayList<>();

        for (ListSelectorModel option: options) {
            for (InstrumentoModel instrumento: instrumentos) {
                if (option.getId() == instrumento.getId()) {
                    results.add(instrumento);
                }
            }
        }

        return results;
    }

    private ArrayList<EstiloModel> getEstilosFromOptions(ArrayList<ListSelectorModel> options) {
        ArrayList<EstiloModel> estilos = Commons.getEstilos();
        ArrayList<EstiloModel> results = new ArrayList<>();

        for (ListSelectorModel option: options) {
            for (EstiloModel estilo: estilos) {
                if (option.getId() == estilo.getId()) {
                    results.add(estilo);
                }
            }
        }

        return results;
    }

    private void checkFields() {
        if (rolField.getText().length() == 0) {
            Commons.showGenericAlertMessage(RegistroProfesionalActivity.this, "Debe seleccionar un rol");
            return;
        }

        if (nombreField.getText().length() == 0) {
            Commons.showGenericAlertMessage(RegistroProfesionalActivity.this, "Debe incluir un nombre artístico");
            return;
        }

        if (instrumentoField.getText().length() == 0) {
            Commons.showGenericAlertMessage(RegistroProfesionalActivity.this, "Debe seleccionar la menos 1 instrumento");
            return;
        }

        if (estiloField.getText().length() == 0) {
            Commons.showGenericAlertMessage(RegistroProfesionalActivity.this, "Debe seleccionar la menos 1 estilo musical");
            return;
        }

        if (precioField.getText().length() == 0) {
            Commons.showGenericAlertMessage(RegistroProfesionalActivity.this, "Debe incluir un precio");
            return;
        }

        openRegistroUsuarioActivity();
    }

    private void openRegistroUsuarioActivity() {
        Intent intent = new Intent(RegistroProfesionalActivity.this, RegistroUsuarioActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            assert data != null;
            switch (requestCode) {
                case rolRequest:
                    ListSelectorModel option = (ListSelectorModel) data.getSerializableExtra("option");
                    RolModel rol = convertListSelectorToRol(option);
                    if (rol != null) {
                        user.setRol(rol);
                        rolField.setText(rol.getNombre());
                    }
                    break;
                case nombreArtisticoRequest:
                    String nombre = data.getStringExtra("fieldValue");
                    nombreField.setText(nombre);
                    user.setNombreArtista(nombre);
                    break;
                case instrumentoRequest:
                    ArrayList<ListSelectorModel> options = (ArrayList<ListSelectorModel>) data.getSerializableExtra("option");
                    ArrayList<InstrumentoModel> instrumentos = getInstrumentosFromOptions(options);
                    user.setInstrumentos(instrumentos);
                    instrumentoField.setText(getInstrumentosStringFromArray(instrumentos));
                    break;
                case estiloRequest:
                    ArrayList<ListSelectorModel> optionsEstilos = (ArrayList<ListSelectorModel>) data.getSerializableExtra("option");
                    ArrayList<EstiloModel> estilos = getEstilosFromOptions(optionsEstilos);
                    user.setEstilos(estilos);
                    estiloField.setText(getEstilosStringFromArray(estilos));
                    break;
                case webRequest:
                    String web = data.getStringExtra("fieldValue");
                    webField.setText(web);
                    user.setPaginaWeb(web);
                    break;
                case precioRequest:
                    String precio = data.getStringExtra("fieldValue").replaceAll("[^\\d.]", "");
                    precioField.setText(precio + " €");
                    user.setPrecio(Double.parseDouble(precio));
                    break;
                default:
                    break;
            }
        }
    }
}
