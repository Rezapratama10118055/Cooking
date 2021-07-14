package com.example.cook.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiResep {
    public static Retrofit getApiResepni(){
        HttpLoggingInterceptor httpLoggingInterception = new HttpLoggingInterceptor();
        httpLoggingInterception.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterception).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://masak-apa.tomorisakura.vercel.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;


    }
    public static ApiService getData(){
        ApiService apiService = getApiResepni().create(ApiService.class);
        return apiService;
    }
}
