package com.yoteayudo.yoteayudo.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.yoteayudo.yoteayudo.R;
import com.yoteayudo.yoteayudo.ViewPageAdapter;

public class BuscarCompararFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_pestanias, container, false);
        View view = inflater.inflate(R.layout.fragment_buscarcomparar, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(viewPager);
        ViewPageAdapter vpAdapter = new ViewPageAdapter(getParentFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new ProductosFragment(), "productos");
        vpAdapter.addFragment(new TiendasFragment(), "Tiendas");
        viewPager.setAdapter(vpAdapter);

        return view;
    }
}