package com.momento2cesde.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroApartamentos extends AppCompatActivity {

    EditText et_pais, et_ciudad, et_direccion, et_alcobas, et_banos, et_valorDia, et_valorNoche, et_descripcion;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_apartamentos);

        et_alcobas = findViewById(R.id.et_alcobas);
        et_banos = findViewById(R.id.et_banos);
        et_pais = findViewById(R.id.et_pais);
        et_ciudad = findViewById(R.id.et_ciudad);
        et_direccion = findViewById(R.id.etdireccion);
        et_valorDia = findViewById(R.id.et_valorDia);
        et_valorNoche = findViewById(R.id.et_valorNoche);
        et_descripcion = findViewById(R.id.et_descripcion);

    }

    public void saveApartments(View view){
        Map<String, Object> user = new HashMap<>();

        String alcobas = et_alcobas.getText().toString();
        String pais = et_pais.getText().toString();
        String banos = et_banos.getText().toString();
        String ciudad = et_ciudad.getText().toString();
        String direccion = et_direccion.getText().toString();
        String valorDia = et_valorDia.getText().toString();
        String valorNoche = et_valorNoche.getText().toString();
        String descripcion = et_descripcion.getText().toString();

        user.put("alcobas", alcobas);
        user.put("pais", pais);
        user.put("banos", banos);
        user.put("ciudad", ciudad);
        user.put("direccion", direccion);
        user.put("valorDia", valorDia);
        user.put("valorNoche", valorNoche);
        user.put("descripcion", descripcion);

        db.collection("apartments")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RegistroApartamentos.this, "Apartamento Registrado", Toast.LENGTH_SHORT).show();

                        Intent Registrar = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(Registrar);
                        Log.d("RegistroApartamento", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistroApartamentos.this, "No se registro", Toast.LENGTH_SHORT).show();
                        Log.w("RegistroApartamento", "Error adding document", e);
                    }
                });


    }
    // Metodo para mostrar y ocultar el menu

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_overflow, menu);
        return true;
    }

    //metodo para asignar las funciones a cada item
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.UsuarioRegistrado) {
            Intent logout = new Intent(getApplicationContext(), ListUsersActivity.class);
            startActivity(logout);
        }
        else if(id==R.id.VerApartamentos){

            Intent intent = new Intent(getApplicationContext(), ListApartmentsActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.RegistrarApartamento){

            Intent intent = new Intent(getApplicationContext(), RegistroApartamentos.class);
            startActivity(intent);
        }
        else if(id==R.id.CerrarSesion){

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}