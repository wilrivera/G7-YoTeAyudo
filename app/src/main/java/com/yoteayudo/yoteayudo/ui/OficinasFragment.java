package com.yoteayudo.yoteayudo.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yoteayudo.yoteayudo.R;

import java.util.ArrayList;
import java.util.List;

public class OficinasFragment extends Fragment implements OnMapReadyCallback{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    DatabaseReference mDatabase;
    Double latitud1, longitud1, latitud2, longitud2, latitud3, longitud3;
    List<Marker> listaMarcadores = new ArrayList<>();
    GoogleMap mMap;


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
        // TextView textView11 = view.findViewById(R.id.textView11);

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map2);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        setupUI();
        myInterfaz();

    }

    private void myInterfaz() {
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

                LatLng sucursal1 = new LatLng(latitud1, longitud1);
                LatLng sucursal2 = new LatLng(latitud2, longitud2);
                LatLng sucursal3 = new LatLng(latitud3, longitud3);

                MarkerOptions markerOptions1 = new MarkerOptions();
                MarkerOptions markerOptions2 = new MarkerOptions();
                MarkerOptions markerOptions3 = new MarkerOptions();

                markerOptions1.position(sucursal1)
                        .title("oficina 1");
                markerOptions2.position(sucursal2)
                        .title("oficina 2");
                markerOptions3.position(sucursal3)
                        .title("oficina 3");

                Marker marker1 = mMap.addMarker(markerOptions1);
                Marker marker2 = mMap.addMarker(markerOptions2);
                Marker marker3= mMap.addMarker(markerOptions3);

                listaMarcadores.add(marker1);
                listaMarcadores.add(marker2);
                listaMarcadores.add(marker3);

                centrarMarcadores(listaMarcadores);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void centrarMarcadores(List<Marker> listaMarcadores) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : listaMarcadores) {
            builder.include(marker.getPosition());
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 150));
    }
    private void setupUI() {

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
    }
}