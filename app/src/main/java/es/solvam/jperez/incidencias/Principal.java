package es.solvam.jperez.incidencias;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;


public class Principal extends Activity {
    //URL url = new URL(urlToImage);
    //Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
    ArrayList<Incidencia> incidencias = new ArrayList<>();
    ListView lvIncidencias;
    ArrayList<String> in;
    ArrayList<String> inUsuario;
    ArrayList<String> inFecha;
    String name;
    Bundle b;
    ImageButton btnHome;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.principal);

        lvIncidencias = (ListView)findViewById(R.id.lvIncidencias);
        btnHome = (ImageButton)findViewById(R.id.btnHome);
        mp = MediaPlayer.create(this, R.raw.page);
        new TareaListarIncidencias(Principal.this,Principal.this).execute();

        lvIncidencias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Principal.this, VerIncidencia.class);
                intent.putExtra("ruta", incidencias.get(position).getImagen());
                intent.putExtra("descripcion", incidencias.get(position).getDescripcion());
                ArrayList<String> textoComentarios = new ArrayList<>();
                ArrayList<String> usuarioComentarios = new ArrayList<>();
                ArrayList<String> fechaComentarios = new ArrayList<>();
                b = getIntent().getExtras();
                name = b.getString("name");

                Incidencia incidencia = incidencias.get(position);
                Log.v("codigo", String.valueOf(incidencia.getCodigo()));

                for (int i = 0; i < incidencia.getComentarios().size(); i++) {

                    textoComentarios.add(incidencias.get(position).getComentarios().get(i).getTexto());
                    usuarioComentarios.add(incidencias.get(position).getComentarios().get(i).getNombre());
                    fechaComentarios.add(incidencias.get(position).getComentarios().get(i).getFecha());

                }
                intent.putExtra("comentarios", textoComentarios);
                intent.putExtra("usuarios", usuarioComentarios);
                intent.putExtra("fechas", fechaComentarios);
                intent.putExtra("name", name);
                intent.putExtra("codigoIncidencia", incidencia.getCodigo());
                startActivity(intent);
                mp.start();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Principal.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

    public void rellenarListView(){
        in = new ArrayList<>();
        inUsuario = new ArrayList<>();
        inFecha = new ArrayList<>();

        for(int i = 0; i<incidencias.size();i++){
            in.add(incidencias.get(i).getDescripcion());
            inUsuario.add(incidencias.get(i).getUsuario());
            inFecha.add(incidencias.get(i).getFecha());
        }

        ///ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,in);
        //lvIncidencias.setAdapter(adaptador);
        lvIncidencias.setAdapter(new Adaptador(Principal.this,in,inUsuario,inFecha));
    }

    public void setIncidencias(ArrayList<Incidencia> incidencias){
        this.incidencias = incidencias;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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
