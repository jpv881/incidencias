package es.solvam.jperez.incidencias;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CrearUsuario extends Activity {
    EditText nombre;
    EditText primerApellido;
    EditText segundoApellido;
    EditText direccion;
    EditText dni;
    EditText telefono;
    EditText email;
    EditText user;
    EditText pass;
    Button btnCrearUsuario;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.crear_usuario);

        nombre = (EditText)findViewById(R.id.etNombre);
        primerApellido = (EditText)findViewById(R.id.etPrimerApellido);
        segundoApellido = (EditText)findViewById(R.id.etSegundoApellido);
        direccion = (EditText)findViewById(R.id.etDireccion);
        dni = (EditText)findViewById(R.id.etDni);
        telefono = (EditText)findViewById(R.id.etTelefono);
        email = (EditText)findViewById(R.id.etEmail);
        user = (EditText)findViewById(R.id.etUser);
        pass = (EditText)findViewById(R.id.etPass);
        btnCrearUsuario = (Button)findViewById(R.id.btnCrearUsuario);
        vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

        btnCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearUsuario();
            }
        });
    }

    public void toastErrorDuplicado(String s){
        Toast t = Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG);
        t.show();
    }

    public void crearUsuario(){
        boolean error = false;
        String sError = "";

        if(nombre.getText().toString().trim().length()==0){
            error = true;
            sError +="Debes introducir un nombre.\n";
        }
        if(primerApellido.getText().toString().trim().length()==0){
            error = true;
            sError +="Debes introducir tu primer apellido.\n";
        }
        if(segundoApellido.getText().toString().trim().length()==0){
            error = true;
            sError +="Debes introducir tu segundo apellido.\n";
        }
        if(direccion.getText().toString().trim().length()==0){
            error = true;
            sError +="Debes introducir tu dirección.\n";
        }
        if(dni.getText().toString().trim().length()==0){
            error = true;
            sError +="Debes introducir tu DNI.\n";
        }
        if(telefono.getText().toString().trim().length()==0){
            error = true;
            sError +="Debes introducir tu número de teléfono.\n";
        }
        if(email.getText().toString().trim().length()==0){
            error = true;
            sError +="Debes introducir tu email.\n";
        }
        if(user.getText().toString().trim().length()==0){
            error = true;
            sError +="Debes introducir un nombre de usuario.\n";
        }
        if(pass.getText().toString().trim().length()==0){
            error = true;
            sError +="Debes introducir una contraseña.\n";
        }

        if(error){
            Toast t = Toast.makeText(getApplicationContext(),sError,Toast.LENGTH_LONG);
            t.show();
        }else{
            new TareaCrearUsuario(CrearUsuario.this,CrearUsuario.this).execute(nombre.getText().toString().trim(),
                    primerApellido.getText().toString().trim(),
                    segundoApellido.getText().toString().trim(),
                    direccion.getText().toString().trim(),
                    dni.getText().toString().trim(),
                    telefono.getText().toString().trim(),
                    email.getText().toString().trim(),
                    user.getText().toString().trim(),
                    pass.getText().toString().trim());
        }
    }

    public void continuar(){
        Intent i = new Intent(CrearUsuario.this,Principal.class);
        startActivity(i);
        vibrator.vibrate(250);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crear_usuario, menu);
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
