package com.example.cook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cook.Model.Result;
import com.example.cook.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterPencarian extends RecyclerView.Adapter<AdapterPencarian.ViewHolder> implements Filterable {
    List<Result> examples;
    List<Result> examplesfull;
    private Context context;

    public AdapterPencarian(List<Result>example){
        examples = example;
        examplesfull = new ArrayList<>(example);
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

//        holder.JudulMeme.setText(examples.get(position).getKey());
//
//        Glide.with(context)
//                .asBitmap()
//                .load(examples.get(position).getThumb())
//                .override(200)
//                .into(holder.Meme);
        Result food = examples.get(position);

        holder.bind(food);


    }

    @Override
    public int getItemCount() {
        return examples.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView JudulMeme;
        private ImageView Meme;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            JudulMeme = itemView.findViewById(R.id.Judul_pencarian);
            Meme = itemView.findViewById(R.id.pencarian_gambar);
        }

        public void bind(Result food) {
//            this.itemView.setId(food);
//            itemView.executePendingBindings();
        }
    }

    @Override
    public Filter getFilter() {
        return examplesfilter;
    }
    private Filter examplesfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Result>filterlist = new ArrayList<>();
            if (charSequence==null || charSequence.length()==0){
                filterlist.addAll(examplesfull);

            }else {
                String filterpeten = charSequence.toString().toLowerCase().trim();
                for (Result result:examplesfull){
                    if (result.getKey().toLowerCase().contains(filterpeten)){
                        filterlist.add(result);

                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
          examples.clear();
          examples.addAll((List)filterResults.values);
          notifyDataSetChanged();

        }
    };

}
