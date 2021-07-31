package com.example.cook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cook.Model.bahan.Results;
import com.example.cook.R;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.ViewHolder> {
    List<String> examples;
    private Context context;


    public StepAdapter(List<String>example){
        examples = example;
    }

    @NonNull
    @Override
    public StepAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_steps, parent, false);
        return new StepAdapter.ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull StepAdapter.ViewHolder holder, int position) {

        holder.step.setText(examples.get(position));
    }

    @Override
    public int getItemCount() {
        return examples.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView step,porsi,waktu,tingkat;
        private ImageView gambar;
        private CardView relativeCategories;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            step = itemView.findViewById(R.id.tvSteps);

        }
    }

}
