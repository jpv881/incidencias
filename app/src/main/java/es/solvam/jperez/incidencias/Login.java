package es.solvam.jperez.incidencias;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by alumno on 8/02/16.
 */
public class Login extends AsyncTask<String, Void, String> {
    private Context context;
    private MainActivity ma;

    public Login(Context context,MainActivity ma) {

        this.context = context;
        this.ma = ma;
    }
    @Override
    protected String doInBackground(String... arg0) {
        String user = arg0[0];
        String pass = arg0[1];


        String link;
        String data;
Log.v("####","login");
        try {
            data = "?user=" + URLEncoder.encode(user, "UTF-8");
            data += "&pass=" + URLEncoder.encode(pass, "UTF-8");

            link = "http://javierperez.ml/cliente/loginmovil.php" + data;
            URL url = new URL(link);
            Log.v("link",link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            /*bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            Log.v("####",result);
            return result;*/
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
                    JSONObject obj = jsonArray.getJSONObject(i);
                    res = obj.getString("result");
                }
            }*/
                if(res.equals("true")){
                    ma.entrar();
                }else{
                    ma.loginIncorrecto();
                }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                String query_result = jsonObj.getString("query_result");
                if (query_result.equals("true")) {
                    ma.entrar();
                } else {
                    ma.loginIncorrecto();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
        }*/
    }
}

