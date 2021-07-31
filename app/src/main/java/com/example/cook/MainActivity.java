package com.example.cook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.cook.API.ApiResep;
import com.example.cook.API.ApiService;
import com.example.cook.Adapter.Adapterviewdata;
import com.example.cook.Adapter.CatagoriResep;
import com.example.cook.CatatanResep.CatatanResep;
import com.example.cook.Model.Example;
import com.example.cook.Model.Result;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    private TextView waktu, nama, porsi, tingkat,nama1,waktu1,porsi1,tingkat1;
    private RecyclerView recyclerView,recyclerView1;
    private RecyclerView.Adapter Adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ApiService apiService;

    private List<Example> item_makans;
    Adapterviewdata adapter;
    CatagoriResep adpter1;
    public static MainActivity ma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.PopularRec);
        nama = findViewById(R.id.Judul_pencarian);
        waktu = findViewById(R.id.waktu_pencarian);
        porsi = findViewById(R.id.porsi);
        tingkat = findViewById(R.id.tingkat);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        ma=this;

        //recaycle view katagori
        recyclerView1 = findViewById(R.id.CatagoriRec);
        nama1 = findViewById(R.id.Judul_pencarian);
        waktu1 = findViewById(R.id.waktu_pencarian);
        porsi1 = findViewById(R.id.porsi);
        tingkat1 = findViewById(R.id.tingkat);




        LinearLayoutManager layoutManagerHorizontal = new LinearLayoutManager(this);
        layoutManagerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManagerHorizontal);
        recyclerView1.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));





        getResep();
        getCatagori();

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu4);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.pencarian:
                        startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.favorite:
                        startActivity(new Intent(getApplicationContext(), CatatanResep.class));
                        overridePendingTransition(0, 0);
                        return true;

                }

                return false;
            }
        });


    }

    private void getResep() {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApibaseUrl.URL_ROOT_HTTP).addConverterFactory(GsonConverterFactory.create()).build();
//        ApiService apiService = retrofit.create(ApiService.class);
        Call<Example>call = ApiResep.getData().getDataResepTerbaru();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
             if(response.isSuccessful()){
                 Log.e("berhasil",response.body().toString());
                 List<Result> examples = response.body().getResults();
                 adapter = new Adapterviewdata(examples);
                 recyclerView.setAdapter(adapter);


             }
            }

            @Override
            public void onFailure(Call <Example> call, Throwable t) {
                Log.e("gagal",t.getLocalizedMessage());

            }
        });

    }
    private void getCatagori() {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApibaseUrl.URL_ROOT_HTTP).addConverterFactory(GsonConverterFactory.create()).build();
//        ApiService apiService = retrofit.create(ApiService.class);
        Call<com.example.cook.Model.katagori.Example> dua = ApiResep.getData().getkatagori();
        dua.enqueue(new Callback<com.example.cook.Model.katagori.Example>() {
            @Override
            public void onResponse(Call<com.example.cook.Model.katagori.Example> call, Response<com.example.cook.Model.katagori.Example> response) {
                if(response.isSuccessful()){
                    Log.e("berhasil",response.body().toString());
                    List<com.example.cook.Model.katagori.Result> epResults = response.body().getResults();
                    adpter1 = new CatagoriResep(epResults);
                    recyclerView1.setAdapter(adpter1);


                }
            }

            @Override
            public void onFailure(Call <com.example.cook.Model.katagori.Example> call, Throwable t) {
                Log.e("gagal",t.getLocalizedMessage());

            }
        });




        }

//    public void setSearchResep(String query){
//
//    }



}