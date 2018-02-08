package es.solvam.jperez.incidencias;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DialogoAcerca extends DialogFragment {

    TextView text;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialogo_acerca, null);
        text = (TextView)v.findViewById(R.id.text);
        text.append("Desarrollado por Javier Pérez\nComo proyecto final" +
                "\nPara la asignatura Desarrollo de Interfaces Web" +
                "\nEn el centro Solvam" +
                "\nEn Febrero de 2016\n\n" +
                "Icons made by:" +
                "\nIcon Works" +
                "\nFreepik" +
                "\n&" +
                "\nRobin Kylander" +
                "\nFrom www.flaticon.com ");

        builder.setView(v)
                .setIcon(R.drawable.information)
                .setTitle("Acerca De")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }

}