package com.yoteayudo.yoteayudo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.myViewHolder> {
    private ArrayList<MyModel2> model2s;

    public MyAdapter2(ArrayList<MyModel2> model2s) {
        this.model2s = model2s;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_single_item_view_favoritos, parent, false);


        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.Name.setText(model2s.get(position).getName());
        holder.Details.setText(model2s.get(position).getDetails());

    }

    @Override
    public int getItemCount() {
        return model2s.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView Name, Details;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.tvProductoNombre);
            Details = itemView.findViewById(R.id.tvPrecio);

        }
    }

}
