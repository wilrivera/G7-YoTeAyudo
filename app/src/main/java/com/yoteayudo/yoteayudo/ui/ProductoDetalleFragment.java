package com.yoteayudo.yoteayudo.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.yoteayudo.yoteayudo.R;


public class ProductoDetalleFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    String tipo, marca, modelo, id, imgProducto;

    RecyclerView recyclerDetallePrecios;
    myadapterPrecios adapter;

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

        Glide.with(getContext()).load(imgProducto).into(imgDetalleProducto);
        txtNombreDetalleProducto.setText(String.valueOf(tipo + " " + modelo));
        txtDetalleProductoMarca.setText(String.valueOf(marca));
        txtDetalleId.setText(String.valueOf(id));

        recyclerDetallePrecios = view.findViewById(R.id.recyclerDetallePrecios);
        recyclerDetallePrecios.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<modelPrecios> options =
                new FirebaseRecyclerOptions.Builder<modelPrecios>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Productos").child(id).child("tiendas"), modelPrecios.class)
                        .build();

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