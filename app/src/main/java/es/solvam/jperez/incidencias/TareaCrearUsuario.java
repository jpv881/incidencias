package es.solvam.jperez.incidencias;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Javi on 09/02/2016.
 */
public class TareaCrearUsuario extends AsyncTask<String, Void, String> {
    private Context context;
    private CrearUsuario cu;

    public TareaCrearUsuario(Context context,CrearUsuario cu) {

        this.context = context;
        this.cu = cu;
    }
    @Override
    protected String doInBackground(String... params) {
        String nombre = params[0];
        String primerApellido = params[1];
        String segundoApellido = params[2];
        String direccion = params[3];
        String dni = params[4];
        String telefono = params[5];
        String email = params[6];
        String user = params[7];
        String pass = params[8];

        String link;
        String data;

        try {
            data = "?nombre=" + URLEncoder.encode(nombre, "UTF-8");
            data += "&primerApellido=" + URLEncoder.encode(primerApellido, "UTF-8");
            data += "&segundoApellido=" + URLEncoder.encode(segundoApellido, "UTF-8");
            data += "&direccion=" + URLEncoder.encode(direccion, "UTF-8");
            data += "&dni=" + URLEncoder.encode(dni, "UTF-8");
            data += "&telefono=" + URLEncoder.encode(telefono, "UTF-8");
            data += "&email=" + URLEncoder.encode(email, "UTF-8");
            data += "&user=" + URLEncoder.encode(user, "UTF-8");
            data += "&pass=" + URLEncoder.encode(pass, "UTF-8");

            link = "http://javierperez.ml/cliente/crearusuario.php" + data;
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
        String res = null;
        try {
            String jsonStr = result;
            JSONObject jsonObj = new JSONObject(jsonStr);
            res = jsonObj.getString("result");
            Log.v("res",res);
            /*JSONArray jsonArray  = new JSONArray(result);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(0);
                    res = obj.getString("result");
                }
            }*/
            if(res.equals("ok")){
                cu.continuar();
            }else{

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
