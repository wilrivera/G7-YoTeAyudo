package com.yoteayudo.yoteayudo.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yoteayudo.yoteayudo.R;


public class DetalleTiendaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    String nombre, direccion, horaFin, horaInicio, web, imgTienda, logo;
    Double latitud, longitud;
    Integer telefono;

    public DetalleTiendaFragment() {
    }

    public DetalleTiendaFragment(String nombre, String direccion, String horaFin, String horaInicio,
                        String web, Double latitud, Double longitud, Integer telefono,
                        String imgTienda, String logo) {

        this.nombre = nombre;
        this.direccion = direccion;
        this.horaFin = horaFin;
        this.horaInicio = horaInicio;
        this.web = web;
        this.imgTienda = imgTienda;
        this.logo = logo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.telefono = telefono;


    }

    public static DetalleTiendaFragment newInstance(String param1, String param2) {
        DetalleTiendaFragment fragment = new DetalleTiendaFragment();
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
        View view = inflater.inflate(R.layout.fragment_detalle_tienda, container, false);

        ImageView imgDetalleTienda = view.findViewById(R.id.imgDetalleTienda);
        //ImageView imgDetalleLogo = view.findViewById(R.id.imgDetalleLogo);
        TextView txtTelefono = view.findViewById(R.id.txtDetalleTelefono);
        TextView txtDireccion = view.findViewById(R.id.txtDetalleDireccion);
        TextView txtDetalleHoraInicio = view.findViewById(R.id.txtDetalleHorario);
        TextView txtWeb = view.findViewById(R.id.txtDetalleWeb);
        //TextView txtDetalleLatitud = view.findViewById(R.id.txtDetalleLatitud);
        //TextView txtDetalleLongitud = view.findViewById(R.id.txtDetalleLongitud);
        TextView txtNombreTienda = view.findViewById(R.id.txtDetalleNombre);

        Glide.with(getContext()).load(imgTienda).into(imgDetalleTienda);
        //Glide.with(getContext()).load(logo).into(imgDetalleLogo);
        txtTelefono.setText(String.valueOf(telefono));
        txtDireccion.setText(direccion + " (ver en mapa)");
        txtDetalleHoraInicio.setText(horaInicio + " - " + horaFin);
        txtWeb.setText(web);
        //txtDetalleLatitud.setText(String.valueOf(latitud));
        //txtDetalleLongitud.setText(String.valueOf(longitud));
        txtNombreTienda.setText(nombre);

        return view;
    }
}