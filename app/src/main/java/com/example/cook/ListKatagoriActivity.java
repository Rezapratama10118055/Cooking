package com.example.cook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cook.API.ApiResep;
import com.example.cook.Adapter.Adapterviewdata;
import com.example.cook.Adapter.BahanAdapter;
import com.example.cook.Adapter.CatagoriResep;
import com.example.cook.Adapter.ListkatagoriAdapter;
import com.example.cook.Adapter.StepAdapter;
import com.example.cook.Model.katagori.Example;
import com.example.cook.Model.katagori.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListKatagoriActivity extends AppCompatActivity {
    private RecyclerView recyclerTampilKatagori;
    String kay ;
    ListkatagoriAdapter adapterKatagori;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_katagori);

        //detailActivity
        recyclerTampilKatagori = findViewById(R.id.rvlistkatagori);

        //rvingredients
        recyclerTampilKatagori.setLayoutManager(new LinearLayoutManager(this));
        recyclerTampilKatagori.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        TextView name = findViewById(R.id.tvTitle);
        name.setText(getIntent().getStringExtra("katagori"));
//        TextView durasi = findViewById(R.id.tvTimes);
//        durasi.setText(getIntent().getStringExtra("waktu"));
//        TextView porsi = findViewById(R.id.tvPortion);
//        porsi.setText(getIntent().getStringExtra("porsi"));
//        TextView kesulitan = findViewById(R.id.tvDificulty);
//        kesulitan.setText(getIntent().getStringExtra("kesulitan"));
//        ImageView gambar = findViewById(R.id.gambarni);
//        gambar.setImageURI(Uri.parse(getIntent().getStringExtra("gambar")));

        kay = getIntent().getStringExtra("kay");

        getTampildataKatagori();


    }
    private void getTampildataKatagori(){
        Call<com.example.cook.Model.KatagoriList.Example> call = ApiResep.getData().getkatagoricari(kay);
        call.enqueue(new Callback<com.example.cook.Model.KatagoriList.Example>() {
            @Override
            public void onResponse(Call<com.example.cook.Model.KatagoriList.Example> call, Response<com.example.cook.Model.KatagoriList.Example> response) {
                if (response.isSuccessful()) {
                    Log.e("berhasil", response.body().toString());
                    List<com.example.cook.Model.KatagoriList.Result> resep = response.body().getResults();
                   adapterKatagori = new ListkatagoriAdapter(resep);
                   recyclerTampilKatagori.setAdapter(adapterKatagori);
                }
            }

            @Override
            public void onFailure(Call<com.example.cook.Model.KatagoriList.Example> call, Throwable t) {

            }
        });

    }
}