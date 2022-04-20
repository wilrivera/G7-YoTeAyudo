package com.yoteayudo.yoteayudo.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoteayudo.yoteayudo.MyAdapter;
import com.yoteayudo.yoteayudo.MyModel;
import com.yoteayudo.yoteayudo.R;

import java.util.ArrayList;


public class ProductosFragment extends Fragment {
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_productos, container, false);
        View view = inflater.inflate(R.layout.fragment_productos, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerTiendas);

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
        recyclerView.setAdapter(myAdapter);
        return view;

    }


}