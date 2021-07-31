package com.example.cook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.example.cook.realm.RealmHelper;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PenjelasanDetail extends AppCompatActivity {

    private TextView Judul, namaPembuat, porsi, tingkat, langkah, bahanbahan, tvbahan, tvpeople, tvdatapublis,tvdeskripsi;
    private RecyclerView recyclerViewBahan, recyclerViewStep;
    private RecyclerView.Adapter Adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ApiService apiService;
    String bg;
    ImageView imgCover;
    MaterialFavoriteButton imgFavorite;
    private List<Example> item_makans;
    private List<com.example.cook.Model.Result> res;
    BahanAdapter Bahanadapter1;
    StepAdapter Stepadpter;
    String nama_judul,nama_porsi,nama_tingkat,nama_waktu;
    String kay,img ;
    RealmHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipes);

        //detailActivity
        recyclerViewBahan = findViewById(R.id.rvIngredients);
        recyclerViewStep = findViewById(R.id.rvSteps);
        namaPembuat = findViewById(R.id.tvAuthor);
        Judul = findViewById(R.id.tvTitleRecipe);
        imgCover = findViewById(R.id.gambarni);
        langkah = findViewById(R.id.tvIngredients);
        bahanbahan = findViewById(R.id.tvSteps);
        imgFavorite = findViewById(R.id.imgFavorite);


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
            ImageView gambar = findViewById(R.id.gambarni);
            gambar.setImageURI(Uri.parse(getIntent().getStringExtra("gambar")));

        kay = getIntent().getStringExtra("kay");
            tvpeople = findViewById(R.id.tvAuthor);
            tvdatapublis = findViewById(R.id.tvDate);
            tvdeskripsi = findViewById(R.id.tvDesc);





        

        Glide.with(this)
                .load("https://www.masakapahariini.com/" + gambar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(gambar);


            getDetail();


//        imgFavorite.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
//
//            @Override
//            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
//                if (favorite){
//                    kay = res.getKey();
//                    nama_judul = res.get(0).getTitle();
//                    nama_porsi = res.get(0).getPortion();
//                    nama_waktu = res.get(0).getTimes();
//                    nama_tingkat = res.get(0).getDificulty();
//                    img = res.get(0).getThumb();
//                    helper.addFavoriteMovie(kay, nama_judul, img, nama_waktu, nama_tingkat,nama_porsi);
//                    Snackbar.make(buttonView, res.get(0).getTitle() + " Added to Favorite",
//                            Snackbar.LENGTH_SHORT).show();
//
//
//            } else {
//                    helper.deleteFavoriteMovie(res.get(0).getKey());
//                    Snackbar.make(buttonView, res.get(0).getTitle() + " Removed from Favorite",
//                            Snackbar.LENGTH_SHORT).show();
//                }
//        }
//
//                });





        }


        private void getDetail(){
            Call<com.example.cook.Model.bahan.Example> call = ApiResep.getData().getDataResepDetail(kay);
            call.enqueue(new Callback<com.example.cook.Model.bahan.Example>() {
                @Override
                public void onResponse(Call<com.example.cook.Model.bahan.Example> call, Response<com.example.cook.Model.bahan.Example> response) {
                    if(response.isSuccessful()){
                        Log.e("berhasil",response.body().toString());




                            List<String> examples = response.body().getResults().getIngredient();
                            List<String> eStrings = response.body().getResults().getStep();
//                            bahanbahan.setText(response.body().getResults().getIngredient().get(0));

                            Bahanadapter1 = new BahanAdapter(examples);
                            recyclerViewBahan.setAdapter(Bahanadapter1);

                            Stepadpter = new StepAdapter(eStrings);
                            recyclerViewStep.setAdapter(Stepadpter);
//                        tvbahan.setText(response.body().getResults().getIngredient().get(0));
                        tvpeople.setText(response.body().getResults().getAuthor().getUser());
                        tvdatapublis.setText(response.body().getResults().getAuthor().getDatePublished());
                        tvdeskripsi.setText(response.body().getResults().getDesc());


                        }
                    }


                @Override
                public void onFailure(Call <com.example.cook.Model.bahan.Example> call, Throwable t) {
                    Log.e("gagal",t.getLocalizedMessage());

                }
            });


        }



    }


