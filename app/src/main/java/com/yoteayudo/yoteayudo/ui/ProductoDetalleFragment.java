package com.yoteayudo.yoteayudo.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yoteayudo.yoteayudo.R;

import java.util.HashMap;
import java.util.Map;


public class ProductoDetalleFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    String tipo, marca, modelo, id, imgProducto, precio;

    RecyclerView recyclerDetallePrecios;
    myadapterPrecios adapter;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    public ProductoDetalleFragment() {

    }

    public ProductoDetalleFragment( String tipo, String marca, String modelo, String id, String imgProducto) {
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.id = id;
        this.imgProducto = imgProducto;

    }


    public static ProductoDetalleFragment newInstance(String param1, String param2) {
        ProductoDetalleFragment fragment = new ProductoDetalleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_producto_detalle, container, false);

        ImageView imgDetalleProducto = view.findViewById(R.id.imgDetalleProducto);
        TextView txtNombreDetalleProducto = view.findViewById(R.id.txtNombreDetalleProducto);
        TextView txtDetalleProductoMarca = view.findViewById(R.id.txtDetalleProductoMarca);
        TextView txtDetalleId = view.findViewById(R.id.txtDetalleId);
        ImageView btnFavorito = view.findViewById(R.id.btnFavorito);

        TextView txtMejorPrecio= view.findViewById(R.id.txtMejorPrecio);

        Glide.with(getContext()).load(imgProducto).into(imgDetalleProducto);
        txtNombreDetalleProducto.setText(String.valueOf(tipo + " " + modelo));
        txtDetalleProductoMarca.setText(String.valueOf(marca));
        txtDetalleId.setText(String.valueOf(id));

        recyclerDetallePrecios = view.findViewById(R.id.recyclerDetallePrecios);
        recyclerDetallePrecios.setLayoutManager(new LinearLayoutManager(getContext()));

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<>();
                map.put("modelo", String.valueOf(modelo));
                map.put("tipo", String.valueOf(tipo));
                map.put("precio", precio);
                String idUsuario = mAuth.getCurrentUser().getUid();
                mDatabase.child("TiendaFavoritos").child(idUsuario).child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Snackbar.make(getView().findViewById(R.id.container_productoDetalle), "Se añadió a favoritos", Snackbar.LENGTH_SHORT).show();
                        } else {
                            Snackbar.make(getView().findViewById(R.id.container_productoDetalle), "No se pudo añadir a favoritos", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        FirebaseRecyclerOptions<modelPrecios> options =
                new FirebaseRecyclerOptions.Builder<modelPrecios>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Productos").child(id).child("tiendas").orderByChild("precio"), modelPrecios.class)
                        .build();

        FirebaseDatabase.getInstance().getReference().child("Productos").child(id).child("tiendas").orderByChild("precio").limitToFirst(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    precio = snapshot1.child("precio").getValue().toString();
                }
                txtMejorPrecio.setText(precio);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new myadapterPrecios(options);
        recyclerDetallePrecios.setAdapter(adapter);

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