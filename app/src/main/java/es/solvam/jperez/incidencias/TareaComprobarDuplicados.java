package es.solvam.jperez.incidencias;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Javi on 11/02/2016.
 */
public class TareaComprobarDuplicados extends AsyncTask<String, Void, String> {
    private Context context;
    private CrearUsuario cu;

    public TareaComprobarDuplicados(Context context,CrearUsuario cu) {

        this.context = context;
        this.cu = cu;
    }
    @Override
    protected String doInBackground(String... params) {
        String nombre = params[0];
        String dni = params[1];
        String email = params[2];

        String link;
        String data;

        try {
            data = "?nombreUsuario=" + URLEncoder.encode(nombre, "UTF-8");
            data += "&dni=" + URLEncoder.encode(dni, "UTF-8");
            data += "&email=" + URLEncoder.encode(email, "UTF-8");


            link = "http://javierperez.ml/cliente/comprobardatosduplicados.php" + data;
            URL url = new URL(link);
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
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        String nombre = null;
        String dni = null;
        String email = null;
        try {
            String jsonStr = result;
            JSONObject jsonObj = new JSONObject(jsonStr);
            nombre = jsonObj.getString("user");
            dni = jsonObj.getString("dni");
            email = jsonObj.getString("email");
            Log.v("nombre",nombre);
            Log.v("dni",dni);
            Log.v("email",email);
            if(nombre.equals("true")){
                cu.toastErrorDuplicado("El nombre introducido ya existe en la base de datos.");
            }else if(dni.equals("true")){
                cu.toastErrorDuplicado("El dni introducido ya existe en la base de datos.");
            }else if(email.equals("true")){
                cu.toastErrorDuplicado("El email introducido ya existe en la base de datos.");
            }else{
                cu.crearUsuario();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
