package com.example.test_work_for_cleveroad.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticlesListRetrofit {

    public static final String URL = "https://newsapi.org/v2/";
    public static final String API_KEY = "655c7a38382945bcb359a36d42f95efe";

    private static Retrofit getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static ArticlesListAPI getApiService(){
        return getInstance().create(ArticlesListAPI.class);
    }
}
