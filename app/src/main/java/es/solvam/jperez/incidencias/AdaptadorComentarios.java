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

public class AdaptadorComentarios extends BaseAdapter {

    private final Activity act;
    ArrayList<String> com;
    ArrayList<String> comUsuarios;
    ArrayList<String> comFechas;
    public Integer[] image = new Integer[]{R.drawable.c1, R.drawable.c2, R.drawable.c3,
            R.drawable.c4, R.drawable.c5};



    public AdaptadorComentarios(Activity activity,ArrayList<String> comentarios,ArrayList<String> nombres,ArrayList<String> fechas){
        act = activity;
        com = comentarios;
        comUsuarios = nombres;
        comFechas = fechas;
    }


    public int getCount(){
        return com.size();
    }

    public Object getItem(int position){
        return com.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View v, ViewGroup parent){
        LayoutInflater inflater = act.getLayoutInflater();
        View view = inflater.inflate(R.layout.adaptador_comentarios, null);
        TextView tvSuperior = (TextView)view.findViewById(R.id.tvSuperiorComentario);
        tvSuperior.setText(com.get(position));
        TextView tvInferior = (TextView)view.findViewById(R.id.tvInferiorComentario);
        tvInferior.setText("Escrito el: "+comFechas.get(position)+" por: "+comUsuarios.get(position));
        ImageView imageView = (ImageView)view.findViewById(R.id.ivComentario);
        imageView.setImageResource(image[position%5]);
        return view;
    }
}
