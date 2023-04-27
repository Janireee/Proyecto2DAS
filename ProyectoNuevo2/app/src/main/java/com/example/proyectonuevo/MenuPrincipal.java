package com.example.proyectonuevo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {
    Button btn_crear_widget, btn_actualizar,btn_Camara,btnContactos;
    public static int num_imagen = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_menu);

        btn_crear_widget = findViewById(R.id.btn_crear_widget);
        btn_actualizar = findViewById(R.id.btn_actualizar);
        btnContactos = findViewById(R.id.btnContactos);
        btn_Camara = findViewById(R.id.btn_Camara);

        btn_Camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuPrincipal.this, Camara.class);
                startActivity(intent);
            }
        });

        btnContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuPrincipal.this, MostrarAgenda.class);
                startActivity(intent);
            }
        });

        btn_crear_widget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppWidgetManager appWidgetManager = v.getContext().getSystemService(AppWidgetManager.class);
                ComponentName myProvider = new ComponentName(v.getContext(), Widget .class);
                if (appWidgetManager.isRequestPinAppWidgetSupported()) {
                    appWidgetManager.requestPinAppWidget(myProvider, null, null);
                }
            }
        });

        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_imagen = (++num_imagen) % 3;
                AppWidgetManager appWidgetManager = v.getContext().getSystemService(AppWidgetManager.class);
                Intent intent = new Intent(getApplication(), Widget.class);
                int[] ids = appWidgetManager.getAppWidgetIds(new ComponentName(getApplication(), Widget.class));
                intent.setAction(appWidgetManager.ACTION_APPWIDGET_UPDATE);
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
                sendBroadcast(intent);
            }
        });
    }
}