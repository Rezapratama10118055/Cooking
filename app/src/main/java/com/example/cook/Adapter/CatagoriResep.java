package com.example.cook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cook.ListKatagoriActivity;
import com.example.cook.Model.katagori.Result;
import com.example.cook.PenjelasanDetail;
import com.example.cook.R;

import java.io.Serializable;
import java.util.List;

public class CatagoriResep extends RecyclerView.Adapter<CatagoriResep.ViewHolder> {
    List<com.example.cook.Model.katagori.Result> etresep;
    private Context context;

    public CatagoriResep(List<com.example.cook.Model.katagori.Result> exaResults){
        etresep = exaResults;
    }



    @NonNull
    @Override
    public CatagoriResep.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.katagori_listklik, parent, false);
        return new CatagoriResep.ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull CatagoriResep.ViewHolder holder, int position) {
        Result etResult = etresep.get(position);
        holder.tvkatagori.setText(etResult.getCategory());
        holder.relativeCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatagoriResep.this.context, ListKatagoriActivity.class);
                intent.putExtra("kay",etresep.get(position).getKey());
                intent.putExtra("katagori",etresep.get(position).getCategory());
                intent.putExtra("Url",etresep.get(position).getUrl());
                context.startActivity(intent);

            }
        });
;


    }

    @Override
    public int getItemCount() {
        return etresep.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvkatagori;
        private LinearLayout relativeCategories;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvkatagori = itemView.findViewById(R.id.tvklikcatagory);
            relativeCategories = itemView.findViewById(R.id.relativeCategories);

        }
    }
}
