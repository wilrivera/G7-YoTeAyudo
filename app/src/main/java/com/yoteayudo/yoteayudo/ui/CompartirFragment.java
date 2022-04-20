package com.yoteayudo.yoteayudo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.yoteayudo.yoteayudo.R;

public class CompartirFragment extends Fragment {

    private Button btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_compartir, container, false);
        View view =  inflater.inflate(R.layout.fragment_compartir, container, false);

        Toolbar actionBar= (Toolbar) view.findViewById(R.id.app_bar_main);
        if(((AppCompatActivity)getActivity()).getSupportActionBar() != null){
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Compartir");
        }


        btn= view.findViewById(R.id.btncompartir);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compartirApp();
            }
        });
        return view;
    }
    private void compartirApp() {
        try {

            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/text");
            i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
            String aux = "Descarga nuestra app\n";
            //aux = aux + "https://play.google.com/store/apps/de..."+getBaseContext().getPackageName();
            aux = aux + "https://www.yoteayudoapp.com";
            i.putExtra(Intent.EXTRA_TEXT, aux);
            startActivity(i);
        }catch (Exception e) {
        }
    }

}