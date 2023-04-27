package com.example.proyectonuevo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<Usuarios> {

    Context context;
    List<Usuarios> usuariosList;

    public Adapter(@NonNull Context context, List<Usuarios> UsuariosList) {
        super(context, R.layout.list_usuarios, UsuariosList);
        this.context = context;
        this.usuariosList = UsuariosList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_usuarios, parent, false);
        TextView tvid = view.findViewById(R.id.txt_id);
        TextView tvnombre = view.findViewById(R.id.txt_name);

        tvid.setText(usuariosList.get(position).getId());
        tvnombre.setText(usuariosList.get(position).getNombre());

        return view;
    }
}
