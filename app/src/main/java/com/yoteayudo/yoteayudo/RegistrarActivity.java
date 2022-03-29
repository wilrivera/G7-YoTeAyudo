package com.yoteayudo.yoteayudo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegistrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Registrar Cuenta");
        setContentView(R.layout.activity_registrar);
        getSupportActionBar().hide();
    }

    public void IrLogin (View v){
        startActivity(new Intent(this, LoginActivity.class));
    }
}