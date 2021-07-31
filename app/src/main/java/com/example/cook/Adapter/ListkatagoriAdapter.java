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
import com.example.cook.Model.KatagoriList.Example;
import com.example.cook.Model.KatagoriList.Result;
import com.example.cook.PenjelasanDetail;
import com.example.cook.R;

import java.io.Serializable;
import java.util.List;

public class ListkatagoriAdapter extends RecyclerView.Adapter<ListkatagoriAdapter.ViewHolder>  {
    List<com.example.cook.Model.KatagoriList.Result> resep;
    private Context context;


    public ListkatagoriAdapter(List<com.example.cook.Model.KatagoriList.Result> resep) {
        this.resep = resep;
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

        holder.judulresep.setText(resep.get(position).getTitle());
        holder.wakturesep.setText(resep.get(position).getTimes());
        holder.porsiresep.setText(resep.get(position).getPortion());
        holder.tingkatresep.setText(resep.get(position).getDificulty());
        holder.relativeCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ListkatagoriAdapter.this.context, PenjelasanDetail.class);
                intent.putExtra("kay",resep.get(position).getKey());
                intent.putExtra("penjelasanLengkap",resep.get(position).getTitle());
                intent.putExtra("porsi",resep.get(position).getPortion());
                intent.putExtra("kesulitan",resep.get(position).getDificulty());
                intent.putExtra("waktu",resep.get(position).getTimes());
                intent.putExtra("gambar",resep.get(position).getThumb());

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
            gambarresep = itemView.findViewById(R.id.gambarni);
            tingkatresep = itemView.findViewById(R.id.tvDificulty);
            relativeCategories = itemView.findViewById(R.id.cvListRecipe);

        }
    }
}
