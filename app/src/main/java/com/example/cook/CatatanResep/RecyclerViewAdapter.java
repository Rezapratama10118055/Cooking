package com.example.cook.CatatanResep;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cook.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<data_resep> listDataresep;
    private Context context;
    dataListener listener;

    public RecyclerViewAdapter(ArrayList<data_resep> listData, Context context) {
        this.listDataresep = listData;
        this.context = context;
        listener = (mylist)context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView datajudulresep, databahanresep, datalangkahresep;
        private LinearLayout ListItem;

        ViewHolder(View itemView) {
            super(itemView);
            //Menginisialisasi View-View yang terpasang pada layout RecyclerView kita
            datajudulresep = itemView.findViewById(R.id.judullagi);
            databahanresep = itemView.findViewById(R.id.bahanlagi);
            datalangkahresep = itemView.findViewById(R.id.langkahlagi);
            ListItem = itemView.findViewById(R.id.list_item);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String judulresep = listDataresep.get(position).getJudulresep();
        final String bahanbahannya = listDataresep.get(position).getBahanbahannya();
        final String langkahlangkah = listDataresep.get(position).getLangkahlangkah();

        holder.datajudulresep.setText("Judul Resep: " + judulresep);
        holder.databahanresep.setText("Bahan Resep: " + bahanbahannya);
        holder.datalangkahresep.setText("Langkah Resep: " + langkahlangkah);

        holder.ListItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final String[] action = {"Update", "Delete"};
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setItems(action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                        /*
                          Berpindah Activity pada halaman layout updateData
                          dan mengambil data pada listMahasiswa, berdasarkan posisinya
                          untuk dikirim pada activity selanjutnya
                        */
                                Bundle bundle = new Bundle();
                                bundle.putString("dataJudulResep", listDataresep.get(position).getJudulresep());
                                bundle.putString("dataBahanResep", listDataresep.get(position).getBahanbahannya());
                                bundle.putString("dataLangkahResep", listDataresep.get(position).getLangkahlangkah());
                                bundle.putString("getPrimaryKey", listDataresep.get(position).getKey());
                                Intent intent = new Intent(view.getContext(), updateData.class);
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                                break;
                            case 1:
                                //Menggunakan interface untuk mengirim data mahasiswa, yang akan dihapus
                                listener.onDeleteData(listDataresep.get(position), position);
                                break;

                        }
                    }
                });
                alert.create();
                alert.show();
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        return listDataresep.size();
    }

    //Membuat Interfece
    public interface dataListener{
        void onDeleteData(data_resep data, int position);
    }

}
