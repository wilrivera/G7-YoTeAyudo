package com.yoteayudo.yoteayudo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.yoteayudo.yoteayudo.ui.ProductosFragment;


public class BuscarCompararActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_comparar);
        getSupportActionBar().setTitle("Buscar y comparar");


        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(viewPager);
        ViewPageAdapter vpAdapter = new ViewPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new ProductosFragment(), "Productos");
        vpAdapter.addFragment(new TiendasFragment(), "Tiendas");
        viewPager.setAdapter(vpAdapter);
    }
}