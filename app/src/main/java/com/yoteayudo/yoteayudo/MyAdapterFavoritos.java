package com.yoteayudo.yoteayudo;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapterFavoritos extends FirebaseRecyclerAdapter<MyModelFavoritos, MyAdapterFavoritos.myviewholder> {
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    public MyAdapterFavoritos(@NonNull FirebaseRecyclerOptions<MyModelFavoritos> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull MyModelFavoritos model) {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        String id = mAuth.getCurrentUser().getUid();

        holder.tvFavTipoModelo.setText(model.getTipo() + " " + model.getModelo());
        holder.tvPrecio.setText(model.getPrecio());
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("TiendaFavoritos").child(id).child(getRef(position).getKey()).removeValue();

            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_single_item_view_favoritos, parent, false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView tvFavTipoModelo, tvPrecio;
        ImageView btnEliminar;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            tvFavTipoModelo = itemView.findViewById(R.id.tvFavTipoModelo);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }

}
