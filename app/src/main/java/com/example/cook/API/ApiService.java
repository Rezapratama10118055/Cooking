package com.example.cook.API;


import com.example.cook.Model.pencarian.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/recipes/:page")
    Call<com.example.cook.Model.Example> getDataResepTerbaru();

    @GET("/api/categorys/recipes/")
    Call<com.example.cook.Model.katagori.Example> getkatagori();


    @GET("/api/categorys/recipes/{key}")
    Call<com.example.cook.Model.KatagoriList.Example>getkatagoricari(@Path(value = "key", encoded = true) String key);

    @GET("/api/recipe/{key}")
    Call<com.example.cook.Model.bahan.Example> getDataResepDetail(@Path(value = "key", encoded = true) String key);

    @GET("api/search/")
    Call<Example> getDataCariResep(@Query("q") String query);

    @GET("api/search/?q=parameter")
    Call<List<Example>> getDataCari();

    @GET("/api/recipes")
    Call<com.example.cook.Model.Example>getdata();



}
