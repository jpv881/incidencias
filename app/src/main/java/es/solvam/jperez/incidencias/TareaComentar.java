package es.solvam.jperez.incidencias;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
 * Created by Javi on 13/02/2016.
 */
public class TareaComentar extends AsyncTask<String, Void, String> {
    private Context context;
    private Activity activity;
    private String usuario;


    public TareaComentar(Context c,Activity a) {

        this.context = c;
        this.activity = a;
    }
    @Override
    protected String doInBackground(String... params) {
        usuario = params[0];
        String codigo = params[1];
        String texto = params[2];

        String link;
        String data;

        try {
            data = "?usuario=" + URLEncoder.encode(usuario, "UTF-8");
            data += "&codigo=" + URLEncoder.encode(codigo, "UTF-8");
            data += "&texto=" + URLEncoder.encode(texto, "UTF-8");


            link = "http://javierperez.ml/cliente/guardarcomentariomovil.php" + data;
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
                activity.startActivity(new Intent(activity, Principal.class).putExtra("name",usuario));

            }else{

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
