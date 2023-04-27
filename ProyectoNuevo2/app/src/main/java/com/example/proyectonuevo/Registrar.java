package com.example.proyectonuevo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class Registrar extends AppCompatActivity {

    EditText txtName,txtEmail,pass;
    Button btn_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        txtName     = findViewById(R.id.ednombre);
        txtEmail    = findViewById(R.id.etemail);
        pass = findViewById(R.id.etcontraseña);
        btn_insert = findViewById(R.id.btn_register);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)!=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new
                    String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 11);
        }

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conseguirToken();
                //insertData(Url);
            }
        });
    }

    private void conseguirToken(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(ContentValues.TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        String URLInsertar = "http://ec2-54-93-62-124.eu-central-1.compute.amazonaws.com/jveganzones001/WEB/insertar.php";

                        insertData(URLInsertar,token);
                        enviarNotificacionFireBase(token);
                    }
                });
    }
    private void insertData(String Url, String tokenUsuario) {

        final String nombre = txtName.getText().toString().trim();
        final String email = txtEmail.getText().toString().trim();
        final String password = pass.getText().toString().trim();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando...");

        if(nombre.isEmpty()){
            txtName.setError("complete los campos");
            return;
        }
        else if(email.isEmpty()){

            txtEmail.setError("complete los campos");
            return;
        }


        else{
            progressDialog.show();

            StringRequest request = new StringRequest(Request.Method.POST, Url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Datas insertados")){

                                Toast.makeText(Registrar.this, "Datos insertados", Toast.LENGTH_SHORT).show();

                                progressDialog.dismiss();
/*
                                Intent intent=new Intent(registrar.this, login.class);
                                startActivity(intent);*/
                            }
                            else{
                                Toast.makeText(Registrar.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                Toast.makeText(Registrar.this, "No se puede insertar", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Registrar.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("nombre",nombre);
                    params.put("email",email);
                    params.put("password",password);
                    params.put("token", tokenUsuario);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Registrar.this);
            requestQueue.add(request);
        }
    }

    private void enviarNotificacionFireBase(String tokenUsu){
        Log.i("ENVIADO", tokenUsu);
        String URL = "http://ec2-54-93-62-124.eu-central-1.compute.amazonaws.com/jveganzones001/WEB/EnviarNotificacionFirebase.php";

        StringRequest busquedaLog = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Registrar.this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(Registrar.this, Login.class);
                startActivity(intent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Registrar.this, "Ha habido un problema al conectarse. Intentelo otra vez", Toast.LENGTH_SHORT);

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("tokenID",tokenUsu);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Registrar.this);
        requestQueue.add(busquedaLog);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public  void  login(View v){
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

}