package com.momento2cesde.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import Models.ApartamentoModel;

public class ListApartmentsActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView rvApartmentList;
    FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_apartment);
        rvApartmentList = findViewById(R.id.rvApartmentList);

        //query a la base de datos()
        Query query = db.collection("apartments");

        //configurar opciones del adaptador
        FirestoreRecyclerOptions<ApartamentoModel> options = new FirestoreRecyclerOptions.Builder<ApartamentoModel>()
                .setQuery(query, ApartamentoModel.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<ApartamentoModel, ApartamentsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ApartamentsViewHolder holder, int position, @NonNull ApartamentoModel model) {
                //asigna los datos a cada elemento

                holder.tvCiudadApto.setText(model.getCiudad());
                holder.tvPaisApto.setText(model.getPais());
                holder.tvDireccionApto.setText(model.getDireccion());
                holder.tvNumeroHabitaciones.setText(model.getHabitaciones());
                holder.tvValorApto.setText(model.getValor());
                holder.tvReseña.setText(model.getReseña());
                String id = getSnapshots().getSnapshot(position).getId();
                holder.btnDeleteApto.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ListApartmentsActivity.this, "" + id, Toast.LENGTH_SHORT).show();
                        deleteApto(id);

                    }
                });

            }

            @NonNull
            @Override
            public ApartamentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                //crea un elemento grafico por cada id
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single2, parent, false);
                return new ApartamentsViewHolder(view);
            }
        };
        rvApartmentList.setHasFixedSize(true);
        rvApartmentList.setLayoutManager(new LinearLayoutManager(this));
        rvApartmentList.setAdapter(adapter);
    }

    private class ApartamentsViewHolder extends RecyclerView.ViewHolder{

        TextView tvCiudadApto,tvPaisApto,tvDireccionApto,tvValorApto,tvReseña,tvNumeroHabitaciones;
        Button btnDeleteApto, btnEditApto;

        public ApartamentsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCiudadApto = itemView.findViewById(R.id.tvCiudadApto);
            tvPaisApto = itemView.findViewById(R.id.tvPaisApto);
            tvDireccionApto = itemView.findViewById(R.id.tvDireccionApto);
            tvValorApto = itemView.findViewById(R.id.tvValorApto);
            tvReseña = itemView.findViewById(R.id.tvReseña);
            tvNumeroHabitaciones = itemView.findViewById(R.id.tvNumeroHabitaciones);
            btnDeleteApto = itemView.findViewById(R.id.btnDeleteApto);
            btnEditApto = itemView.findViewById(R.id.btnEditApto);
        }
    }

    public void deleteApto(String id) {
        db.collection("apartments").document((id))
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ListApartmentsActivity.this, "Documento Eliminado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ListApartmentsActivity.this, " Error", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
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