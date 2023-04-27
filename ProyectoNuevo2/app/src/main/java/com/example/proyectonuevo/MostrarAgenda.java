package com.example.proyectonuevo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MostrarAgenda extends AppCompatActivity {

    ListView listView;
    Adapter adapter;
    public static ArrayList<Usuarios>usuariosArrayList =new ArrayList<>();

    Usuarios usuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =findViewById(R.id.list);
        adapter=new Adapter(this,usuariosArrayList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                // ProgressDialog progressDialog=new ProgressDialog(view.getContext());

                CharSequence[]dialogo={"VER DATOS","EDITAR DATOS","ELIMINAR DATOS"};

                builder.setTitle(usuariosArrayList.get(position).getNombre());

                builder.setItems(dialogo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){

                            case 0:
                                startActivity(new Intent(getApplicationContext(),Detalles.class).putExtra("position",position));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(), Editar.class).putExtra("position",position));
                                break;
                            case 2:
                                Eliminar(usuariosArrayList.get(position).getId());
                                break;
                        }
                    }
                });
                builder.show();

            }
        });

        ListarDatos();
    }
    public  void agregar(View view){
        Intent intent=new Intent(MostrarAgenda.this,Agregar.class);
        startActivity(intent);
    }


    public void ListarDatos(){

        StringRequest request =new StringRequest(Request.Method.POST, "http://ec2-54-93-62-124.eu-central-1.compute.amazonaws.com/jveganzones001/WEB/mostrar_.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        usuariosArrayList.clear();
                        try {
                            JSONObject jsonObject =new JSONObject(response);
                            String exito=jsonObject.getString("exito");
                            JSONArray jsonArray =jsonObject.getJSONArray("datos");

                            if (exito.equals("1")){
                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject object=jsonArray.getJSONObject(i);
                                    String id=object.getString("id");
                                    String nombre=object.getString("nombre");
                                    String correo=object.getString("correo");
                                    String direccion=object.getString("direccion");

                                    usuarios =new Usuarios(id,nombre,correo,direccion);
                                    usuariosArrayList.add(usuarios);
                                    adapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(MostrarAgenda.this, "Error al listar los datos", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MostrarAgenda.this, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MostrarAgenda.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        ){


        };
        RequestQueue requestQueue = Volley.newRequestQueue(MostrarAgenda.this);
        requestQueue.add(request);

    }

    public  void Eliminar(final  String id){
        StringRequest request =new StringRequest(Request.Method.POST, "http://ec2-54-93-62-124.eu-central-1.compute.amazonaws.com/jveganzones001/WEB/eliminar_.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("datos eliminados")) {
                            Toast.makeText(MostrarAgenda.this, "elimino correctamente", Toast.LENGTH_SHORT).show();
                            if (usuariosArrayList.size() == 1) {
                                usuariosArrayList.clear(); // Elimina el único elemento de la lista
                            } else {
                                startActivity(new Intent(getApplicationContext(), MostrarAgenda.class));
                            }
                            finish();
                        } else {
                            Toast.makeText(MostrarAgenda.this, "Error no se puede registrar", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MostrarAgenda.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        }
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String>params=new HashMap<>();
                params.put("id",id);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MostrarAgenda.this);
        requestQueue.add(request);

    }
}
