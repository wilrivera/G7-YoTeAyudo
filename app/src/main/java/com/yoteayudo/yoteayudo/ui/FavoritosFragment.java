package com.yoteayudo.yoteayudo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yoteayudo.yoteayudo.MyAdapter2;
import com.yoteayudo.yoteayudo.MyModel2;
import com.yoteayudo.yoteayudo.R;

import java.util.ArrayList;

public class FavoritosFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerTiendas);
        Toolbar actionBar= (Toolbar) view.findViewById(R.id.app_bar_main);
        if(((AppCompatActivity)getActivity()).getSupportActionBar() != null){
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Lista de favoritos");
        }

        LinearLayoutManager lmg = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lmg);
        ArrayList<MyModel2> models = new ArrayList<>();{
            models.add(new MyModel2("Teclado 1","$100 "));
            models.add(new MyModel2("Procesador i7","$340 "));
            models.add(new MyModel2("Case FD","$87 "));
        }

        MyAdapter2 myAdapter2 = new MyAdapter2(models);
        recyclerView.setAdapter(myAdapter2);
        return view;

    }
}