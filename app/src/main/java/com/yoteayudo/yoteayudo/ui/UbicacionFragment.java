package com.yoteayudo.yoteayudo.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yoteayudo.yoteayudo.R;

public class UbicacionFragment extends Fragment {

    Double latitud, longitud;
    String nombre;
    SupportMapFragment mapFragment;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public UbicacionFragment() {
    }

    public UbicacionFragment(Double latitud, Double longitud, String nombre){
        this.latitud= latitud;
        this.longitud= longitud;
        this.nombre= nombre;

    }
    public static UbicacionFragment newInstance(String param1, String param2) {
        UbicacionFragment fragment = new UbicacionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_ubicacion, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment)

                getChildFragmentManager().findFragmentById(R.id.map);

        // Async map

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {


            @Override


            public void onMapReady(GoogleMap googleMap) {


                googleMap.getUiSettings().setZoomControlsEnabled(true);

                googleMap.setTrafficEnabled(true);


                googleMap.addMarker(new MarkerOptions()

                        .position(new LatLng(latitud, longitud))

                        .title(nombre));


                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitud, longitud), 15));

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {


                    @Override

                    public void onMapClick(LatLng latLng) {

                        // When clicked on map

                        // Initialize marker options

                        MarkerOptions markerOptions = new MarkerOptions();

                        // Set position of marker

                        markerOptions.position(latLng);

                        // Set title of marker

                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                        // Remove all


                        googleMap.clear();

                        // Animating to zoom the marker

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                        // Add marker on map

                        googleMap.addMarker(markerOptions);

                    }

                });

            }

        });

        return view;
    }
}