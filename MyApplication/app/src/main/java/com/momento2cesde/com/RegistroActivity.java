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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    EditText etName, etIdentification, etCountry, etCity, etEmail , etPassword;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etName = findViewById(R.id.etName);
        etName.requestFocus();
        etIdentification = findViewById(R.id.etIdentification);
        etCountry = findViewById(R.id.etCountry);
        etCity = findViewById(R.id.etCity);
        etEmail= findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

    }

    //Validacion de los campos
    /*public boolean validarDatos(){
        
        boolean retorno = true;
        String name = etName.getText().toString();
        String identification = etIdentification.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();

        if(name.isEmpty())
        {
            etName.setError("Campo Obligatorio");
            retorno= false;
        }
        if (identification.isEmpty())
        {
            etIdentification.setError("Campo Obligatorio");
            retorno= false;
        }
        if (password.isEmpty())
        {
            etPassword.setError("Campo Obligatorio");
            retorno= false;
        }
        if (email.isEmpty())
        {
            etEmail.setError("Campo Obligatorio");
            retorno= false;
        }
        return retorno;
    }
*/

    /*//Autentificacion de Usuarios (Registro)
    public void registerUser(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(RegistroActivity.this, "Usuario Registrado",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegistroActivity.this, "No se registró",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }*/

    //Autentificacion de Usuarios (Verificar)
    /*public void singIn(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(RegistroActivity.this, "Inicio de sesion correcto",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegistroActivity.this, "Error de Usuario",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }*/

    //Conexion a BD de los datos del Usuario

        public void saveUser(View view) {
        Map<String, Object> user = new HashMap<>();

        String name = etName.getText().toString();
        String identification = etIdentification.getText().toString();
        String country = etCountry.getText().toString();
        String city = etCity.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();

            if (email.isEmpty()||password.isEmpty()||identification.isEmpty()||name.isEmpty()||country.isEmpty()||city.isEmpty()){
                Toast.makeText(this, "Los campos no pueden quedar vacios", Toast.LENGTH_SHORT).show();
            }
            else if (password.length()<8){
                Toast.makeText(this, "La contraseña debe tener minimo 8 caracteres", Toast.LENGTH_SHORT).show();
            }

            else{
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Map<String, Object> user = new HashMap<>();

                                    String name = etName.getText().toString();
                                    String identification = etIdentification.getText().toString();
                                    String country = etCountry.getText().toString();
                                    String city = etCity.getText().toString();
                                    String email = etEmail.getText().toString();
                                    String password = etPassword.getText().toString();


                                    user.put("identification", identification);
                                    user.put("name", name);
                                    user.put("country", country);
                                    user.put("city", city);
                                    user.put("email", email);
                                    user.put("password", password);

                                    db.collection("users").document(identification)
                                            .set(user)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    etIdentification.setText("");
                                                    etName.setText("");
                                                    etCountry.setText("");
                                                    etCity.setText("");
                                                    etEmail.setText("");
                                                    etPassword.setText("");
                                                    etName.requestFocus();
                                                    Toast.makeText(RegistroActivity.this, "Usuario Registrado con exito", Toast.LENGTH_SHORT).show();
                                                    Log.d("Firestore", "DocumentSnapshot successfully written!");
                                                    Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(RegistroActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                                                    Log.w("Firestore", "Error writing document", e);
                                                }
                                            });


                                } else if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegistroActivity.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(RegistroActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        }

         public void VerTodos(View view) {
        Intent intent = new Intent(this, ListApartmentsActivity.class);
        startActivity(intent);
    }

        /*user.put("name", name);
        user.put("identification", identification);
        user.put("country", country);
        user.put("city", city);
        user.put("password", password);
        user.put("email", email);
        user.put("passwordDos", passwordDos);
        user.put("emailDos", emailDos);


       /* if(validarDatos()){
            Toast.makeText(this, "Ingresaste datos", Toast.LENGTH_SHORT).show();
        }*/

        //CODIGO PARA REGISTRAR CON ID AUTOMATICO
        /*db.collection("usersApartments")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RegistroActivity.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                        Log.d("firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistroActivity.this, "Hubo un error", Toast.LENGTH_SHORT).show();
                        Log.w("firebase", "Error adding document", e);
                    }
                });*/

        //CODIGO PARA REGISTRAR CON ID QUE YO DESEE
        /*db.collection("users").document(identification)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(RegistroActivity.this, "Registro Correcto",
                                Toast.LENGTH_SHORT).show();

                                           }

                   
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistroActivity.this, "No se registró",
                                Toast.LENGTH_SHORT).show();
                    }
                });


    }




    public void limpiarCampos (){
        
        
    }
    //Autentificacion de Usuarios (Registro)
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
                            Toast.makeText(RegistroActivity.this, "Usuario Registrado",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegistroActivity.this, "No se registró",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }*/

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
