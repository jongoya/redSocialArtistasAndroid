package com.gestor.jonny.red.Plataforma;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.gestor.jonny.red.Plataforma.Fragments.Busqueda.BusquedaFragment;
import com.gestor.jonny.red.Plataforma.Fragments.Mensajeria.MensajeriaFragment;
import com.gestor.jonny.red.Plataforma.Fragments.Novedades.NovedadesFragment;
import com.gestor.jonny.red.Plataforma.Fragments.Portfolio.PortfolioFragment;
import com.gestor.jonny.red.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Plataforma extends AppCompatActivity {
    @BindView(R.id.navigation_bar) BottomNavigationView navigationBar;

    public String novedadesTag = "novedades";
    public String busquedaTag = "busqueda";
    public String mensajeriaTag = "mensajeria";
    public String perfilTag = "perfil";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plataforma);
        ButterKnife.bind(this);
        setNavigationTabClick();
        changeFragment(new NovedadesFragment(), novedadesTag);
    }

    private void setNavigationTabClick() {
        navigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_novedades:
                        changeFragment(new NovedadesFragment(), novedadesTag);
                        break;
                    case R.id.nav_busqueda:
                        changeFragment(new BusquedaFragment(), busquedaTag);
                        break;
                    case  R.id.nav_mensajeria:
                        changeFragment(new MensajeriaFragment(), mensajeriaTag);
                        break;
                    default:
                        changeFragment(new PortfolioFragment(), perfilTag);
                        break;
                }
                return true;
            }
        });
    }

    private void changeFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, fragment, tag).commit();
    }

    /*private Bitmap rotateImage(Bitmap bitmap, String filePath){
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
        int rotationAngle = 0;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
        // Rotate Bitmap
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return rotatedBitmap;
    }*/

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            if(BitmapFactory.decodeFile(picturePath, options) != null){
                Bitmap imageBitmap = rotateImage(BitmapFactory.decodeFile(picturePath, options), picturePath);
                Bitmap imagenGrande = Commons.redimensionarImagen(imageBitmap, 1200);
                if(layoutSubirImg.getVisibility()==View.VISIBLE){
                    ImageView imageView = (ImageView) findViewById(R.id.imageSubida);
                    imagenNueva = imagenGrande;
                    imageView.setImageBitmap(imagenGrande);
                    urlImgNueva = picturePath;
                }else{
                    if (imagenFondo.getVisibility() == View.VISIBLE) {
                        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
                        imagenDePortada = imagenGrande;
                        imageView.setImageBitmap(imagenGrande);
                    } else if (imagenArtista.getVisibility() == View.VISIBLE) {
                        ImageView imageView = (ImageView) findViewById(R.id.imageView4);
                        imagenDeArtista = imagenGrande;
                        imageView.setImageBitmap(imagenGrande);
                    }
                }
            }else{
                Toast.makeText(getApplicationContext(), "Error al recoger la imagen por favor seleccione otra imagen", Toast.LENGTH_LONG).show();
            }

        }
    }*/

    /*public void consultaArtistas(){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Cargando Artistas",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap<String, String>();
            String url = Constants.URL_LOCALHOST + "formularioArtistas.php";
            params.put("usu", usuario);
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,url, new JSONObject(params),
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
                                            artistaFormulario.add(artista);
                                        }
                                    }
                                    if(artistaFormulario.size()>0){
                                        adapterFormulario = new ArtistasAdapter(getApplicationContext(), artistaFormulario, plataforma.this);
                                        listaAr.setAdapter(adapterFormulario);
                                        layoutFormulario.setVisibility(View.VISIBLE);
                                    }
                                    progress.dismiss();
                                }else{
                                    progress.dismiss();
                                }
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
            addToQueue(request);
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, no se han podido cargar las imagenes del perfil", Toast.LENGTH_LONG).show();
        }

    }*/

    /*private void actualizarPortada(String urlPortada, final String urlImagen){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Actualizando portada",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            String endPoint = Constants.URL_LOCALHOST + "cambiarPortada.php";
            String link = "imagenesSubidas/"+urlImagen;
            final String linkImgPortada = Commons.eliminarRuta(urlPortada);
            params.put("usu", usuario);
            params.put("urlImagen", link);
            params.put("urlPortada", urlPortada);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,endPoint, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    if(SqliteManager.setPortada(urlImagen, linkImgPortada, db)){
                                        Thread thread = new Thread() {
                                            @Override
                                            public void run() {
                                                final Bitmap img = SqliteManager.loadImageFromStorage(urlImagen);
                                                plataforma.this.runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                    ImageView foto = (ImageView)findViewById(R.id.fotoPerfil);
                                                    foto.setImageBitmap(img);
                                                    Toast.makeText(getApplicationContext(), "Imagen Actualizada", Toast.LENGTH_SHORT).show();
                                                    posPortada = imagenAmpliada;
                                                    progress.dismiss();
                                                    }
                                                });
                                            }
                                        };
                                        thread.start();
                                    }else{
                                        progress.dismiss();
                                        Toast.makeText(getApplicationContext(), "Error al actualizar la imagen", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    progress.dismiss();
                                    Toast.makeText(getApplicationContext(), "Error al actualizar la imagen", Toast.LENGTH_SHORT).show();
                                }
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
            addToQueue(request);
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }
    }*/
    /*private void actualizarArtista(String urlArtista, final String urlImagen){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Actualizando imagen",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            String endPoint = Constants.URL_LOCALHOST + "cambiarArtista.php";
            String link = "imagenesSubidas/"+urlImagen;
            final String linkImgArtista = Commons.eliminarRuta(urlArtista);
            params.put("usu", usuario);
            params.put("urlImagen", link);
            params.put("urlArtista", urlArtista);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,endPoint, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    if(SqliteManager.setArtista(urlImagen, linkImgArtista, db)){
                                        Thread thread = new Thread() {
                                            @Override
                                            public void run() {
                                                final Bitmap img = SqliteManager.loadImageFromStorage(urlImagen);
                                                plataforma.this.runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ImageView foto = (ImageView)findViewById(R.id.fotoArtista);
                                                        foto.setImageBitmap(img);
                                                        Toast.makeText(getApplicationContext(), "Imagen Actualizada", Toast.LENGTH_SHORT).show();
                                                        posArtista = imagenAmpliada;
                                                        progress.dismiss();
                                                    }
                                                });
                                            }
                                        };
                                        thread.start();
                                    }else{
                                        progress.dismiss();
                                        Toast.makeText(getApplicationContext(), "Error al actualizar la imagen", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al actualizar lod datos, intentelo de nuevo", Toast.LENGTH_SHORT).show();
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
            addToQueue(request);
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }
    }*/
    /*private void actualizarYoutube( final String enlace) {
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Actualizando enlace",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            String endPoint = Constants.URL_LOCALHOST + "enlaceYoutube.php";
            params.put("usu", usuario);
            params.put("enlace", enlace);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, endPoint, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    youtubePlayer.cueVideo(enlace);
                                    Toast.makeText(getApplicationContext(), "Se ha actualizado el enlace", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al actualizar el enlace", Toast.LENGTH_SHORT).show();
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
            addToQueue(request);
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }

    }*/
    /*private void actualizarRedes( final String enlace) {
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Actualizando redes",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            String endPoint = Constants.URL_LOCALHOST + "enlaceRedes.php";
            params.put("usu", usuario);
            params.put("enlace", enlace);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, endPoint, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(), "Enlace añadido", Toast.LENGTH_SHORT).show();
                                    enlaceRedes.setText("");
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al añadir el enlace, intentelo de nuevo", Toast.LENGTH_SHORT).show();
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
            addToQueue(request);
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }
    }*/

    /*private void recargarDatos(){
        if(Commons.isOnline(this)){
            Map<String, String> params = new HashMap();
            String endPoint = Constants.URL_LOCALHOST + "recargarDatos.php";
            params.put("usuario", usuario);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,endPoint, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    nombre = (String) response.get("nombre");
                                    apellidos = (String) response.get("ape");
                                    edad = (String) response.get("edad");
                                    fecha = (String) response.get("fecha");
                                    correo = (String) response.get("correo");
                                    pais = (String) response.get("pais");
                                    ciudad = (String) response.get("direccion");
                                    usuario = (String) response.get("usuario");
                                    recorri = (String) response.get("recorrido");
                                    nombreBar = (String) response.get("precio");
                                    instru = (String) response.get("instru");
                                    generos = (String) response.get("generos");
                                    nombreArtista = (String) response.get("nombreArtista");
                                    paginaWeb = (String) response.get("paginaWeb");
                                    rol = (String) response.get("rol");
                                    youtubeVideo = (String) response.get("youtube");
                                    enlacesRedesSociales = (String) response.get("redes");
                                    inicializarTextosPerfilPersonal();
                                    inicializarDatosProfesional();
                                    scrollRecorrido.removeAllViews();
                                    separarRecorridos(recorri);
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
            addToQueue(request);
        }else{
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }
    }*/

    /*private void actualizarTitulo(final String url, final String titulo, final int pos){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Actualizando datos",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            String endPoint = Constants.URL_LOCALHOST + "modificarImagen.php";
            String link = "imagenesSubidas/"+url;
            params.put("usu", usuario);
            params.put("url", link);
            params.put("titulo", titulo);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,endPoint, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    if(SqliteManager.editarTitulo(url, titulo, db)){
                                        titulosImagenes.set(pos, titulo);
                                        Toast.makeText(getApplicationContext(), "Imagen Actualizada", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Error al actualizar la imagen, intentelo de nuevo", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al actualizar la imagen, intentelo de nuevo", Toast.LENGTH_SHORT).show();
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
            addToQueue(request);
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }
    }*/
    /*private void eliminarImagen(final String url){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Eliminando imagen",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap();
            String endPoint = Constants.URL_LOCALHOST + "eliminarArchivo.php";
            String link = "imagenesSubidas/"+url;
            params.put("usu", usuario);
            params.put("url", link);
            params.put("urlPeque", Commons.getImagenMini(link));
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,endPoint, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Thread thread = new Thread() {
                                        @Override
                                        public void run() {
                                            if(SqliteManager.eliminarImagen(url, db)){
                                                urlImagenes.remove(imagenAmpliada);
                                            }
                                            plataforma.this.runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    gridview.setAdapter(new fotosAdapter(getApplicationContext(), urlImagenes, plataforma.this));
                                                    Toast.makeText(getApplicationContext(), "Imagen eliminada", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    };
                                    thread.start();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al eliminar la imagen", Toast.LENGTH_SHORT).show();
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
            addToQueue(request);
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }
    }*/


    /*public void subidaImagenNueva(final Bitmap imagen, final String numero, final String titulo){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Guardando imagen",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap<String, String>();

            //imagen Grande
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            imagen.compress(Bitmap.CompressFormat.JPEG, 80, out);
            byte[] byte_arr = out.toByteArray();
            String img = Base64.encodeToString(byte_arr, 0);

            //imagen pequeña
            Bitmap imgPeque = Commons.redimensionarImagen(imagen, 400);
            ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            imgPeque.compress(Bitmap.CompressFormat.JPEG, 80, out2);
            byte[] byte_arr2 = out2.toByteArray();
            String img2 = Base64.encodeToString(byte_arr2, 0);

            String url = Constants.URL_LOCALHOST + "imagenNueva.php";
            params.put("imagen", img);
            params.put("imagenPeque", img2);
            params.put("usuario", usuario);
            params.put("url", "imagenesSubidas/"  + numero + "Nueva.jpg");
            params.put("urlPeque", "imagenesSubidas/"  + numero + "NuevaMini.jpg");
            params.put("titulo", titulo);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int) response.get("valor") == 200){
                                    layoutSubirImg.startAnimation(outToRightAnimation(R.id.layoutSubirImg));
                                    Toast.makeText(getApplicationContext(), "Imagen Subida", Toast.LENGTH_SHORT).show();
                                    ImageView imageView = (ImageView) findViewById(R.id.imageSubida);
                                    final ImagenObject imag = new ImagenObject();
                                    imag.setUrl(numero + "Nueva.jpg");
                                    imag.setTitulo(titulo);
                                    imag.setPortada("false");
                                    imag.setArtista("false");
                                    Thread thread = new Thread() {
                                        @Override
                                        public void run() {
                                            if(SqliteManager.anadirImagen(imag,db,getApplicationContext(),imagen)){
                                                plataforma.this.runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        progress.dismiss();
                                                        gridview.setAdapter(new fotosAdapter(getApplicationContext(), urlImagenes, plataforma.this));
                                                    }
                                                });
                                            }else{
                                                progress.dismiss();
                                                Toast.makeText(getApplicationContext(), "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    };
                                    thread.start();
                                    imageView.setImageBitmap(null);
                                }else{
                                    progress.dismiss();
                                    Toast.makeText(getApplicationContext(), "Error al subir la imagen, intentelo de nuevo", Toast.LENGTH_SHORT).show();
                                }

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
            addToQueue(request);
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }

    }*/


    /*public void subidaImagenPortada(final Bitmap imagen){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Guardando imagen",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap<String, String>();

            //imagen grande
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            imagen.compress(Bitmap.CompressFormat.JPEG, 80, out);
            byte[] byte_arr = out.toByteArray();
            String img = Base64.encodeToString(byte_arr, 0);

            //imagen pequeña
            Bitmap imgPeque = Commons.redimensionarImagen(imagen, 400);
            ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            imgPeque.compress(Bitmap.CompressFormat.JPEG, 80, out2);
            byte[] byte_arr2 = out2.toByteArray();
            String img2 = Base64.encodeToString(byte_arr2, 0);

            String url = Constants.URL_LOCALHOST + "imagenPortada.php";
            params.put("imagen", img);
            params.put("imagenPeque", img2);
            params.put("usuario", usuario);
            params.put("url", "imagenesSubidas/" + usuario + "Portada.jpg");
            params.put("urlPeque", "imagenesSubidas/" + usuario + "PortadaMini.jpg");
            params.put("urlPhp", "imagenesSubidas/" + usuario + "Portada.jpg");
            params.put("urlPhpPeque", "imagenesSubidas/" + usuario + "PortadaMini.jpg");
            params.put("titulo", "Imagen Portada");
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(), "Imagen Cargada", Toast.LENGTH_SHORT).show();
                                    applyImgPortada();
                                    siguientePortada.setVisibility(View.INVISIBLE);
                                    imagenFondo.setVisibility(View.INVISIBLE);
                                    siguienteArtista.setVisibility(View.VISIBLE);
                                    imagenArtista.setVisibility(View.VISIBLE);
                                    imagenDePortada = null;
                                    final ImagenObject imag = new ImagenObject();
                                    imag.setUrl(usuario + "Portada.jpg");
                                    imag.setTitulo("Imagen Portada");
                                    imag.setPortada("true");
                                    imag.setArtista("false");
                                    Thread thread = new Thread() {
                                        @Override
                                        public void run() {
                                            if(SqliteManager.anadirImagen(imag,db,getApplicationContext(),imagen)){
                                                final Bitmap bitmap = SqliteManager.loadImageFromStorage(Commons.getImagenMini(imag.getUrl()));
                                                plataforma.this.runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        progress.dismiss();
                                                        ImageView foto = (ImageView)findViewById(R.id.fotoPerfil);
                                                        foto.setImageBitmap(bitmap);
                                                    }
                                                });
                                            }else{
                                                progress.dismiss();
                                                Toast.makeText(getApplicationContext(), "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    };
                                    thread.start();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al subir la imagen, intentelo de nuevo", Toast.LENGTH_SHORT).show();
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
            addToQueue(request);
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }

    }*/

    /*public void subidaImagenArtista(final Bitmap imagen){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Guardando imagen",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap<String, String>();

            //imagen grande
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            imagen.compress(Bitmap.CompressFormat.JPEG, 80, out);
            byte[] byte_arr = out.toByteArray();
            String img = Base64.encodeToString(byte_arr, 0);

            //imagen pequeña
            Bitmap imgPeque = Commons.redimensionarImagen(imagen, 400);
            ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            imgPeque.compress(Bitmap.CompressFormat.JPEG, 80, out2);
            byte[] byte_arr2 = out2.toByteArray();
            String img2 = Base64.encodeToString(byte_arr2, 0);

            String url = Constants.URL_LOCALHOST + "imagenArtista.php";
            params.put("imagen", img);
            params.put("imagenPeque", img2);
            params.put("usuario", usuario);
            params.put("url", "imagenesSubidas/" + usuario+ "Artista.jpg");
            params.put("urlPhp", "imagenesSubidas/" + usuario+ "Artista.jpg");
            params.put("urlPhpPeque", "imagenesSubidas/" + usuario+ "ArtistaMini.jpg");
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(), "Imagen Subida", Toast.LENGTH_SHORT).show();
                                    //imgArtista = "imagenesSubidas/" + usuario + "Portada.jpg";
                                    applyImgArtista();
                                    siguienteArtista.setVisibility(View.INVISIBLE);
                                    imagenArtista.setVisibility(View.INVISIBLE);
                                    finalizarImagenes.setVisibility(View.VISIBLE);
                                    recorrido.setVisibility(View.VISIBLE);
                                    final ImagenObject imag = new ImagenObject();
                                    imag.setUrl(usuario + "Artista.jpg");
                                    imag.setTitulo("Imagen Artista");
                                    imag.setPortada("false");
                                    imag.setArtista("true");
                                    Thread thread = new Thread() {
                                        @Override
                                        public void run() {
                                            if(SqliteManager.anadirImagen(imag,db,getApplicationContext(),imagen)){
                                                final Bitmap bitmap = SqliteManager.loadImageFromStorage(Commons.getImagenMini(imag.getUrl()));
                                                plataforma.this.runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        progress.dismiss();
                                                        ImageView foto = (ImageView)findViewById(R.id.fotoArtista);
                                                        foto.setImageBitmap(bitmap);
                                                        finalizarImagenes.setEnabled(false);
                                                    }
                                                });
                                            }else{
                                                progress.dismiss();
                                                Toast.makeText(getApplicationContext(), "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    };
                                    thread.start();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al subir la imagen, intentelo de nuevo", Toast.LENGTH_SHORT).show();
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
            addToQueue(request);
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }
    }*/

    /*public void guardarRecorrido(String recorrido){
        if(Commons.isOnline(this)){
            progress = ProgressDialog.show(Plataforma.this, "Actualizando recorrido",
                    " Unos segundos", true);
            Map<String, String> params = new HashMap<String, String>();
            String url = Constants.URL_LOCALHOST + "guardarRecorrido.php";
            params.put("usu",usuario);
            params.put("recorrido",recorrido);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if((int)response.get("valor") == 200){
                                    Toast.makeText(getApplicationContext(), "Recorrido añadido", Toast.LENGTH_SHORT).show();
                                    textoCiudadReco.setText("");
                                    textoGeneroReco.setText("");
                                    textoInstruReco.setText("");
                                    textoFechIniReco.setText("");
                                    textoFechFinReco.setText("");
                                    resumenReco.setText("");
                                    finalizarImagenes.setEnabled(true);
                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al añadir el recorrido, intentelo de nuevo", Toast.LENGTH_SHORT).show();
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
            addToQueue(request);
        }else{
            progress.dismiss();
            Toast.makeText(getApplicationContext(), "No dispone de conexion a internet, intentelo mas tarde", Toast.LENGTH_LONG).show();
        }
    }*/
}
