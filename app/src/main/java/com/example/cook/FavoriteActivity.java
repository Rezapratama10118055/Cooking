package com.example.cook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Filter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Priority;
import com.example.cook.API.ApiResep;
import com.example.cook.API.ApiService;
import com.example.cook.API.ApibaseUrl;
import com.example.cook.Adapter.AdapterPencarian;
import com.example.cook.Adapter.Adapterviewdata;
import com.example.cook.Model.pencarian.Example;
import com.example.cook.Model.pencarian.Result;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoriteActivity extends AppCompatActivity  {
    private TextView pencarian;
    private Button btnpencarian;
    private RecyclerView recyclerViewpencarian;
    private RecyclerView.Adapter Adapter;
    private SearchView searchResep;
    private RecyclerView.LayoutManager layoutManager;
    private ApiService apiService;
    private com.example.cook.Model.pencarian.Example item_makans;
    String query;
    AdapterPencarian adapterPencarian;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerViewpencarian = findViewById(R.id.recPencarian);
        recyclerViewpencarian.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewpencarian.setHasFixedSize(true);
        recyclerViewpencarian.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
      
//         TampilDataResep("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pencarian, menu);
        MenuItem searchItem = menu.findItem(R.id.pencariandata);

//        SearchView searchView = null;
//        if (searchItem != null) {
//            searchView = (SearchView) searchItem.getActionView();
//        }
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return true;
//
//            }
//        });
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.pencariandata).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String quer) {
                TampilDataResep(quer);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
//                TampilDataResep(s);
                return false;
            }
        });
    return true;
    }

    public void  TampilDataResep(String query){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApibaseUrl.URL_ROOT_HTTP).addConverterFactory(GsonConverterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<com.example.cook.Model.pencarian.Example>call = apiService.getDataCariResep(query);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("berhasil",response.body().toString());
                com.example.cook.Model.pencarian.Example item_makans = response.body();
                adapterPencarian = new AdapterPencarian(item_makans,FavoriteActivity.this);
                Log.e("berhasil",item_makans.toString());
                recyclerViewpencarian.setAdapter(adapterPencarian);
                adapterPencarian.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("gagal",t.getLocalizedMessage());
                 Toast.makeText(FavoriteActivity.this,"Error On"+t.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    }
