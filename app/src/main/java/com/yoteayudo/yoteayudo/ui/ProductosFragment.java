package com.yoteayudo.yoteayudo.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.yoteayudo.yoteayudo.R;
import com.yoteayudo.yoteayudo.modelProductos;
import com.yoteayudo.yoteayudo.myadapterProductos;

import java.util.Locale;


public class ProductosFragment extends Fragment {
    /*private RecyclerView recyclerView;*/

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    RecyclerView recyclerResultadoProductos;
    myadapterProductos adapter;
    SearchView searchViewProductos;

    public ProductosFragment() {
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
        View view = inflater.inflate(R.layout.fragment_productos, container, false);

        recyclerResultadoProductos = (RecyclerView) view.findViewById(R.id.recyclerResultadoProductos);
        recyclerResultadoProductos.setLayoutManager(new LinearLayoutManager(getContext()));
        searchViewProductos = view.findViewById(R.id.searchViewProductos);

        searchViewProductos.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                buscarProducto(s.toLowerCase(Locale.ROOT));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                buscarProducto(s.toLowerCase(Locale.ROOT));
                return false;
            }
        });

        FirebaseRecyclerOptions<modelProductos> options =
                new FirebaseRecyclerOptions.Builder<modelProductos>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Productos"), modelProductos.class)
                        .build();

        adapter = new myadapterProductos(options);
        recyclerResultadoProductos.setAdapter(adapter);

        /*RecyclerView recyclerView = view.findViewById(R.id.recyclerTiendas);

        LinearLayoutManager lmg = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lmg);
        ArrayList<MyModel> models = new ArrayList<>();{
            models.add(new MyModel(R.drawable.ic_procesador,"Procesadores"));
            models.add(new MyModel(R.drawable.ic_ram,"Memorias RAM"));
            models.add(new MyModel(R.drawable.ic_air_cooling,"Refrigeración"));
            models.add(new MyModel(R.drawable.ic_mobo,"Placas Madre"));
            models.add(new MyModel(R.drawable.ic_video_tarjeta,"tarjetas de video"));
        }

        MyAdapter myAdapter = new MyAdapter(models);
        recyclerView.setAdapter(myAdapter);*/

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

    private void buscarProducto(String s) {

        FirebaseRecyclerOptions<modelProductos> options =
                new FirebaseRecyclerOptions.Builder<modelProductos>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Productos").orderByChild("tipo").startAt(s).endAt(s + "\uf8ff"), modelProductos.class)
                        .build();


        adapter = new myadapterProductos(options);
        adapter.startListening();
        recyclerResultadoProductos.setAdapter(adapter);
    }


}