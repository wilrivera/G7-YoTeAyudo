package com.yoteayudo.yoteayudo.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.yoteayudo.yoteayudo.R;

public class myadapter extends FirebaseRecyclerAdapter<model, myadapter.myviewholder> {

    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myadapter.myviewholder holder, int position, @NonNull model model) {
        holder.txtNombre.setText(model.getNombre());
        holder.txtDireccion.setText(model.getDireccion());
        holder.txtHoraFin.setText(model.getHoraFin());
        holder.txtHoraInicio.setText(model.getHoraInicio());
        holder.txtWeb.setText(model.getWeb());
        holder.txtLatitud.setText(String.valueOf(model.getLatitud()));
        holder.txtLongitud.setText(String.valueOf(model.getLongitud()));
        holder.txtTelefono.setText(String.valueOf(model.getTelefono()));
        Glide.with(holder.imgLogo.getContext()).load(model.getLogo()).into(holder.imgLogo);
        Glide.with(holder.imgTienda.getContext()).load(model.getImgTienda()).into(holder.imgTienda);

        holder.recycler_container.setOnClickListener(view -> {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                    new DetalleTiendaFragment(model.getNombre(), model.getDireccion(), model.getHoraFin(),
                            model.getHoraInicio(), model.getWeb(), model.getLatitud(), model.getLongitud(),
                            model.getTelefono(), model.getImgTienda(), model.getLogo())).addToBackStack(null).commit();
        });


    }

    @NonNull
    @Override
    public myadapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_fragment_tiendas, parent, false);
        return new myviewholder(view);
    }
    public class myviewholder extends RecyclerView.ViewHolder {

        ImageView imgLogo, imgTienda;
        View recycler_container;
        ImageButton imgTiendasNext;
        TextView txtWeb, txtLatitud, txtNombre, txtHoraInicio, txtLongitud, txtDireccion,
                txtHoraFin, txtTelefono;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            imgLogo = itemView.findViewById(R.id.imgTiendasLogo);
            imgTienda = itemView.findViewById(R.id.imgTiendasTiendas);
            txtWeb = itemView.findViewById(R.id.txtTiendasWeb);
            txtLatitud = itemView.findViewById(R.id.txtTiendasLatitud);
            txtNombre = itemView.findViewById(R.id.txtTiendasNombre);
            txtHoraInicio = itemView.findViewById(R.id.txtTiendasHoraInicio);
            txtLongitud = itemView.findViewById(R.id.txtTiendasLongitud);
            txtDireccion = itemView.findViewById(R.id.txtTiendasDireccion);
            txtHoraFin = itemView.findViewById(R.id.txtTiendasHoraFin);
            txtTelefono = itemView.findViewById(R.id.txtTiendasTelefono);
            imgTiendasNext = itemView.findViewById(R.id.imgTiendasNext);
            recycler_container = itemView.findViewById(R.id.recycler_container);
        }
    }
}
