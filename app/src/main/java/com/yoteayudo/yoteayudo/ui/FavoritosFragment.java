package com.yoteayudo.yoteayudo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yoteayudo.yoteayudo.MyAdapterFavoritos;
import com.yoteayudo.yoteayudo.MyModelFavoritos;
import com.yoteayudo.yoteayudo.R;

import java.util.ArrayList;

public class FavoritosFragment extends Fragment {

    RecyclerView recyclerFavoritos;
    TextView txtFavVacio;
    MyAdapterFavoritos adapter;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);
        recyclerFavoritos = view.findViewById(R.id.recyclerFavoritos);
        recyclerFavoritos.setLayoutManager(new LinearLayoutManager(getContext()));
        txtFavVacio= view.findViewById(R.id.txtFavVacio);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        String id = mAuth.getCurrentUser().getUid();

        Toolbar actionBar= (Toolbar) view.findViewById(R.id.app_bar_main);
        if(((AppCompatActivity)getActivity()).getSupportActionBar() != null){
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Lista de favoritos");
        }



        /*LinearLayoutManager lmg = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lmg);
        ArrayList<MyModelFavoritos> models = new ArrayList<>();{
            models.add(new MyModelFavoritos("Teclado 1","$100 "));
            models.add(new MyModelFavoritos("Procesador i7","$340 "));
            models.add(new MyModelFavoritos("Case FD","$87 "));
        }

        MyAdapterFavoritos myAdapter2 = new MyAdapterFavoritos(models);
        recyclerView.setAdapter(myAdapter2);*/

        FirebaseRecyclerOptions<MyModelFavoritos> options =
                new FirebaseRecyclerOptions.Builder<MyModelFavoritos>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("TiendaFavoritos").child(id), MyModelFavoritos.class)
                        .build();

        adapter = new MyAdapterFavoritos(options);
        recyclerFavoritos.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("TiendaFavoritos").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int conteo = (int) snapshot.getChildrenCount();
                if (conteo==0){
                    txtFavVacio.setText("No tienes artículos en tu lista de favoritos");
                }else{
                    txtFavVacio.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return view;

    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}