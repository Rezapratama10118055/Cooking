package com.example.cook.API;

import com.example.cook.Model.Example;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/recipes/:page")
    Call<Example>getDataResepTerbaru();

    @GET("/api/categorys/recipes/")
    Call<com.example.cook.Model.katagori.Example> getkatagori();


    @GET("/api/categorys/recipes/{key}")
    Call<com.example.cook.Model.katagori.Example>getkatagoricari();

    @GET("/api/recipe/{key}")
    Call<com.example.cook.Model.bahan.Example> getDataResepDetail(@Path(value = "key", encoded = true) String key);

    @GET("api/search/?q=parameter")
    Call<com.example.cook.Model.pencarian.Example>getDataCariResep();




}
