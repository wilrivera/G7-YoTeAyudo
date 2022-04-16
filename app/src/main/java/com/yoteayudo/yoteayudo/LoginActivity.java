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

public class LoginActivity extends AppCompatActivity {
    EditText etLoginCorreo, etLoginPassword;
    Button btnLoginIngresar, btnLoginCrearCuenta;

    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        etLoginCorreo = findViewById(R.id.etLoginCorreo);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btnLoginIngresar = findViewById(R.id.btnLoginIngresar);
        btnLoginCrearCuenta = findViewById(R.id.btnLoginCrearCuenta);

        mAuth = FirebaseAuth.getInstance();
        btnLoginIngresar.setOnClickListener(view ->{
            loginUser();
        });

        btnLoginCrearCuenta.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this, RegistrarActivity.class));
        });



    }

    private  void loginUser(){
        String email= etLoginCorreo.getText().toString();
        String password =etLoginPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            etLoginCorreo.setError("Debes ingresar un correo");
            etLoginCorreo.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etLoginPassword.setError("Debes ingresar una contraseña");
            etLoginPassword.requestFocus();
        }else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else {
                        Toast.makeText(LoginActivity.this, "Error de autenticación", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /*public void ingresar (View v){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void registrar (View v){
        startActivity(new Intent(this, RegistrarActivity.class));
    }
*/


}