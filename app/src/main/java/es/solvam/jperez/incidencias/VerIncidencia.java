package es.solvam.jperez.incidencias;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class VerIncidencia extends Activity {
    ImageView im;
    TextView tvDescripcion;
    ListView lvComentarios;
    ArrayList<String> comentarios;
    ArrayList<String> usuarios;
    ArrayList<String> fechas;
    Bundle b;
    ImageButton btnCrearComentario;
    String name;
    int codigoIncidencia;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.incidencia);

        im = (ImageView)findViewById(R.id.iv);
        tvDescripcion = (TextView)findViewById(R.id.tvDescripcion);
        lvComentarios = (ListView)findViewById(R.id.lvComentarios);
        btnCrearComentario = (ImageButton)findViewById(R.id.btnCrearComentario);
        b = getIntent().getExtras();
        String ruta = b.getString("ruta");
        //String descripcion = b.get("descripcion").toString();
        String descripcion = b.getString("descripcion");
        tvDescripcion.setText(descripcion);
        mp = MediaPlayer.create(this, R.raw.page);

        rellenarListView();
        //ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,comentarios);
        //lvComentarios.setAdapter(adaptador);
        new TareaCargarImagen(VerIncidencia.this,VerIncidencia.this).execute(ruta);

        btnCrearComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VerIncidencia.this,Comentar.class);
                name = b.getString("name");
                codigoIncidencia = b.getInt("codigoIncidencia");
                i.putExtra("name",name);
                i.putExtra("codigoIncidencia",codigoIncidencia);
                startActivity(i);
                mp.start();
            }
        });

    }

    private void rellenarListView(){
        comentarios = b.getStringArrayList("comentarios");
        usuarios = b.getStringArrayList("usuarios");
        fechas = b.getStringArrayList("fechas");

        ///ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,in);
        //lvIncidencias.setAdapter(adaptador);

    }

    public void cargarImagen(Bitmap b){
        im.setImageBitmap(b);
        lvComentarios.setAdapter(new AdaptadorComentarios(VerIncidencia.this, comentarios, usuarios, fechas));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ver_incidencia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
