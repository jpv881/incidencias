package es.solvam.jperez.incidencias;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Javi on 13/02/2016.
 */
public class TareaCargarImagen   extends AsyncTask<String, Void, Bitmap> {

    private Context context;
    private VerIncidencia vi;

    public TareaCargarImagen(Context context,VerIncidencia vi) {

        this.context = context;
        this.vi = vi;
    }
    @Override
    protected Bitmap doInBackground(String... params) {
        String ruta = params[0];
        URL url = null;
        Bitmap image = null;
        try {
            url = new URL(ruta);
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    protected void onPostExecute(Bitmap resultado) {
        vi.cargarImagen(resultado);
    }
}
