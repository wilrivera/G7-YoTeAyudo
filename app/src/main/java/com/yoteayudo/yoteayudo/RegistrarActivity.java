package com.yoteayudo.yoteayudo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistrarActivity extends AppCompatActivity {
    EditText etRegistrarNombre, etRegistrarCorreo, etRegistrarPassword;
    Button btnRegistrarCrearCuenta, btnRegistrarYatienesCuenta;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Registrar Cuenta");
        setContentView(R.layout.activity_registrar);
        getSupportActionBar().hide();

        etRegistrarNombre = findViewById(R.id.etRegistrarNombre);
        etRegistrarCorreo = findViewById(R.id.etRegistrarCorreo);
        etRegistrarPassword = findViewById(R.id.etRegistrarPassword);
        btnRegistrarCrearCuenta = findViewById(R.id.btnRegistrarCrearCuenta);
        btnRegistrarYatienesCuenta = findViewById(R.id.btnRegistrarYatienesCuenta);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnRegistrarCrearCuenta.setOnClickListener(view ->{
            createUser();
        });
        btnRegistrarYatienesCuenta.setOnClickListener(view ->{
            startActivity(new Intent(RegistrarActivity.this, LoginActivity.class));
        });

    }
    private void createUser(){
        String email= etRegistrarCorreo.getText().toString();
        String nombre = etRegistrarNombre.getText().toString();
        String password = etRegistrarPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            etRegistrarCorreo.setError("Debes ingresar un correo");
            etRegistrarCorreo.requestFocus();
        }if(TextUtils.isEmpty(nombre)){
            etRegistrarNombre.setError("Debes ingresar un nombre de usuario");
            etRegistrarNombre.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etRegistrarPassword.setError("Debes ingresar una contraseña");
            etRegistrarPassword.requestFocus();
        }else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Map<String, Object> map = new HashMap<>();
                        map.put("email", email);
                        map.put("name", nombre);
                        map.put("password", password);
                        String id = mAuth.getCurrentUser().getUid();
                        mDatabase.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task2) {
                                if(task2.isSuccessful()){
                                    Toast.makeText(RegistrarActivity.this, "Se creó tu cuenta correctamente", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegistrarActivity.this, LoginActivity.class));
                                }else{
                                    Toast.makeText(RegistrarActivity.this, "Problemas para registrar tu cuenta", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }else {
                        Toast.makeText(RegistrarActivity.this, "Problemas para registrar tu cuenta", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    /*public void IrLogin (View v){
        startActivity(new Intent(this, LoginActivity.class));
    }*/
}