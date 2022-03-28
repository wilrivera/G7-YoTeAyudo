package com.yoteayudo.yoteayudo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

    }

    public void ingresar (View v){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void registrar (View v){
        startActivity(new Intent(this, RegistrarActivity.class));
    }

}