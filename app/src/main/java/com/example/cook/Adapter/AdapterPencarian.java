package com.example.cook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cook.FavoriteActivity;
import com.example.cook.Model.pencarian.Example;
import com.example.cook.Model.pencarian.Result;
import com.example.cook.PenjelasanDetail;
import com.example.cook.R;


import java.util.List;

public class AdapterPencarian extends RecyclerView.Adapter<AdapterPencarian.ViewHolder>{
    com.example.cook.Model.pencarian.Example mData;

    private Context context;

//    public AdapterPencarian(List<com.example.cook.Model.pencarian.Result> example) {
//        mData = example;
//
//    }

    public AdapterPencarian(com.example.cook.Model.pencarian.Example item_makans, Context context) {
        this.mData = item_makans;
        this.context = context;

    }


    @NonNull
    @Override
    public AdapterPencarian.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pencarian, parent, false);
        return new AdapterPencarian.ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPencarian.ViewHolder holder, int position) {
        holder.Judul.setText(mData.getResults().get(position).getTitle());
        holder.tingkat.setText(mData.getResults().get(position).getDifficulty());
        holder.waktu.setText(mData.getResults().get(position).getTimes());
        holder.porsi.setText(mData.getResults().get(position).getServing());

        Glide.with(context)
                .asBitmap()
                .load(mData.getResults().get(position).getThumb())
                .into(holder.gambarpencarian);

        holder.cffood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdapterPencarian.this.context, PenjelasanDetail.class);
                intent.putExtra("kay",mData.getResults().get(position).getKey());
                intent.putExtra("penjelasanLengkap",mData.getResults().get(position).getTitle());
                intent.putExtra("porsi",mData.getResults().get(position).getServing());
                intent.putExtra("kesulitan",mData.getResults().get(position).getDifficulty());
                intent.putExtra("waktu",mData.getResults().get(position).getTimes());
                intent.putExtra("gambar",mData.getResults().get(position).getThumb());

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.getResults().size();
    }







    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Judul, waktu, tingkat, porsi;
        private ImageView gambarpencarian;
        private CardView cffood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Judul = itemView.findViewById(R.id.jdl);
            gambarpencarian = itemView.findViewById(R.id.hasil_gambar);
            waktu = itemView.findViewById(R.id.waktu_serce);
            tingkat = itemView.findViewById(R.id.tingkat);
            porsi = itemView.findViewById(R.id.porsi);
            cffood = itemView.findViewById(R.id.cvFood);
        }


    }
}
