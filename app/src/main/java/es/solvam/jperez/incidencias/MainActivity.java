package es.solvam.jperez.incidencias;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    Button btnCrear;
    Button btnAcceder;
    Button btnSalir;
    EditText loginUser;
    EditText loginPass;
    String user;
    String pass;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCrear = (Button)findViewById(R.id.btnCrear);
        btnSalir = (Button)findViewById(R.id.btnSalir);
        btnAcceder = (Button)findViewById(R.id.btnEntrar);
        loginUser = (EditText)findViewById(R.id.loginUser);
        loginPass = (EditText)findViewById(R.id.loginPass);
        vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,CrearUsuario.class);
                startActivity(i);
            }
        });

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(loginUser.getText().toString().trim().length() == 0){
                    Toast t = Toast.makeText(getApplicationContext(),"Debes introducir tu usuario",Toast.LENGTH_LONG);
                    t.show();
                }else{
                    user = loginUser.getText().toString();
                }

                if(loginPass.getText().toString().trim().length() == 0){
                    Toast t = Toast.makeText(getApplicationContext(),"Debes introducir tu contraseña",Toast.LENGTH_LONG);
                    t.show();
                }else{
                    pass = loginPass.getText().toString();
                }

                new Login(MainActivity.this,MainActivity.this).execute(user, pass);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void entrar(){
        Intent i = new Intent(MainActivity.this,Principal.class);
        i.putExtra("name",loginUser.getText().toString().trim());
        startActivity(i);
        vibrator.vibrate(250);
    }

    public void loginIncorrecto(){
        Toast t = Toast.makeText(getApplicationContext(),"DATOS DE ACCESO INCORRECTOS",Toast.LENGTH_LONG);
        t.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.acerca) {
            DialogoAcerca diAcerca = new DialogoAcerca();
            diAcerca.show(getFragmentManager(), "xxx");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
