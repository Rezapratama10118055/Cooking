package com.example.cook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cook.Model.Result;
import com.example.cook.PenjelasanDetail;
import com.example.cook.R;

import java.io.Serializable;
import java.util.List;

public class ListkatagoriAdapter extends RecyclerView.Adapter<ListkatagoriAdapter.ViewHolder>  {
    List<com.example.cook.Model.Result> resep;
    private Context context;

    public ListkatagoriAdapter(List<com.example.cook.Model.Result> exaResep){
        resep = exaResep;
    }


    @NonNull
    @Override
    public ListkatagoriAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_categories_resep, parent, false);
        return new ListkatagoriAdapter.ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull ListkatagoriAdapter.ViewHolder holder, int position) {
        Result exaResult = resep.get(position);
        holder.judulresep.setText(exaResult.getTitle());
        holder.wakturesep.setText(exaResult.getTimes());
        holder.porsiresep.setText(exaResult.getPortion());
        holder.tingkatresep.setText(exaResult.getDificulty());
        holder.relativeCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ListkatagoriAdapter.this.context, PenjelasanDetail.class);
                intent.putExtra("penjelasanLengkap", (Serializable) ListkatagoriAdapter.this.resep.get(position));
                context.startActivity(intent);
            }
        });

        Glide.with(context)
                .asBitmap()
                .load(resep.get(position).getThumb())
                .into(holder.gambarresep);



    }

    @Override
    public int getItemCount() {
        return resep.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView judulresep,porsiresep,wakturesep,tingkatresep;
        private ImageView gambarresep;
        private CardView relativeCategories;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judulresep = itemView.findViewById(R.id.tvTitleRecipe);
            porsiresep = itemView.findViewById(R.id.tvPortion);
            wakturesep = itemView.findViewById(R.id.tvTimes);
            gambarresep = itemView.findViewById(R.id.gambartampil);
            tingkatresep = itemView.findViewById(R.id.tvDificulty);
            relativeCategories = itemView.findViewById(R.id.cvListRecipe);

        }
    }
}
