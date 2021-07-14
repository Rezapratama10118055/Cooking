package com.example.cook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cook.API.ApiResep;
import com.example.cook.API.ApiService;
import com.example.cook.Adapter.BahanAdapter;
import com.example.cook.Adapter.StepAdapter;
import com.example.cook.Model.bahan.Example;
import com.example.cook.Model.bahan.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PenjelasanDetail extends AppCompatActivity {

    private TextView Judul, namaPembuat, porsi, tingkat, langkah, bahanbahan, porsi1, tingkat1;
    private RecyclerView recyclerViewBahan, recyclerViewStep;
    private RecyclerView.Adapter Adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ApiService apiService;
    String bg;
    ImageView imgCover, imgPhoto;
    private List<Example> item_makans;
    private List<Results> bahan;
    BahanAdapter Bahanadapter1;
    StepAdapter Stepadpter;
    String nama_judul;
    String kay ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipes);

        //detailActivity
        recyclerViewBahan = findViewById(R.id.rvIngredients);
        recyclerViewStep = findViewById(R.id.rvSteps);
        namaPembuat = findViewById(R.id.tvAuthor);
        Judul = findViewById(R.id.tvTitleRecipe);
        imgCover = findViewById(R.id.gambartampil);
        langkah = findViewById(R.id.tvIngredients);
        bahanbahan = findViewById(R.id.tvSteps);

//        porsi1 = findViewById(R.id.porsi);
//        tingkat1 = findViewById(R.id.tingkat);





//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        Results results = (Results) bundle.getSerializable("penjelasanLengkap");



            //rvingredients
            recyclerViewBahan.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewBahan.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
            //rvStep
            recyclerViewStep.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewStep.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

            //data rest api
            TextView name = findViewById(R.id.tvTitleRecipe);
            name.setText(getIntent().getStringExtra("penjelasanLengkap"));
            TextView durasi = findViewById(R.id.tvTimes);
             durasi.setText(getIntent().getStringExtra("waktu"));
            TextView porsi = findViewById(R.id.tvPortion);
             porsi.setText(getIntent().getStringExtra("porsi"));
             TextView kesulitan = findViewById(R.id.tvDificulty);
            kesulitan.setText(getIntent().getStringExtra("kesulitan"));
            kay = getIntent().getStringExtra("kay");


        
//
//        Glide.with(this)
//                .load(ApiResep.getData().getDataResepDetail() + bg)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imgCover);


            getDetail();








        }


        private void getDetail(){
            Call<com.example.cook.Model.bahan.Example> call = ApiResep.getData().getDataResepDetail(kay);
            call.enqueue(new Callback<com.example.cook.Model.bahan.Example>() {
                @Override
                public void onResponse(Call<com.example.cook.Model.bahan.Example> call, Response<com.example.cook.Model.bahan.Example> response) {
                    if(response.isSuccessful()){
                        Log.e("berhasil",response.body().toString());
                        com.example.cook.Model.bahan.Results examples = response.body().getResults();
                        bahanbahan.setText(response.body().getResults().getIngredient().get(0));
                        Bahanadapter1 = new BahanAdapter(examples);
                        recyclerViewBahan.setAdapter(Bahanadapter1);


                    }
                }

                @Override
                public void onFailure(Call <com.example.cook.Model.bahan.Example> call, Throwable t) {
                    Log.e("gagal",t.getLocalizedMessage());

                }
            });


        }

//    private void getDetailLangkah(){
//        Call<com.example.cook.Model.bahan.Example> call = ApiResep.getData().getDataResepDetail("key");
//        call.enqueue(new Callback<com.example.cook.Model.bahan.Example>() {
//            @Override
//            public void onResponse(Call<com.example.cook.Model.bahan.Example> call, Response<com.example.cook.Model.bahan.Example> response) {
//                if(response.isSuccessful()){
//                    Log.e("berhasil",response.body().toString());
//                    List<com.example.cook.Model.bahan.Results> examples = response.body().getResults();
//                    langkah.setText(response.body().getResults().get(0).getStep());
//                    Stepadpter = new StepAdapter(examples);
//                    recyclerViewBahan.setAdapter(Stepadpter);
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call <com.example.cook.Model.bahan.Example> call, Throwable t) {
//                Log.e("gagal",t.getLocalizedMessage());
//
//            }
//        });
//

    }


