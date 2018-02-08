package es.solvam.jperez.incidencias;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Comentar extends Activity {
    Bundle b;
    String name;
    int codigoIncidencia;
    Button btnComentar;
    EditText etComentario;
    Button btnCancelarComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.comentar);

        btnComentar = (Button)findViewById(R.id.btnComentar);
        btnCancelarComentario = (Button)findViewById(R.id.btnCancelarComentario);
        etComentario = (EditText)findViewById(R.id.etComentario);
        b = getIntent().getExtras();
        name = b.getString("name");
        codigoIncidencia = b.getInt("codigoIncidencia");

        btnComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TareaComentar(Comentar.this,Comentar.this).execute(name,String.valueOf(codigoIncidencia),etComentario.getText().toString().trim());
            }
        });

        btnCancelarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Comentar.this,Principal.class);
                i.putExtra("name",name);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comentar, menu);
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
