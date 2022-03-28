package com.yoteayudo.yoteayudo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TiendasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TiendasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_tiendas, container, false);

        View view = inflater.inflate(R.layout.fragment_productos, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager lmg = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lmg);
        ArrayList<MyModel> models = new ArrayList<>();{
            models.add(new MyModel(R.drawable.sercoplus,"Sercoplus"));
            models.add(new MyModel(R.drawable.totem_hardware,"Totem Hardware"));
        }

        MyAdapter myAdapter = new MyAdapter(models);
        recyclerView.setAdapter(myAdapter);
        return view;
    }
}