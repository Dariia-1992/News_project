package com.example.test_work_for_cleveroad.network;

import com.example.test_work_for_cleveroad.model.ArticlesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticlesListAPI {
    @GET("everything")
    Call<ArticlesResponse> getArticles(@Query("apiKey") String apiKey, @Query("q") String query);
}
