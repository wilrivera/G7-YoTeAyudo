package com.yoteayudo.yoteayudo.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yoteayudo.yoteayudo.R;

public class OficinasFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    DatabaseReference mDatabase;
    SupportMapFragment mapFragment;
    Double latitud1, longitud1, latitud2, longitud2, latitud3, longitud3;

    public OficinasFragment() {

    }

    public static OficinasFragment newInstance(String param1, String param2) {
        OficinasFragment fragment = new OficinasFragment();
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

        View view = inflater.inflate(R.layout.fragment_oficinas, container, false);

        Toolbar actionBar= (Toolbar) view.findViewById(R.id.app_bar_main);
        if(((AppCompatActivity)getActivity()).getSupportActionBar() != null){
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Nuestras oficinas");
        }

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Oficinas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                latitud1 = (Double) snapshot.child("oficina1").child("latitud").getValue();
                longitud1 = (Double) snapshot.child("oficina1").child("longitud").getValue();

                latitud2 = (Double) snapshot.child("oficina2").child("latitud").getValue();
                longitud2 = (Double) snapshot.child("oficina2").child("longitud").getValue();

                latitud3 = (Double) snapshot.child("oficina3").child("latitud").getValue();
                longitud3 = (Double) snapshot.child("oficina3").child("longitud").getValue();
                SupportMapFragment supportMapFragment = (SupportMapFragment)
                        getChildFragmentManager().findFragmentById(R.id.map2);

                supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        googleMap.getUiSettings().setZoomControlsEnabled(true);
                        googleMap.setTrafficEnabled(true);

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(latitud1, longitud1)).title("Oficina1"));

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(latitud2, longitud2)).title("Oficina 2"));

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(latitud3, longitud3)).title("Oficina 3"));

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitud1, longitud1), 15));
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
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        return view;
    }
}