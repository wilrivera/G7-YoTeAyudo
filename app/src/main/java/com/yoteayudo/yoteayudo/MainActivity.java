package com.yoteayudo.yoteayudo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.yoteayudo.yoteayudo.ui.BuscarCompararFragment;
import com.yoteayudo.yoteayudo.ui.CompartirFragment;
import com.yoteayudo.yoteayudo.ui.CuponFragment;
import com.yoteayudo.yoteayudo.ui.FavoritosFragment;
import com.yoteayudo.yoteayudo.ui.PerfilFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //comentario prueba nuevo repo

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById((R.id.nav_view));
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_perfil, R.id.nav_cupon, R.id.nav_favoritos, R.id.nav_compartir, R.id.nav_buscarcomparar)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int mId = item.getItemId();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        if (mId == R.id.nav_perfil) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, new PerfilFragment()).addToBackStack(null).commit();
        }if (mId == R.id.nav_buscarcomparar) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, new BuscarCompararFragment()).addToBackStack(null).commit();

        }if (mId == R.id.nav_cupon) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, new CuponFragment()).addToBackStack(null).commit();

        }if (mId == R.id.nav_favoritos) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, new FavoritosFragment()).addToBackStack(null).commit();

        }if (mId == R.id.nav_compartir) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, new CompartirFragment()).addToBackStack(null).commit();

        }else if (mId == R.id.nav_cerrarsesion) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        drawer.closeDrawers();
        return true;
    }
}