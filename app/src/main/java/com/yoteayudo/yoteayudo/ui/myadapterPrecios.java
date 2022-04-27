package com.yoteayudo.yoteayudo.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.yoteayudo.yoteayudo.R;

public class myadapterPrecios extends FirebaseRecyclerAdapter<modelPrecios, myadapterPrecios.myviewholder> {
    public myadapterPrecios(@NonNull FirebaseRecyclerOptions<modelPrecios> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull modelPrecios model) {

        holder.txtDetalleProductoTienda.setText(model.getNombre());
        holder.txtDetalleProductoPrecio.setText(model.getPrecio());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_single_producto_precios, parent, false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        TextView txtDetalleProductoTienda, txtDetalleProductoPrecio;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            txtDetalleProductoTienda = itemView.findViewById(R.id.txtDetalleProductoTienda);
            txtDetalleProductoPrecio = itemView.findViewById(R.id.txtDetalleProductoPrecio);
        }
    }
}
