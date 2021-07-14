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

import java.util.List;

public class Adapterviewdata extends RecyclerView.Adapter<Adapterviewdata.ViewHolder>{
    List<Result> examples;
    private Context context;

    public Adapterviewdata(List<Result>example){
         examples = example;
    }




    @NonNull
    @Override
    public Adapterviewdata.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_categories_resep, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterviewdata.ViewHolder holder, int position) {
              Result example = examples.get(position);
//       String judul = example.getResults().get(position).getTitle();
//       String gambar = example.getResults().get(position).getThumb();
//        String duration = example.getResults().get(position).getTimes();
//        String porsi = example.getResults().get(position).getPortion();
//        String kesulitan = example.getResults().get(position).getDificulty();


        holder.judul.setText(example.getTitle());
//        holder.gambar.setImageURI(Uri.parse(examples.get(position).getThumb()));
        holder.waktu.setText(example.getTimes());
        holder.porsi.setText(example.getPortion());
        holder.tingkat.setText(example.getDificulty());
        holder.relativeCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Adapterviewdata.this.context, PenjelasanDetail.class);
                intent.putExtra("kay",example.getKey());
                intent.putExtra("penjelasanLengkap",example.getTitle());
                intent.putExtra("porsi",example.getPortion());
                intent.putExtra("kesulitan",example.getDificulty());
                intent.putExtra("waktu",example.getTimes());
                intent.putExtra("bg",example.getThumb());

                context.startActivity(intent);
            }
        });

        Glide.with(context)
                .asBitmap()
                .load(examples.get(position).getThumb())
                .into(holder.gambar);


    }

    @Override
    public int getItemCount() {
        return examples.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView judul,porsi,waktu,tingkat;
        private ImageView gambar;
        private CardView relativeCategories;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.tvTitleRecipe);
            relativeCategories = itemView.findViewById(R.id.cvListRecipe);
            gambar = itemView.findViewById(R.id.gambartampil);
            porsi = itemView.findViewById(R.id.tvPortion);
            waktu = itemView.findViewById(R.id.tvTimes);
            tingkat = itemView.findViewById(R.id.tvDificulty);
        }
    }

}
