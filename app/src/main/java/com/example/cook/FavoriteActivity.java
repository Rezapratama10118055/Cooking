package com.example.cook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.cook.API.ApiResep;
import com.example.cook.API.ApiService;
import com.example.cook.Adapter.AdapterPencarian;
import com.example.cook.Model.Example;
import com.example.cook.Model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteActivity extends AppCompatActivity {
    private TextView pencarian;
    private Button  btnpencarian;
    private RecyclerView recpencarian;
    private RecyclerView.Adapter Adapter;
  
    private RecyclerView.LayoutManager layoutManager;
    private ApiService apiService;
    private List<Example> item_makans;
    AdapterPencarian adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recpencarian = findViewById(R.id.recPencarian);
        pencarian = findViewById(R.id.txt_pencarian);
        btnpencarian = findViewById(R.id.btn_pencarian);



        recpencarian.setLayoutManager(new LinearLayoutManager(this));
        recpencarian.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        getTampilresep();

    }

    private void getTampilresep() {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApibaseUrl.URL_ROOT_HTTP).addConverterFactory(GsonConverterFactory.create()).build();
//        ApiService apiService = retrofit.create(ApiService.class);
        Call<Example> call = ApiResep.getData().getDataResepTerbaru();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful()){
                    Log.e("berhasil",response.body().toString());
                    List<Result> examples = response.body().getResults();
                    adapter = new AdapterPencarian(examples);
                    recpencarian.setAdapter(adapter);


                }
            }

            @Override
            public void onFailure(Call <Example> call, Throwable t) {
                Log.e("gagal",t.getLocalizedMessage());

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pencarian,menu);
        MenuItem sMenuItem = menu.findItem(R.id.pencariandata);
        SearchView searchView = (SearchView) sMenuItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
}