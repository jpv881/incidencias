package es.solvam.jperez.incidencias;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by alumno on 12/02/16.
 */
public class TareaListarIncidencias  extends AsyncTask<String, Void, String> {

    private Context context;
    private Principal pr;

    public TareaListarIncidencias(Context context,Principal pr) {

        this.context = context;
        this.pr = pr;
    }
    @Override
    protected String doInBackground(String... params) {
        String link;

        link = "http://javierperez.ml/cliente/listadoincidenciasmovil.php";
        URL url = null;
        try {
            url = new URL(link);
            Log.v("link", link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder resultado = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                resultado.append(line);
            }
            Log.v("res:",resultado.toString());
            return resultado.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String resultado) {
        ArrayList<Incidencia> incidencias = new ArrayList<>();
        Comentario c;
        Incidencia in;
        ArrayList<Comentario> comentarios;
        try {
            /*String jsonStr = result;
            JSONObject jsonObj = new JSONObject(jsonStr);
            res = jsonObj.getString("result");
            Log.v("res",res);*/
            JSONArray arrayComentarios;

            JSONArray jsonArray  = new JSONArray(resultado);

            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {

                    comentarios = new ArrayList<>();
                    JSONObject obj = jsonArray.getJSONObject(i);
                    arrayComentarios = obj.getJSONArray("comentarios");

                    for(int j=0;j<arrayComentarios.length();j++){

                        JSONObject jsonComentario = arrayComentarios.getJSONObject(j);
                        Log.v("obj",jsonComentario.toString());

                        int codigoIncidencia = jsonComentario.getInt("codigoIncidencia");
                        int codigoUsuario = jsonComentario.getInt("codigoUsuario");
                        String fecha = jsonComentario.getString("fecha");
                        String texto = jsonComentario.getString("texto");
                        String nombre = jsonComentario.getString("nombreUsuarioComentario");

                        c = new Comentario();
                        c.setCodigoIncidencia(codigoIncidencia);
                        c.setCodigoUsuario(codigoUsuario);
                        c.setFecha(fecha);
                        c.setTexto(texto);
                        c.setNombre(nombre);

                        comentarios.add(c);
                    }

                    String usuario = obj.getString("usuario");
                    String fecha = obj.getString("fecha");
                    String descripcion = obj.getString("descripcion");
                    String rutaImagen = obj.getString("imagen");
                    int codigo = obj.getInt("codigo");
                    in = new Incidencia();
                    in.setUsuario(usuario);
                    in.setFecha(fecha);
                    in.setDescripcion(descripcion);
                    in.setImagen(rutaImagen);
                    in.setComentarios(comentarios);
                    in.setCodigo(codigo);
                    incidencias.add(in);

                }
            }
            pr.setIncidencias(incidencias);
            pr.rellenarListView();

Log.v("size",String.valueOf(incidencias.size()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
