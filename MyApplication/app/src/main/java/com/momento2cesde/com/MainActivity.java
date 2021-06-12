package com.momento2cesde.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        //2. Aqui pongo los EditTex o Text View con sus Id que vaa llenar e usuario
        // Ejemplo:  etName = findViewById(R.id.etName);


        // Create a new user with a first and last name
        /*Map<String, Object> user = new HashMap<>();
        user.put("first", "Leo");
        user.put("last", "Lovelace");
        user.put("born", 1815);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firebase", "Error adding document", e);
                    }
                });*/
    }
    //Autentificacion de Usuarios Nuevos (Registro)
    /*public void registerUser(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent Registrar = new Intent(getApplicationContext(),RegistroActivity.class);
                            startActivity(Registrar);
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Usuario Registrado",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "No se registró",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }*/


    //Autentificacion de Usuarios (Verificar)
    public void singIn(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent Registrar = new Intent(getApplicationContext(),ListUsersActivity.class);
                            startActivity(Registrar);
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Inicio de sesion correcto",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Error de Usuario",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void onStart(View view) {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //  reload();
        }
    }

    public void RegistroActivity(View view) {
        Intent intent = new Intent(this, RegistroActivity.class);
              //Intent intent = new Intent(this, ListUsersActivity.class);
        startActivity(intent);
    }

    public void IraRegistro(View view){
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

    public void IraApartamentos(View view){
        Intent intent = new Intent(this, ListApartmentsActivity.class);
        startActivity(intent);
    }
    public void saverPreferences(){
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        String userState = "login";
        SharedPreferences.Editor editor = preferences.edit();// Habilita la edicion.
        editor.putString("state", "userState"); //agrega los datos
        editor.commit(); // Guarda los cambios
    }

    /*public String loadPreferences(){
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String userState = preferences.getString("state", "error");
        //Toast.makeText(this, userState, Toast.LENGTH_SHORT).show();
        return();

        if (userState.equals("login")){
             Intent intent = new Intent();
        }
    }*/

    //CODIGO PARA ELIMIAR REGISTRO POR ID

    /*public void deleteUser(View view){
        db.collection("usersApartments").document("741")
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                   Toast.makeText(MainActivity.this, "Documento Eliminado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, " Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }*/

    // Metodo para mostrar y ocultar el menu


}