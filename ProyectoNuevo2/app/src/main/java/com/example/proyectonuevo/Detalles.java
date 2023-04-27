package com.example.proyectonuevo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Detalles extends AppCompatActivity {

    TextView ednombre,edcorreo,eddireccion;
    Button btnregistrar;
    private  int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);


        ednombre=findViewById(R.id.ednombre);
        edcorreo=findViewById(R.id.edcorreo);
        eddireccion=findViewById(R.id.eddireccion);



        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");


        ednombre.setText(MostrarAgenda.usuariosArrayList.get(position).getNombre());
        edcorreo.setText(MostrarAgenda.usuariosArrayList.get(position).getCorreo());
        eddireccion.setText(MostrarAgenda.usuariosArrayList.get(position).getDireccion());
    }
}