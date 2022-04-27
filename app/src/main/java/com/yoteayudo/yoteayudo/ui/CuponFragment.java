package com.yoteayudo.yoteayudo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.yoteayudo.yoteayudo.R;

import java.util.Random;

public class CuponFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cupon, container, false);

        Button btn_Generate;
        TextView edt_Questions;

        Toolbar actionBar= (Toolbar) view.findViewById(R.id.app_bar_main);
        if(((AppCompatActivity)getActivity()).getSupportActionBar() != null){
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Generar cupón");
        }

        btn_Generate=(Button) view.findViewById(R.id.btn_Generate);
        edt_Questions= (TextView) view.findViewById(R.id.edt_Questions);

        final String questions[] ={
                "T2022-001",
                "T2022-002",
                "T2022-003",
                "T2022-004",
                "T2022-005",
                "T2022-006"
        };

        btn_Generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int Questions = rand.nextInt(  6);

                edt_Questions.setText(questions[Questions]);
            }
        });

        return view;
    }

}