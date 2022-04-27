package com.yoteayudo.yoteayudo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.yoteayudo.yoteayudo.ui.ProductoDetalleFragment;

public class myadapterProductos extends FirebaseRecyclerAdapter<modelProductos, myadapterProductos.myviewholder> {

    public myadapterProductos(@NonNull FirebaseRecyclerOptions<modelProductos> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull modelProductos model) {

        holder.txtTabTipoMarca.setText(model.getTipo() + " " + model.getMarca());
        /*holder.txtProductosMarca.setText(model.getMarca());*/
        holder.txtTabModelo.setText(model.getModelo());
        /*holder.txtProductoId.setText(model.getId());*/
        Glide.with(holder.imgTabProducto.getContext()).load(model.getImgProducto()).into(holder.imgTabProducto);

        holder.imgTabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,
                        new ProductoDetalleFragment(model.getTipo(), model.getMarca(), model.getModelo(), model.getId(), model.getImgProducto())).addToBackStack(null).commit();

            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_single_tab_productos, parent, false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder{

        ImageView imgProducto;
        TextView txtProductosTipo, txtProductosMarca, txtProductoModelo, txtProductoId;

        ImageView imgTabProducto;
        TextView txtTabTipoMarca, txtTabModelo;
        ImageView imgTabNext;




        public myviewholder(@NonNull View itemView) {
            super(itemView);

            /*imgProducto = itemView.findViewById(R.id.imgProducto);
            txtProductosTipo = itemView.findViewById(R.id.txtProductosTipo);
            txtProductosMarca = itemView.findViewById(R.id.txtProductosMarca);
            txtProductoModelo = itemView.findViewById(R.id.txtProductoModelo);
            txtProductoId = itemView.findViewById(R.id.txtProductoId);*/

            imgTabProducto = itemView.findViewById(R.id.imgTabProducto);
            txtTabTipoMarca = itemView.findViewById(R.id.txtTabTipoMarca);
            txtTabModelo = itemView.findViewById(R.id.txtTabModelo);
            imgTabNext = itemView.findViewById(R.id.imgTabNext);
        }
    }

}
