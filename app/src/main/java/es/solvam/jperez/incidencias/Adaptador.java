package es.solvam.jperez.incidencias;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private final Activity act;
    ArrayList<String> in;
    ArrayList<String> inUsuarios;
    ArrayList<String> inFechas;
    public Integer[] image = new Integer[]{R.drawable.s1, R.drawable.s2, R.drawable.s3,
            R.drawable.s4, R.drawable.s5};



    public Adaptador(Activity activity,ArrayList<String> incidencias,ArrayList<String> nombres,ArrayList<String> fechas){
        act = activity;
        in = incidencias;
        inUsuarios = nombres;
        inFechas = fechas;
    }


    public int getCount(){
        return in.size();
    }

    public Object getItem(int position){
        return in.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View v, ViewGroup parent){
        LayoutInflater inflater = act.getLayoutInflater();
        View view = inflater.inflate(R.layout.adaptador, null);
        TextView tvSuperior = (TextView)view.findViewById(R.id.tvSuperior);
        tvSuperior.setText(in.get(position));
        TextView tvInferior = (TextView)view.findViewById(R.id.tvInferior);
        tvInferior.setText("Escrito el: "+inFechas.get(position)+" por: "+inUsuarios.get(position));
        ImageView imageView = (ImageView)view.findViewById(R.id.iv);
        imageView.setImageResource(image[position%5]);
        return view;
    }
}
