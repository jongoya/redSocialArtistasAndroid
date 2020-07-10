package com.gestor.jonny.red.busquedaGlobal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.gestor.jonny.red.artista.ArtistasAdapter;
import com.gestor.jonny.red.R;
import com.gestor.jonny.red.artista.artistaItem;
import com.gestor.jonny.red.artista.perfilArtista;
import com.gestor.jonny.red.Commons.Commons;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class busquedaGlobal extends FragmentActivity {

    public static String URL_LOCALHOST = "http://www.djmrbug.com/artistas/";
    private GoogleMap mMap;
    ArtistasAdapter adapter;
    ArrayList<artistaItem> artistas = new ArrayList<artistaItem>();
    String usuario = "";

    RelativeLayout layoutBusqueda;
    Button botonBusqueda;
    ListView lista;
    Button abrirBusqueda;
    EditText direccion;
    ProgressDialog progress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_global);

        recogerIntent();

        recogerCampos();

        setUpMapIfNeeded();

        layoutBusqueda.getBackground().setAlpha(200);
        lista.getBackground().setAlpha(200);

        botonBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(direccion.getText().length()>1){
                    abrirBusqueda.setVisibility(View.VISIBLE);
                    hideLayout();
                    showList();
                    reiniciarMapa();
                    MostrarDireccion(direccion.getText()+"");
                    recogerArtistasCiudad(direccion.getText()+"");
                }else{
                    Toast.makeText(getApplicationContext(), "Debe escribir una dirección", Toast.LENGTH_LONG).show();
                }
            }
        });

        abrirBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direccion.setText("");
                showLayout();
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombreUsu = artistas.get(position).getUsuario();
                Intent myIntent = new Intent(busquedaGlobal.this, perfilArtista.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myIntent.putExtra("usuario", nombreUsu);
                myIntent.putExtra("usuarioSesion", usuario);
                getApplicationContext().startActivity(myIntent);
            }
        });
    }

    private void recogerCampos(){
        lista = (ListView)findViewById(R.id.listaGlobal);
        layoutBusqueda =(RelativeLayout)findViewById(R.id.layoutBusquedaGlob);
        botonBusqueda = (Button)findViewById(R.id.botBusqGlob);
        abrirBusqueda = (Button)findViewById(R.id.botonAbrirGlob);
        direccion = (EditText)findViewById(R.id.direccionGlob);
    }

    private void recogerIntent(){
        Intent intent = getIntent();
        if(intent != null){
            usuario = intent.getStringExtra("usuario");
        }
    }

    private void hideLayout(){
        TranslateAnimation toTop = new TranslateAnimation(0, 0, 0, -150);
        toTop.setDuration(700);
        toTop.setFillAfter(true);
        RelativeLayout layout =(RelativeLayout)findViewById(R.id.layoutBusquedaGlob);
        layout.startAnimation(toTop);
    }

    private void showLayout(){
        TranslateAnimation toTop = new TranslateAnimation(0, 0, 0, 0);
        toTop.setDuration(700);
        toTop.setFillAfter(true);
        RelativeLayout layout =(RelativeLayout)findViewById(R.id.layoutBusquedaGlob);
        layout.startAnimation(toTop);
        abrirBusqueda.setVisibility(View.INVISIBLE);
        lista.setVisibility(View.INVISIBLE);
    }

    private void showList(){
        lista.setVisibility(View.VISIBLE);
        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) lista.getLayoutParams();
        p.setMargins(0, 0, 0, 0);
        lista.setLayoutParams(p);
    }

    private void reiniciarMapa(){
        mMap.clear();
        artistas.removeAll(artistas);
        adapter = new ArtistasAdapter(getApplicationContext(), artistas, busquedaGlobal.this);
        lista.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapGlobal)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 14.0f));
            }
        }
    }

    public void MostrarDireccion(String ciudad){
        final String direc = ciudad;
        String direccion =formatearDireccion(direc);
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address="+direccion+",&key=AIzaSyAmiCEYb3SOmjAPLAhZWp3xZLw9vF40ap4\n";
        /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.length()>1){
                                JSONArray json1 = response.getJSONArray("results");
                                JSONObject actor = json1.getJSONObject(0);
                                JSONObject actor2 = actor.getJSONObject("geometry");
                                JSONObject actor3 = actor2.getJSONObject("location");
                                double lat = (Double) actor3.get("lat");
                                double lng = (Double) actor3.get("lng");
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 14.0f));
                                mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title(direc).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                            }else{
                            }
                            onConnectionFinished();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onConnectionFailed(error.toString());
            }
        });
        addToQueue(request);*/
    }

    public String formatearDireccion(String texto){
        StringBuffer direc = new StringBuffer();
        //elimina espacios

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if(c != ' '){
                direc.append(c);
            }
        }
        StringBuffer res = new StringBuffer();
        //coloca comas entre numeros y letras
        boolean control = false;
        for (int i = 0; i < direc.length(); i++) {
            char c = direc.charAt(i);
            if(esUnNumero(c)){//si es un numero
                if(i<direc.length()-1){//verificamos que no nos salta arrayindexoutofbounds al cojer el siguiente numero
                    char b = direc.charAt(i+1);
                    if(!esUnNumero(b)){//si el siguiente no es un numero
                        control = true;
                    }
                }
            }else if(!esUnNumero(c)){//si es una letra
                if(i<direc.length()-1){//verificamos que no nos salta arrayindexoutofbounds al cojer el siguiente numero
                    char b = direc.charAt(i+1);
                    if(esUnNumero(b)){//si el siguiente es un numero
                        control = true;
                    }
                }
            }else if(!esUnNumero(c)){//si es una letra
                if(i<direc.length()-1){//verificamos que no nos salta arrayindexoutofbounds al cojer el siguiente numero
                    char b = direc.charAt(i+1);
                    if(!esUnNumero(b) && esMayusuclas(b)){//si el siguiente es un numero
                        control = true;
                    }
                }
            }
            res.append(c);
            if(control){
                res.append(',');
                control = false;
            }
        }
        return res+"";
    }

    public boolean esUnNumero(char c){
        if(c=='9'||c=='8'||c=='7'||c=='6'||c=='5'||c=='4'||c=='3'||c=='2'||c=='1'||c=='0'){
            return true;
        }else{
            return false;
        }
    }

    public void recogerArtistasCiudad(final String ciudad){
        if(Commons.isOnline(this)){

            progress = ProgressDialog.show(busquedaGlobal.this, "Cargando Artistas",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap<String, String>();
            params.put("ciudad", ciudad);
            String url = URL_LOCALHOST + "recogerDatosLocal.php";
            /*JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,url,new JSONObject(params),
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                if(response.length()>0){
                                    for (int i = 0; i < response.length(); i++) {
                                        JSONObject json = response.getJSONObject(i);
                                        artistaItem artista = new artistaItem();
                                        String direccion = (String) json.get("direccion");
                                        String nombre = (String) json.get("nombreArtista");
                                        String usuari = (String) json.get("usuario");
                                        String rol = (String) json.get("rol");
                                        String url = (String) json.get("url");
                                        artista.setDireccion(direccion);
                                        artista.setNombreAr(nombre);
                                        artista.setUsuario(usuari);
                                        artista.setRol(rol);
                                        artista.setLink(URL_LOCALHOST + url);
                                        String usuarioImpro = artista.getUsuario();
                                        if(!usuarioImpro.equals(usuario)){
                                            artistas.add(artista);
                                        }
                                    }
                                    filtrarArtistas(artistas, ciudad);
                                    adapter = new ArtistasAdapter(getApplicationContext(), artistas, busquedaGlobal.this);
                                    lista.setAdapter(adapter);
                                    setMarkers();
                                }
                                progress.dismiss();
                                onConnectionFinished();
                            } catch (Exception e) {
                                progress.dismiss();
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progress.dismiss();
                    onConnectionFailed(error.toString());
                }
            });
            addToQueue(request);*/
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexión a internet, intentelo de nuevo mas tarde", Toast.LENGTH_LONG).show();
        }
    }

    private void setMarkers(){
        for (int i = 0; i < artistas.size(); i++) {
            createMarker(artistas.get(i).getDireccion(), artistas.get(i).getNombreAr());
        }
    }

    private void createMarker(String direc, final String nombreArtista){
        String direccion= formatearDireccion(direc);
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address="+direccion+",&key=AIzaSyAmiCEYb3SOmjAPLAhZWp3xZLw9vF40ap4\n";
        /*JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.length()>1){
                                JSONArray json1 = response.getJSONArray("results");
                                JSONObject actor = json1.getJSONObject(0);
                                JSONObject actor2 = actor.getJSONObject("geometry");
                                JSONObject actor3 = actor2.getJSONObject("location");
                                double lat = (Double) actor3.get("lat");
                                double lng = (Double) actor3.get("lng");
                                mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title(nombreArtista));
                            }else{
                            }
                            onConnectionFinished();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onConnectionFailed(error.toString());
            }
        });
        addToQueue(request);*/
    }

    private boolean esMayusuclas(char c){
        String letra = String.valueOf(c);
        if(letra.equals(letra.toUpperCase())){
            return true;
        }else{
            return false;
        }
    }

    private String getNombreCiudad(String direccion){
        String direcFormateado = formatearDireccion(direccion);
        int numComas = 0;
        int contadorComas= 0;
        StringBuffer string = new StringBuffer();

        //contamos comas
        for (int i = 0; i < direcFormateado.length(); i++) {
            if(direcFormateado.charAt(i)==','){
                numComas++;
            }
        }

        //recojemos la ciudad teniendo en cuenta las comas
        boolean controlComas=false;
        for (int i = 0; i < direcFormateado.length(); i++) {
            if(contadorComas== numComas){
                string.append(direcFormateado.charAt(i));
            }
            if(direcFormateado.charAt(i)==','){
                contadorComas++;
            }
        }
        return string+"";
    }

    private void filtrarArtistas(ArrayList<artistaItem> artistas, String direccionLocal){
        ArrayList<artistaItem>arraySecundario = new ArrayList();
        for (int i = 0; i < artistas.size(); i++) {
            String direccion = artistas.get(i).getDireccion();
            String ciudad = getNombreCiudad(direccion);
            if(direccionLocal.contains(ciudad)){
                arraySecundario.add(artistas.get(i));
            }
        }
        artistas.removeAll(artistas);
        artistas.addAll(arraySecundario);
    }
}
